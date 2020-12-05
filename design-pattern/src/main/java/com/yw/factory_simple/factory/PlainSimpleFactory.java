package com.yw.factory_simple.factory;

import com.yw.factory_simple.ProductTypes;
import com.yw._model.ConcreteProductA;
import com.yw._model.ConcreteProductB;
import com.yw._model.Product;

/**
 * 普通-简单工厂
 */
public class PlainSimpleFactory {
    public static Product create(String type) {
        Product product = null;
        if (type.equalsIgnoreCase(ProductTypes.TYPE_A)) {
            product = new ConcreteProductA();
        } else if (type.equalsIgnoreCase(ProductTypes.TYPE_B)) {
            product = new ConcreteProductB();
        }
        return product;
    }
}