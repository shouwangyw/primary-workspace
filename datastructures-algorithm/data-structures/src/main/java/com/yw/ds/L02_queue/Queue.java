package com.ds.L02_queue;

/**
 * 定义一个队列接口
 *
 * @author yangwei
 * @date 2020-05-03 15:29
 */
public interface Queue {
    /**
     * 判断队列是否满
     */
    boolean isFull();

    /**
     * 判断队列是否为空
     */
    boolean isEmpty();

    /**
     * 添加数据到队列
     */
    void addQueue(int n);

    /**
     * 获取队列的数据，出队列
     */
    int getQueue();

    /**
     * 打印队列的数据
     */
    void showQueue();

    /**
     * 显示队列的头数据，不是取数据
     */
    int headQueue();
}
