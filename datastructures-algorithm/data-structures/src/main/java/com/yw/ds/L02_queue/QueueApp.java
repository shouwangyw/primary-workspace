package com.yw.ds.L02_queue;

import java.util.Scanner;

/**
 * 队列演示App
 * @author yangwei
 * @date 2020-05-03 15:36
 */
public class QueueApp {
    public static void play(Queue queue, String subject) {
        System.out.println(subject);
        // 菜单
        System.out.println("s(show)：显示队列");
        System.out.println("e(exit)：退出程序");
        System.out.println("a(add)[空格][number]：添加数据到队列");
        System.out.println("g(get)：从队列获取数据");
        System.out.println("h(head)：查看队列头的数据");

        // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            // 接收一个字符
            String command = scanner.nextLine().trim();
            char key = command.charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    int value = 0;
                    try {
                        value = Integer.parseInt(command.split(" ")[1]);
                        queue.addQueue(value);
                        System.out.printf("%d入队列成功！\n", value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.printf("%d入队列失败！\n", value);
                    }
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}
