package com.yw.singleton;

/**
 * @author yangwei
 * @date 2020-01-07 23:35
 */
public class LazySingleton1 {
    private LazySingleton1() {}
    private static LazySingleton1 instance = null;

    public static LazySingleton1 getInstance() {
        if (instance == null) {
            instance = new LazySingleton1();
        }
        return instance;
    }
}
