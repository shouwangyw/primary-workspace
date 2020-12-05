package com.yw.factory_abstract.factory;

import com.yw.factory_abstract.product.ConcreteProductA1;
import com.yw.factory_abstract.product.ConcreteProductB1;
import com.yw.factory_abstract.product.ProductA;
import com.yw.factory_abstract.product.ProductB;

/**
 * 具体工厂1
 */
public class ConcreteFactory1 implements AbstractFactory {
    public ProductA createA() {
        return new ConcreteProductA1();
    }

    public ProductB createB() {
        return new ConcreteProductB1();
    }
}