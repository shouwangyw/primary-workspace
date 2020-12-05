package com.atguigu.dp.L05Adapter.springmvc;

/**
 * @author yangwei
 * @date 2020-06-27 10:06
 */
public interface Handler {
    void doHandle();
}
class SimpleHandler implements Handler {
    @Override
    public void doHandle() {
        System.out.println("SimpleHandler.doHandle() ...");
    }
}
class HttpHandler implements Handler {
    @Override
    public void doHandle() {
        System.out.println("HttpHandler.doHandle() ...");
    }
}
class AnnotationHandler implements Handler {
    @Override
    public void doHandle() {
        System.out.println("AnnotationHandler.doHandle() ...");
    }
}