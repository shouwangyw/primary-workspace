package com.atguigu.dp.L02Factory.simple;

/**
 * @author yangwei
 * @date 2020-06-26 17:52
 */
public class PizzaStore {
    public static void main(String[] args) {
        new PizzaOrder(new SimpleFactory());
    }
}
