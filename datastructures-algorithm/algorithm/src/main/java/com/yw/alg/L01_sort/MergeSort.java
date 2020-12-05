package com.yw.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-17 15:07
 */
public class MergeSort extends AbstractSort {
    private int mergeCounter = 0;

    public MergeSort() {
        super("归并排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        mergeCounter = 0;
        mergeSort(0, arr.length - 1, new int[arr.length]);
        System.out.printf("总共合并了 %d 次\n", mergeCounter);
        return arr;
    }

    /**
     * 分+合的方法
     *
     * @param left
     * @param right
     * @param temp
     */
    private void mergeSort(int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(left, mid, temp);
            // 向右递归分解
            mergeSort(mid + 1, right, temp);
            // 合并
            merge(left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做合并中转的数组
     */
    private void merge(int left, int mid, int right, int[] temp) {
        // 左边有序序列的初始索引
        int i = left;
        // 右边有序序列的初始索引
        int j = mid + 1;
        // 指向temp数组的当前索引
        int t = 0;

        //1、先把左右两边（有序）的数据按照规则填充到temp数组中
        //   直到左右两边的有序序列的一边处理完毕
        while (i <= mid && j <= right) {
            // 若左边有序序列的当前元素，小于等于右边有序序列的当前元素
            // 则将左边的当前元素，拷贝到temp数组，然后i++、t++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            }
            // 反之，则将右边的当前元素，拷贝到temp数组，然后j++、t++
            else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        //2、把有剩余数据的一边的数据依次全部填充到temp
        //2.1、左边有剩余元素，则全部填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        //2.2、右边有剩余元素，则全部填充到temp
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }

        //3、将temp数组的元素拷贝到arr，注意并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
        mergeCounter++;
    }
}
