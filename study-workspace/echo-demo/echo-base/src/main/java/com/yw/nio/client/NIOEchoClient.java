package com.yw.nio.client;

import com.yw.info.CmdInfo;
import com.yw.info.HostInfo;
import com.yw.util.InputUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by hp on 2019/5/4.
 */
public class NIOEchoClient {
    public static void main(String[] args) throws Exception {
        // 打开客户端连接通道
        SocketChannel clientChannel = SocketChannel.open();
        // 连接
        clientChannel.connect(new InetSocketAddress(HostInfo.HOST_NAME, HostInfo.PORT));
        // 开辟缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(50);
        boolean flag = true;
        while (flag){
            buffer.clear(); // 清空缓冲区
            String inputData = InputUtil.getString("请输入要发送的信息：").trim();
            // 将输入的数据保存在缓冲区之中
            buffer.put(inputData.getBytes());
            // 重置缓冲区
            buffer.flip();
            // 发送数据
            clientChannel.write(buffer);
            // 在读取之前进行缓冲区清空
            buffer.clear();
            int readCount = clientChannel.read(buffer);
            buffer.flip();
            System.err.println(new String(buffer.array(), 0, readCount));
            if(CmdInfo.CMD_QUIT.equalsIgnoreCase(inputData)){
                flag = false;
            }
        }
        clientChannel.close();
    }
}
