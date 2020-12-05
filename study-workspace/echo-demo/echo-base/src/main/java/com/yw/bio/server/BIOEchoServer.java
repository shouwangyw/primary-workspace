package com.yw.bio.server;

import com.yw.info.CmdInfo;
import com.yw.info.HostInfo;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hp on 2019/5/4.
 */
public class BIOEchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(HostInfo.PORT);
        System.out.println("服务器端已经启动，监听端口为：" + HostInfo.PORT);
        boolean flag = true;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (flag){
            Socket client = server.accept();
            executorService.submit(new EchoClientHandler(client));
        }
        executorService.shutdown();
        server.close();
    }
    private static class EchoClientHandler implements Runnable {
        private Socket client; // 每一个客户端都需要启动一个任务（task）来执行
        private Scanner scanner;
        private PrintStream out;
        private boolean flag = true; // 循环标记
        public EchoClientHandler(Socket client){
            this.client = client;   // 保存每一个客户端操作
            try{
                this.scanner = new Scanner(this.client.getInputStream());
                this.scanner.useDelimiter("\n"); // 设置换行符
                this.out = new PrintStream(this.client.getOutputStream());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (this.flag){
                if (this.scanner.hasNext()){    // 现在有数据进行输入
                    String val = this.scanner.next().trim(); // 去掉多余的空格内容
                    System.err.println("【服务器端】" + val);
                    if(CmdInfo.CMD_QUIT.equalsIgnoreCase(val)){
                        this.out.println("ByeBye ... ");
                        this.flag = false;
                    }else {
                        out.println("【Echo】" + val);
                    }
                }
            }
            this.scanner.close();
            this.out.close();
            try{
                this.client.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
