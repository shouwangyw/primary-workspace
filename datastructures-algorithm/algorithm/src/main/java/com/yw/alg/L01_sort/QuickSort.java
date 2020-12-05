package com.yw.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-17 00:08
 */
public class QuickSort extends AbstractSort {
    public QuickSort() {
        super("快速排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        quickSort(0, arr.length - 1);
        return arr;
    }

    private void quickSort(int left, int right) {
        // 左索引下标
        int l = left;
        // 右索引下标
        int r = right;
        // pivot中轴
        int pivot = arr[(left + right) / 2];
        // while循环：目的是让比 pivot 值小的放在左边，比 pivot 值大的放在右边
        while (l < r) {
            // 在pivot左边一直找，找到大于等于pivot值才退出
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot右边一直找，找到小于等于pivot值才退出
            while (arr[r] > pivot) {
                r--;
            }
            // l >= r 说明pivot的左右两边的值，已经按照
            // 左边全部是小于等于pivot，右边全部是大于等于pivot
            if (l >= r) {
                break;
            }
            // 否则就进行交换
            swap(l, r);
            // 若交换完后，arr[l]==pivot，则r--前移
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，arr[r]==pivot，则l++后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 若l==r，必须l++、r--，否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(l, right);
        }
    }
}
