package com.atguigu.dp.L02Factory.method;

/**
 * @author yangwei
 * @date 2020-06-26 18:43
 */
public class BJCheesePizza extends Pizza {
    public BJCheesePizza() {
        super("北京奶酪披萨");
    }

    @Override
    public void prepare() {
        System.out.println("给制作北京奶酪披萨准备原材料");
    }
}
