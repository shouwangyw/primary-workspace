package com.atguigu.dp.L02Factory.abstrac;

/**
 * @author yangwei
 * @date 2020-06-26 18:43
 */
public class BJPepperPizza extends Pizza {
    public BJPepperPizza() {
        super("北京胡椒披萨");
    }

    @Override
    public void prepare() {
        System.out.println("给制作北京胡椒披萨准备原材料");
    }
}
