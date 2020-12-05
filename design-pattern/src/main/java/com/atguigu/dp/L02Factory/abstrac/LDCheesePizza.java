package com.atguigu.dp.L02Factory.abstrac;

/**
 * @author yangwei
 * @date 2020-06-26 18:43
 */
public class LDCheesePizza extends Pizza {
    public LDCheesePizza() {
        super("伦敦奶酪披萨");
    }

    @Override
    public void prepare() {
        System.out.println("给制作伦敦奶酪披萨准备原材料");
    }
}
