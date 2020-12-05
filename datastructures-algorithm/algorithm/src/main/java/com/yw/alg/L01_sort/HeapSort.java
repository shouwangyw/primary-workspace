package com.yw.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-06-04 22:49
 */
public class HeapSort extends AbstractSort {
    public HeapSort() {
        super("堆排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        heapSort();
        return arr;
    }

    private void heapSort() {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(i, arr.length);
        }
//        System.out.println("调成大顶堆后的：" + Arrays.toString(arr));
        // 不断进行 交换and调整
        for (int j = arr.length - 1; j > 0;j--) {
            // 交换
            swap(0, j);
            // 调整
            adjustHeap(0, j);
        }
    }

    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * 完成将以 i 对应的非叶子结点的树 调整成大顶堆
     * 举例：int arr[] = {4, 6, 8, 5, 9}
     * 1、i=1 => adjustHeap() => 得到 {4, 9, 8, 5, 6}
     * 2、i=0 => adjustHeap() => 得到 {9, 6, 8, 5, 4}
     *
     * @param i      表示非叶子结点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     */
    private void adjustHeap(int i, int length) {
        // 先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        // 开始调整
        // k = i * 2 + 1 表示 k 是 i 结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 说明左子结点的值小于右子结点的值，则k++指向右子结点
                k++;
            }
            if (arr[k] > temp) {
                // 如果子结点大于父结点，则把较大的值赋给当前结点
                arr[i] = arr[k];
                // i 指向 k，继续循环比较
                i = k;
            } else {
                break;
            }
        }
        // 当for循环结束后，就已经将以i为父结点的数的最大值，放到了最顶(局部)
        // 最后将temp值放到调整后的位置
        arr[i] = temp;
    }
}
