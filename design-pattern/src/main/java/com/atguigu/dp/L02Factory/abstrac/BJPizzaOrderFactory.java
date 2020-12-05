package com.atguigu.dp.L02Factory.abstrac;

/**
 * @author yangwei
 * @date 2020-06-26 19:07
 */
public class BJPizzaOrderFactory extends AbstractPizzaOrderFactory {
    BJPizzaOrderFactory() {
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
