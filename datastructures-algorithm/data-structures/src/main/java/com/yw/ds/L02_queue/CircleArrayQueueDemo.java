package com.yw.ds.L02_queue;

/**
 * 数组模拟实现Queue
 *
 * @author yangwei
 * @date 2020-05-03 14:37
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        QueueApp.play(new CircleArrayQueue(4), "测试数据模拟环形队列的案例~~");
    }
}

class CircleArrayQueue extends ArrayQueue {
    /**
     * 创建队列的构造器
     */
    public CircleArrayQueue(int maxSize) {
        super(maxSize);
        front = 0;
        rear = 0;
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    @Override
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            throw new RuntimeException("队列满，不能加入数据");
        }
        // 直接将数据加入
        arr[rear] = n;
        // 将rear后移，这里必须考虑后移
        rear = (rear + 1) % maxSize;
    }

    @Override
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 抛异常
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出front是指向队列的第一个元素
        // 先把front对应的值保留到一个个临时的变量
        int value = arr[front];
        // 将front后移，并考虑取模
        front = (front + 1) % maxSize;
        // 将临时保存的变量返回
        return value;
    }

    @Override
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据~");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 请当前队列的有些数据个数
     *
     * @return
     */
    private int size() {
        return (rear + maxSize - front) % maxSize;
    }

    @Override
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}

