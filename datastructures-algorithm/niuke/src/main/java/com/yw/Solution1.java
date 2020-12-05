package com.yw;

import java.util.Scanner;

/**
 * *c*m*b*n*t*
 * @author yangwei
 * @date 2020-06-07 14:29
 */
public class Solution1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int len = str.length();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (str.charAt(i) == '*') {
                sb1.append(c);
            } else {
                sb2.append(c);
            }
        }
        System.out.println(sb1.toString() + sb2.toString());
    }
}
