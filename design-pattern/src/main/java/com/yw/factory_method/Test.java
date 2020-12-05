package com.yw.factory_method;

import com.yw._model.Product;
import com.yw.factory_method.factory.ConcreteFactoryA;
import com.yw.factory_method.factory.ConcreteFactoryB;
import com.yw.factory_method.factory.MethodFactory;

/**
 * 测试
 */
public class Test {
    public static void main(String[] args) {
        MethodFactory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.create();
        productA.methodDiff();

        MethodFactory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.create();
        productB.methodDiff();
    }
}