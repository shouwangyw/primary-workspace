package com.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-15 13:53
 */
public class InsertSort extends AbstractSort {
    public InsertSort() {
        super("插入排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        // 一开始，第0个元素有序，接着需要依次遍历第 1~length-1 个元素，进行插入排序
        for (int i = 1; i < arr.length; i++) {
            int insertVal =  arr[i];
            int insertIndex = i - 1;
            // 给 insertVal 找到插入的位置
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 进行数据移动（移动法）
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出 while 循环时，说明插入的位置找到, insertIndex + 1
            // 这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        return arr;
    }
}
