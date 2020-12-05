package com.yw;

import java.io.IOException;

/**
 * 强引用
 *
 * @author yangwei
 * @date 2020-04-23 09:36
 */
public class T01NormalReference {
    public static void main(String[] args) throws IOException {
        Obj o = new Obj();
        o = null;
        System.gc(); // DisableExplicitGC

        System.in.read();
    }
}
