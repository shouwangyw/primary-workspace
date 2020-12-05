package com.alg.L01_sort;

import java.util.Arrays;

/**
 * @author yangwei
 * @date 2020-05-15 07:28
 */
public abstract class AbstractSort {
    /**
     * 排序算法名称
     */
    protected String sortName;
    /**
     * 定义一个数组
     */
    protected int[] arr;
    /**
     * 定义一个计数器，用于记录发生交换的次数
     */
    private int swapCounter;

    protected AbstractSort(String sortName) {
        this.sortName = sortName;
        this.swapCounter = 0;
    }

    public AbstractSort setArr(int... arr) {
        this.arr = arr;
        return this;
    }

    /**
     * 抽象出排序方法，由具体子类实现
     *
     * @return 返回有序的数组
     */
    public abstract int[] sort();

    /**
     * 判断数组是否空
     */
    protected boolean isEmpty() {
        return arr == null || arr.length == 0;
    }

    /**
     * 简单几个数进行的排序测试
     */
    public void basicTest(int... arr) {
        System.out.println("【" + sortName + "】简单几个数进行的排序测试输出");
        this.arr = arr;
        System.out.print(sortName + "前：");
        print();
        sort();
        System.out.print(sortName + "后：");
        print();
        System.out.println();
    }

    /**
     * 排序速度测试
     *
     * @param num 排序数量
     */
    public void speedTest(int num) {
        System.out.println("【" + sortName + "】排序速度测试");
        arr = generateArr(num);
        System.out.printf(sortName + " %d 个数，", num);
        long startTime = System.currentTimeMillis();
        sort();
        long endTime = System.currentTimeMillis();
        System.out.printf("总耗时：%d ms\n", endTime - startTime);
        System.out.printf("总共进行了：%d 次数据交换\n\n", getSwapCounter());
    }

    /**
     * 元素交换
     */
    protected void swap(int i, int j) {
        swapCounter++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     */
    protected void print() {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 获取排序发生交换的次数
     */
    protected int getSwapCounter() {
        return swapCounter;
    }

    /**
     * 随机生成 num 个数
     *
     * @param num
     * @return
     */
    private int[] generateArr(int num) {
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            // 随机生成一个[0, Integer.MAX_VALUE)的 数
            arr[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return arr;
    }
}
