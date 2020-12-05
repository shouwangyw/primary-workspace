package com.atguigu.dp.L02Factory.abstrac;

/**
 * @author yangwei
 * @date 2020-06-26 18:49
 */
public class LDPizzaOrderFactory extends AbstractPizzaOrderFactory {
    LDPizzaOrderFactory() {
        super("开始订购伦敦披萨");
    }

    @Override
    Pizza createPizza(String orderType) {
        if (orderType.equals("cheese")) {
            return new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            return new LDPepperPizza();
        }
        return null;
    }
}
