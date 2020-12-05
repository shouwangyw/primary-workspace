package com.alg.L01_sort;

/**
 * @author yangwei
 * @date 2020-05-17 16:04
 */
public class RadixSort extends AbstractSort {
    public RadixSort() {
        super("基数排序");
    }

    @Override
    public int[] sort() {
        if (isEmpty()) {
            throw new RuntimeException("排序数组空~~");
        }
        radixSort();
        return arr;
    }

    private void radixSort() {
        // 得到数组中最大的数的位数，先假设第一数就是最大数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = String.valueOf(max).length();
        // 定义一个二维数组，表示10个桶，每一个桶都是一个一位数组
        //  为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为 arr.length
        //  基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounter = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // (针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounter[digitOfElement]] = arr[j];
                bucketElementCounter[digitOfElement]++;
            }
            // 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            // 遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounter.length; k++) {
                // 若桶中有数据，则放入到原数组中
                if (bucketElementCounter[k] != 0) {
                    // 循环该桶即第 k 个桶(即第 k 个一维数组), 放入
                    for (int l = 0; l < bucketElementCounter[k]; l++) {
                        // 取出元素放入到 arr
                        arr[index++] = bucket[k][l];
                    }
                }
                // 需要将每个 bucketElementCounts[k] = 0 !!!!
                bucketElementCounter[k] = 0;
            }
        }
    }
}
