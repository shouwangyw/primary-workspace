package com.yw.factory_simple;

import com.yw.factory_simple.factory.PlainSimpleFactory;
import com.yw.factory_simple.factory.StaticSimpleFactory;
import com.yw._model.Product;

/**
 * 测试
 */
public class Test {
    public static void main(String[] args) {
        Product productA1 = PlainSimpleFactory.create(ProductTypes.TYPE_A);
        productA1.methodDiff();
        Product productB1 = PlainSimpleFactory.create(ProductTypes.TYPE_B);
        productB1.methodDiff();

        Product productA2 = StaticSimpleFactory.createA();
        productA2.methodDiff();
        Product productB2 = StaticSimpleFactory.createB();
        productB2.methodDiff();
    }
}