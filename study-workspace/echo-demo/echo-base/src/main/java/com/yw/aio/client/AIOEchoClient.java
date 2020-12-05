package com.yw.aio.client;


import com.yw.info.CmdInfo;
import com.yw.info.HostInfo;
import com.yw.util.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hp on 2019/5/4.
 */
public class AIOEchoClient {
    public static void main(String[] args) throws Exception {
        AIOClientThread client = new AIOClientThread();
        new Thread(client).start();
        while (client.sendMessage(InputUtil.getString("请输入要发送的内容："))) {
        }
    }
}
@SuppressWarnings("Since15")
class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public ClientReadHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String readMessage = new String(buffer.array(), 0, buffer.remaining());
        System.out.println(readMessage); // 输出读取内容
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.latch.countDown();
    }
}
@SuppressWarnings("Since15")
class ClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public ClientWriteHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) {
            this.clientChannel.write(buffer, buffer, this);
        } else {
            ByteBuffer readBuffer = ByteBuffer.allocate(100); // 读取服务端回应
            this.clientChannel.read(readBuffer, readBuffer, new ClientReadHandler(this.clientChannel, this.latch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.latch.countDown();
    }
}
@SuppressWarnings("Since15")
class AIOClientThread implements Runnable {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public AIOClientThread() {
        try {
            this.clientChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clientChannel.connect(new InetSocketAddress(HostInfo.HOST_NAME, HostInfo.PORT));
        this.latch = new CountDownLatch(1);
    }

    @Override
    public void run() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessage(String msg) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put(msg.getBytes());
        buffer.flip();
        this.clientChannel.write(buffer, buffer, new ClientWriteHandler(this.clientChannel, this.latch));
        if (CmdInfo.CMD_QUIT.equalsIgnoreCase(msg)) {
            return false;
        }
        return true;
    }
}
