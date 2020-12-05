package com.ds.L04_stack;

/**
 * 测试数组实现栈
 *
 * @author yangwei
 * @date 2020-05-08 23:41
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        StackApp.play(new ArrayStack(4), "数组实现栈功能演示~~");
    }
}

/**
 * 数组实现栈
 */
class ArrayStack implements Stack {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组模拟栈
     */
    private int[] stack;
    /**
     * 栈顶，初始化为-1
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize <= 0 ? 0 : maxSize;
        stack = new int[this.maxSize];
    }

    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满了~~");
        }
        top++;
        stack[top] = value;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    @Override
    public int peek() {
        return stack[top];
    }

    @Override
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
