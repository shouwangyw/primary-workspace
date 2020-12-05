package com.atguigu.dp.L05Adapter.springmvc;

/**
 * @author yangwei
 * @date 2020-06-27 10:07
 */
public interface HandlerAdapter {
    boolean support(Handler handler);
    void handle(Handler handler);
}
class SimpleHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Handler handler) {
        return handler instanceof SimpleHandler;
    }
    @Override
    public void handle(Handler handler) {
        handler.doHandle();
    }
}
class HttpHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Handler handler) {
        return handler instanceof HttpHandler;
    }
    @Override
    public void handle(Handler handler) {
        handler.doHandle();
    }
}
class AnnotationHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Handler handler) {
        return handler instanceof AnnotationHandler;
    }
    @Override
    public void handle(Handler handler) {
        handler.doHandle();
    }
}