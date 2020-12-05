package com.yw.t01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @date 2020-08-03 12:23
 */
public class T04_Test {
    public static void main(String[] args) {
        System.out.println();

        Map<String, Integer> map1 = new HashMap<>(10);
        Map<String, Integer> map2 = new HashMap<>(12);

        long t1 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            map1.put("key" + i, i);
        }
        System.out.println(System.nanoTime() - t1);
        long t2 = System.nanoTime();
        for (int i = 10; i < 20; i++) {
            map2.put("key" + i, i);
        }
        System.out.println(System.nanoTime() - t2);
    }
}
