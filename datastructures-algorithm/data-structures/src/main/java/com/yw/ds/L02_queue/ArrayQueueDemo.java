package com.ds.L02_queue;

/**
 * 数组模拟实现Queue
 *
 * @author yangwei
 * @date 2020-05-03 14:37
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        QueueApp.play(new ArrayQueue(3), "测试数组模拟队列的案例~~");
    }
}

/**
 * 使用数组模拟一个队列，编写一个ArrayQueue
 */
class ArrayQueue implements Queue {
    /**
     * 数组最大容量
     */
    protected int maxSize;
    /**
     * 队列头
     */
    protected int front;
    /**
     * 队列尾
     */
    protected int rear;
    /**
     * 用于存放数据
     */
    protected int[] arr;

    /**
     * 创建队列的构造器
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        // 指向队列头部，队列头的前一个位置
        front = -1;
        // 指向队列尾，指向队列尾的数据
        rear = -1;
    }

    @Override
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            throw new RuntimeException("队列满，不能加入数据");
        }
        rear++;
        arr[rear] = n;
    }

    @Override
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 抛异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    @Override
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    @Override
    public int headQueue() {
        if (isEmpty()) {
            // 抛异常
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front + 1];
    }
}
