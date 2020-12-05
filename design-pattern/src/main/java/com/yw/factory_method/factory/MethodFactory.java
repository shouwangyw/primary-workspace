package com.yw.factory_method.factory;

import com.yw._model.Product;

/**
 * 方法工厂接口
 */
public interface MethodFactory {
    Product create();
}