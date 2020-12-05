package com.yw.A1B2C3D4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangwei
 * @date 2020-04-30 16:16
 */
public class T03AtomicInteger {

    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (threadNo.get() != 1) {}
                System.out.println(c);
                threadNo.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (threadNo.get() != 2) {}
                System.out.println(c);
                threadNo.set(1);
            }
        }, "t2").start();
    }
}
