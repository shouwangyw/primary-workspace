package com.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-15 08:13
 */
public class SelectSort extends AbstractSort {
    public SelectSort() {
        super("选择排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        // 第一层循环：共进行几趟选择交换
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // 第二层循环：共进行几次比较交换，并找到最小的那个元素的索引
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // 最后进行交换
//            swap(i, minIndex);
            // 优化
            if (i != minIndex) {
                swap(i, minIndex);
            }
        }
        return arr;
    }
}
