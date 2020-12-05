package com.atguigu.dp.L04Builder;

/**
 * 建造者模式在JDK应用的源码分析
 * @author yangwei
 * @date 2020-06-26 22:12
 */
public class JDKBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, World");
        System.out.println(sb);
    }
}
