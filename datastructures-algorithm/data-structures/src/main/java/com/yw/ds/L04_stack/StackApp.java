package com.yw.ds.L04_stack;

import java.util.Scanner;

/**
 * @author yangwei
 * @date 2020-05-08 23:59
 */
public class StackApp {
    public static void play(Stack stack, String subject) {
        System.out.println(subject);
        // 菜单
        System.out.println("show：显示栈");
        System.out.println("exit：退出程序");
        System.out.println("push[空格][number]：添加数据到栈（入栈）");
        System.out.println("pop：从栈获取数据（出栈）");
        System.out.println("peek：查看栈顶的数据");

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            // 接收一个字符
            String command = scanner.nextLine().trim();
            String[] commands = command.split(" ");
            switch (commands[0]) {
                case "show":
                    stack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    try {
                        int value = Integer.parseInt(commands[1]);
                        stack.push(value);
                        System.out.printf("%d入栈成功！\n", value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " 入栈失败!");
                    }
                    break;
                case "pop":
                    try {
                        int value = stack.pop();
                        System.out.printf("出栈的数据是%d\n", value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "peek":
                    try {
                        int value = stack.peek();
                        System.out.printf("栈顶的数据是%d\n", value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
