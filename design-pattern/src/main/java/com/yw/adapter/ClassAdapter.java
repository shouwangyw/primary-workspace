package com.yw.adapter;

import com.yw.adapter.Adaptee;
import com.yw.adapter.Target;

/**
 * 类适配器类
 */
public class ClassAdapter extends Adaptee implements Target {
    public void method2() {
        System.out.println("This is Target's method");
    }
}