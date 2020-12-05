package com.ds.L04_stack;

/**
 * 定义栈接口
 * @author yangwei
 * @date 2020-05-09 00:00
 */
public interface Stack {
    /**
     * 判断栈满
     *
     * @return
     */
    boolean isFull();
    /**
     * 判断栈空
     *
     * @return
     */
    boolean isEmpty();
    /**
     * 入栈-push
     */
    void push(int value);
    /**
     * 出栈-pop
     */
    int pop();
    /**
     * 查看栈顶的元素
     */
    int peek();
    /**
     * 遍历栈
     */
    void show();
}
