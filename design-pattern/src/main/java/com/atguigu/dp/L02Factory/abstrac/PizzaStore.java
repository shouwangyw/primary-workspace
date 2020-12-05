package com.atguigu.dp.L02Factory.abstrac;

/**
 * @author yangwei
 * @date 2020-06-26 17:52
 */
public class PizzaStore {
    public static void main(String[] args) {
        new PizzaOrder(new BJPizzaOrderFactory());
        new PizzaOrder(new LDPizzaOrderFactory());
    }
}
