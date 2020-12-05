package com.atguigu.dp.L02Factory.method;

/**
 * @author yangwei
 * @date 2020-06-26 18:43
 */
public class LDPepperPizza extends Pizza {
    public LDPepperPizza() {
        super("伦敦胡椒披萨");
    }

    @Override
    public void prepare() {
        System.out.println("给制作伦敦胡椒披萨准备原材料");
    }
}
