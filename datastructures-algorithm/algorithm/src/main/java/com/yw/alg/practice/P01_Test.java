package com.yw.alg.practice;

/**
 * @author yangwei
 * @date 2020-06-11 22:33
 */
public class P01_Test {
    public static void main(String[] args) {
        System.out.println(reverse(-112324312));
    }

    public static int reverse(int x) {
        int resX = 0;
        while (x != 0) {
            int tail = x % 10;
            if (resX > 214748364 || (resX == 214748364 && tail > 7)) {
                return 0;
            }
            if (resX < -214748364 || (resX == -214748364 && tail < -8)) {
                return 0;
            }
            resX = resX * 10 + tail;
            x /= 10;
        }
        return resX;
    }
}
