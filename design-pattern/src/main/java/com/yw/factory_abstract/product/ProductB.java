package com.yw.factory_abstract.product;

/**
 * 抽象产品B
 */
public abstract class ProductB {
    // 所有产品类的公共业务方法
    public void methodSame() {
        // 公共方法具体实现
    }

    // 声明抽象业务方法
    public abstract void methodDiff();
}