package com.yw.builder;

import com.yw.builder.product.Product;

/**
 * 构建者接口
 */
public interface Builder {
	Builder buildPartA();
	Builder buildPartB();
	Builder buildPartC();
	Product build();
}