package com.atguigu.dp.L04Builder.house;

/**
 * @author yangwei
 * @date 2020-06-26 21:42
 */
public abstract class AbstractHouse {
    abstract void buildBasic();
    abstract void buildWalls();
    abstract void roofed();
    public void build() {
        buildBasic();
        buildWalls();
        roofed();
        System.out.println("房子建造完成~~");
    }
}
