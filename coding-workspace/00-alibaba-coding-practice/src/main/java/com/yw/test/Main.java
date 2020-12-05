package com.yw.test;

import java.util.Scanner;

/**
 * 翻转字符串（1）
 * 给定字符类型的数组chas，请在单词间做逆序调整。只要做到单词的顺序逆序即可，对空格的位置没有要求。
 * 输入描述
 * 输入一行只含字母和空格的字符串，代表chas。
 * 输出描述
 * 输出一行字符串，代表逆转顺序后的字符串。
 * 示例1
 * 输入
 * i am a student
 * 输出
 * i ma a tneduts
 * @author yangwei
 * @date 2020-04-30 19:32
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(revertStr(s));
    }

    private static String revertStr(String str) {
        // 按空格拆分
        String[] s = str.split(" ");
        // 每个单词进行逆序
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            char[] cs = s[i].toCharArray();
            StringBuffer sb = new StringBuffer();
            int cl = cs.length;
            for (int j = 0; j < cl; j++) {
                sb.append(cs[cl - j - 1]);
            }
            result.append(sb).append(" ");
        }

        return result.toString().trim();
    }
}
