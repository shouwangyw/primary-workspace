package com.yw;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用
 *
 * @author yangwei
 * @date 2020-04-24 07:48
 */
public class T02SoftReference {
    private static final int SIZE_5_M = 1024 * 1024 * 5;
    private static final int SIZE_6_M = 1024 * 1024 * 6;

    public static void main(String[] args) {
        SoftReference<byte[]> sr = new SoftReference<>(new byte[SIZE_5_M]);
        System.out.println(sr.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(sr.get());

        // 再分配一个数组，堆内存将装不下，这时候系统会发生GC
        // 先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[SIZE_6_M];
        System.out.println(sr.get());

//        SoftReference<Obj> sr = new SoftReference<>(new Obj(1, "Tom"));
//        Runnable r1 = () -> {
//            while (true) {
//                if (sr.get() == null) {
//                    System.out.println("==>> " + sr.get());
//                    break;
//                } else {
//                    System.out.println("==>> " + sr.get());
//                }
//            }
//        };
//        Runnable r2 = () -> {
//            List<String> list = new ArrayList<>();
//            int i = 100;
//            while (true) {
//                list.add((i++) + "");
//            }
//        };
//
//        new Thread(r1).start();
//        new Thread(r2).start();
    }
}
