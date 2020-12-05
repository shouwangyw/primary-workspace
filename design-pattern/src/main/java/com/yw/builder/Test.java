package com.yw.builder;

import com.yw.builder.product.Product;

/**
 * 测试
 */
public class Test {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.constructProduct();
        System.out.println(product); // Product [partA=A, partB=B, partC=C]
    }
}