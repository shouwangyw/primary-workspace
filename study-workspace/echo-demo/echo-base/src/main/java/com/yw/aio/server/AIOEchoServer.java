package com.yw.aio.server;

import com.yw.info.CmdInfo;
import com.yw.info.HostInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hp on 2019/5/4.
 */
public class AIOEchoServer {
    public static void main(String[] args) throws Exception {
        new Thread(new AIOServerThread()).start();
    }
}

/**
 * 定义一个单独的的服务器的处理线程
 */
@SuppressWarnings("Since15")
class AIOServerThread implements Runnable {
    private AsynchronousServerSocketChannel serverSocketChannel = null;
    private CountDownLatch latch = null; // 做一个同步处理操作

    public AIOServerThread() throws Exception {
        this.latch = new CountDownLatch(1);  // 等待线程数量为1
        // 打开服务器的通道
        this.serverSocketChannel = AsynchronousServerSocketChannel.open();
        // 绑定端口
        this.serverSocketChannel.bind(new InetSocketAddress(HostInfo.PORT));
        System.out.println("服务器启动成功，监听端口为：" + HostInfo.PORT);
    }

    public AsynchronousServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    public void run() {
        this.serverSocketChannel.accept(this, new AcceptHandler());
        try {
            this.latch.await(); // 线程等待
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 连接接收的回调处理操作
 */
@SuppressWarnings("Since15")
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerThread> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AIOServerThread aioThread) {
        aioThread.getServerSocketChannel().accept(aioThread, this); // 接收连接
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer, buffer, new EchoHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AIOServerThread aioThread) {
        System.err.println("客户端连接创建失败....");
        aioThread.getLatch().countDown();
    }
}

@SuppressWarnings("Since15")
class EchoHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    // 是否结束交互过程，exit = true表示结束，exit = false表示继续
    private boolean exit = false;

    public EchoHandler(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        // 读取之前需要执行重置处理
        buffer.flip();
        String readMessage = new String(buffer.array(), 0, buffer.remaining()).trim();
        System.out.println("【服务器端接收到消息内容】" + readMessage);
        // 回应的数据信息
        String writeMessage = "【Echo】" + readMessage + "\n";
        if (CmdInfo.CMD_QUIT.equalsIgnoreCase(readMessage)) {
            writeMessage = "【Echo】 ByeBye ... \n";
            // 结束后期的交互
            this.exit = true;
        }
        this.echoWrite(writeMessage);
    }

    private void echoWrite(String content) {
        final ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put(content.getBytes());// 向缓存中保存数据
        buffer.flip();
        this.clientChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buf) {
                if (buf.hasRemaining()) {   // 缓存中是否有数据
                    EchoHandler.this.clientChannel.write(buffer, buffer, this);
                } else {
                    if (!EchoHandler.this.exit) {    // 还没有结束
                        ByteBuffer readBuffer = ByteBuffer.allocate(100);
                        EchoHandler.this.clientChannel.read(readBuffer, readBuffer, new EchoHandler(EchoHandler.this.clientChannel));
                    }
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    EchoHandler.this.clientChannel.close();
                } catch (IOException e) {
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        try {
            this.clientChannel.close();
        } catch (IOException e) {
        }
    }
}