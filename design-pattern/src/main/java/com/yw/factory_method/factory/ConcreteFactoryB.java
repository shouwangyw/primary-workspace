package com.yw.factory_method.factory;

import com.yw._model.ConcreteProductB;
import com.yw._model.Product;

/**
 * 具体的方法工厂B
 */
public class ConcreteFactoryB implements MethodFactory {
    public Product create() {
        return new ConcreteProductB();
    }
}