package com.atguigu.dp.L02Factory.method;

/**
 * @author yangwei
 * @date 2020-06-26 18:49
 */
public class BJPizzaOrder extends PizzaOrder {
    BJPizzaOrder() {
        super("开始订购北京披萨");
    }
    @Override
    Pizza createPizza(String orderType) {
        if (orderType.equals("cheese")) {
            return new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            return new BJPepperPizza();
        }
        return null;
    }
}
