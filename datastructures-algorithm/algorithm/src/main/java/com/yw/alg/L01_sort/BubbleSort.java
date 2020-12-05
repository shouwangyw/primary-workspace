package com.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-14 08:02
 */
public class BubbleSort extends AbstractSort {
    public BubbleSort(){
        super("冒泡排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        // 第一层循环：共进行几趟冒泡
        for (int i = 0; i < arr.length - 1; i++) {
            // 优化：定义一个标志位，表示是在第二层循环中否进行过交换
            boolean flag = false;
            // 第二层循环：共进行几次比较交换
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 升序：如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    swap(j, j + 1);
                }
            }
            // 若flag为false，说明这一趟冒泡没有发生交换，
            // 数组已经是有序的，可以提前结束循环
            if (!flag) {
                break;
            }
        }
        return arr;
    }
}
