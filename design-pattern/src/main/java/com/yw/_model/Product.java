package com.yw._model;

/**
 * 抽象产品类
 */
public abstract class Product {
    // 所有产品类的公共业务方法
    public void methodSame() {
        // 公共方法具体实现
    }

    // 声明抽象业务方法
    public abstract void methodDiff();
}