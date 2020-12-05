package com.yw.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-16 23:15
 */
public class ShellSort extends AbstractSort {
    public ShellSort() {
        super("希尔排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
//        sortBySwap();
        sortByMove();
        return arr;
    }

    /**
     * 交换法希尔排序
     */
    private void sortBySwap() {
        // 增量 gap, 并逐步的缩小增量
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            for (int i = grap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共 gap 组，每组 arr.length/grap 个元素), 步长 gap
                for (int j = i - grap; j >= 0; j -= grap) {
                    if (arr[j] > arr[j + grap]) {
                        // 交换
                        swap(j, j + grap);
                    }
                }
            }
        }
    }

    /**
     * 移位法希尔排序
     */
    private void sortByMove() {
        // 增量 gap, 并逐步的缩小增量
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = grap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - grap]) {
                    while (j - grap >= 0 && temp < arr[j - grap]) {
                        // 移位
                        arr[j] = arr[j - grap];
                        j -= grap;
                    }
                    // 当退出 while 后，就给 temp 找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
