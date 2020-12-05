package com.yw.bio.client;

import com.yw.info.CmdInfo;
import com.yw.info.HostInfo;
import com.yw.util.InputUtil;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by hp on 2019/5/4.
 */
public class BIOEchoClient {
    public static void main(String[] args) throws Exception{
        // 定义连接的主机信息
        Socket client = new Socket(HostInfo.HOST_NAME, HostInfo.PORT);
        // 获取服务器端的响应数据
        Scanner scanner = new Scanner(client.getInputStream());
        scanner.useDelimiter("\n");
        // 向服务器端发送信息内容
        PrintStream out = new PrintStream(client.getOutputStream());
        boolean flag = true; // 交互的标记
        while (flag){
            String inputData = InputUtil.getString("请输入要发送的内容：").trim();
            out.println(inputData); // 把数据发送到服务器端上
            if(scanner.hasNext()){
                String str = scanner.next().trim();
                System.out.println(str);
            }
            if(CmdInfo.CMD_QUIT.equalsIgnoreCase(inputData)){
                flag = false;
            }
        }
        client.close();
    }
}
