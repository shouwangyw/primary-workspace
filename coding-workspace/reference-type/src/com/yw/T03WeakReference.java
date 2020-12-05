package com.yw;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * @author yangwei
 * @date 2020-04-24 07:48
 */
public class T03WeakReference {
    public static void main(String[] args) {
        WeakReference<Obj> wr = new WeakReference<>(new Obj());

        System.out.println(wr.get());
        System.gc();
        System.out.println(wr.get());

        ThreadLocal<Obj> tl = new ThreadLocal<>();
        tl.set(new Obj());
        tl.remove(); // !!! 注意用完了要remove，防止内存泄漏
    }
}
