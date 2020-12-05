package com.atguigu.dp.L02Factory.simple;

/**
 * @author yangwei
 * @date 2020-06-26 17:45
 */
public class GreekPizza extends Pizza {
    public GreekPizza() {
        super("希腊披萨");
    }
    @Override
    public void prepare() {
        System.out.println("给希腊披萨准备原材料");
    }
}
