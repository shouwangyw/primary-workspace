package com.atguigu.dp.L02Factory.pizza;

/**
 * @author yangwei
 * @date 2020-06-26 17:44
 */
public class CheesePizza extends Pizza {
    public CheesePizza() {
        super("奶酪披萨");
    }
    @Override
    public void prepare() {
        System.out.println("给制作奶酪披萨准备原材料");
    }
}
