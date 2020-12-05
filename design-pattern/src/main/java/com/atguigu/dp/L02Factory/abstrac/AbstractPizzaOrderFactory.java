package com.atguigu.dp.L02Factory.abstrac;

/**
 * @author yangwei
 * @date 2020-06-26 19:07
 */
public abstract class AbstractPizzaOrderFactory {
    AbstractPizzaOrderFactory(String desc) {
        System.out.println(desc);
    }

    abstract Pizza createPizza(String orderType);
}
