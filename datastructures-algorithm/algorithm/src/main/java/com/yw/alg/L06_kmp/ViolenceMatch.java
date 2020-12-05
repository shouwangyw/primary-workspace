package com.alg.L06_kmp;

/**
 * 暴力匹配
 *
 * @author yangwei
 * @date 2020-06-13 23:42
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        System.out.println(match("硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好", "尚硅谷你尚硅你"));
    }

    private static int match(String src, String target) {
        int i = 0, j = 0;
        while (i < src.length() && j < target.length()) {
            if (src.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
            if (j == target.length()) {
                return i - j;
            }
        }
        return -1;
    }
}
