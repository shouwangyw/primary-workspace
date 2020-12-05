package com.yw.kmp;

import java.util.Arrays;

/**
 * @author yangwei
 * @date 2020-06-13 14:47
 */
public class P01_Test {
    public static void main(String[] args) {
        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch("ABCDABD", "ABD", next));
    }

    private static int kmpSearch(String src, String target, int[] next) {
        for (int i = 0, j = 0; i < src.length(); i++) {
            while (j > 0 && src.charAt(i) != target.charAt(j)) {
                j = next[j - 1];
            }
            if (src.charAt(i) == target.charAt(j)) {
                j++;
            }
            if (j == target.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    private static int[] kmpNext(String s) {
        int[] next = new int[s.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
