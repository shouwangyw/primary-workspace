package com.yw.factory_method.factory;

import com.yw._model.ConcreteProductA;
import com.yw._model.Product;

/**
 * 具体的方法工厂A
 */
public class ConcreteFactoryA implements MethodFactory {
	public Product create() {
		return new ConcreteProductA();
	}
}