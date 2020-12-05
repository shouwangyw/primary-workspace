package com.ds.L04_stack;

/**
 * 测试链表实现栈
 *
 * @author yangwei
 * @date 2020-05-09 07:28
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        StackApp.play(new LinkedListStack(4), "链表实现栈功能演示~~");
    }
}

/**
 * 链表实现栈
 */
class LinkedListStack implements Stack {
    /**
     * 定义一个双向链表
     */
    static class LinkedNode {
        private Integer value;
        private LinkedNode next;
        private LinkedNode pre;

        public LinkedNode(Integer value) {
            this.value = value;
        }
    }
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 记录链表有效数据长度
     */
    private int counter = 0;
    /**
     * 链表模拟栈
     */
    private LinkedNode head;
    /**
     * 栈顶
     */
    private LinkedNode top;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        // 初始化头结点和栈顶
        head = new LinkedNode(null);
        top = head.next;
    }
    @Override
    public boolean isFull() {
        return counter == maxSize;
    }
    @Override
    public boolean isEmpty() {
        return head.next == null;
    }
    @Override
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满了~~");
        }
        LinkedNode node = new LinkedNode(value);
        node.pre = top;
        if (top == null) {
            head.next = node;
            node.pre = head;
        } else {
            top.next = node;
        }
        top = node;
        counter++;
    }
    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~~");
        }
        int value = top.value;

        LinkedNode pre = top.pre;
        top.pre = null;
        top = pre;
        top.next = null;

        counter--;
        return value;
    }
    @Override
    public int peek() {
        return top.value;
    }
    @Override
    public void show() {
        LinkedNode cursor = head.next;
        while (cursor != null) {
            System.out.printf("%d -> ", cursor.value);
            cursor = cursor.next;
        }
        System.out.println("null");
    }
}
