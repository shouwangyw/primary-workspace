package com.atguigu.dp.L04Builder.impove;

/**
 * @author yangwei
 * @date 2020-06-26 21:56
 */
public abstract class HouseBuilder {
    protected House house = new House();
    abstract void buildBasic();
    abstract void buildWalls();
    abstract void roofed();
    public House getHouse() {
        return house;
    }
}
