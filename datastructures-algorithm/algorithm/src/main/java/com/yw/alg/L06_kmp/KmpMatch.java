package com.yw.alg.L06_kmp;

import java.util.Arrays;

/**
 * KMP算法 字符串匹配
 *
 * @author yangwei
 * @date 2020-06-14 14:10
 */
public class KmpMatch {
    public static void main(String[] args) {
        String src = "BBC ABCDAB ABCDABCDABDE";
        String target = "ABCDABD";
        int[] next = kmpNext(target);
        System.out.printf("Next[%s] = %s\n", target, Arrays.toString(next));
        int index = match(src, target, next);
        System.out.println("KMP匹配index = " + index);
    }

    /**
     * kmp匹配算法
     */
    private static int match(String src, String target, int[] next) {
        for (int i = 0, j = 0; i < src.length(); i++) {
            // kmp 算法的核心
            while (j > 0 && src.charAt(i) != target.charAt(j)) {
                j = next[j - 1];
            }
            if (src.charAt(i) == target.charAt(j)) {
                j++;
            }
            if (j == target.length()) {
                // 找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串(子串)的部分匹配值
     */
    private static int[] kmpNext(String s) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        // 创建一个数组保存部分匹配值
        int[] next = new int[len];
        next[0] = 0;
        for (int i = 1, j = 0; i < len; i++) {
            // kmp 算法的核心
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
