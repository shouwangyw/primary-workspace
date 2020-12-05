package com.yw.A1B2C3D4;

/**
 * @author yangwei
 * @date 2020-04-30 16:16
 */
public class T02Cas {
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (r != ReadyToRun.T1) {}
                System.out.println(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadyToRun.T2) {}
                System.out.println(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}
