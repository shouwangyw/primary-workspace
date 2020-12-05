package com.atguigu.dp.L04Builder.impove;

/**
 * 指挥者：去指定建造房子的流程
 * @author yangwei
 * @date 2020-06-26 22:00
 */
public class HouseDirector {
    private HouseBuilder builder;
    HouseDirector(HouseBuilder builder) {
        this.builder = builder;
    }
    public House build() {
        builder.buildBasic();
        builder.buildWalls();
        builder.roofed();
        return builder.getHouse();
    }
}
