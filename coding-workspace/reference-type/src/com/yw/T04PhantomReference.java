package com.yw;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 *
 * @author yangwei
 * @date 2020-04-24 07:48
 */
public class T04PhantomReference {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<Obj> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<Obj> pr = new PhantomReference<>(new Obj(), QUEUE);

        new Thread(() -> {
           while (true) {
               LIST.add(new byte[1024 * 1024]);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   System.out.println(e.getMessage());
                   Thread.currentThread().interrupt();
               }
               System.out.println(pr.get());
           }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends Obj> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("--- 虚引用对象被JVM回收了 ---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
