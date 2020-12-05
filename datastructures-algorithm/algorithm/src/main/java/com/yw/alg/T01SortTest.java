package com.alg;

import com.alg.L01_sort.AbstractSort;
import com.alg.L01_sort.HeapSort;

import java.util.Arrays;

/**
 * @author yangwei
 * @date 2020-05-03 17:01
 */
public class T01SortTest {
    public static void main(String[] args) {
//        // 冒泡排序Demo
//        AbstractSort bubbleSort = new BubbleSort().setArr(3, 9, -1, 10, -2);
////        System.out.printf("冒泡排序结果：%s\n", Arrays.toString(bubbleSort.sort()));
////        bubbleSort.basicTest(5, 4, 1, 8, 9, 38, 46, 7, 4);
////        bubbleSort.speedTest(80000);
////
//        // 选择排序Demo
//        AbstractSort selectSort = new SelectSort().setArr(3, 9, -1, 10, -2);
////        System.out.printf("选择排序结果：%s\n", Arrays.toString(selectSort.sort()));
////        selectSort.basicTest(5, 4, 1, 8, 9, 38, 46, 7, 4);
////        selectSort.speedTest(80000);
//
//        // 插入排序Demo
//        AbstractSort insertSort = new InsertSort();
//        insertSort.basicTest(5, 4, 1, 8, 9, 38, 46, 7, 4);
//        insertSort.speedTest(80000);
//
////        bubbleSort.speedTest(800000);
////        selectSort.speedTest(800000);
////        insertSort.speedTest(800000);
//
//        // 希尔排序Demo
//        AbstractSort shellSort = new ShellSort();
//        shellSort.basicTest(8, 9, 1, 7, 2, 3, 5, 4, 6, 0);
////        shellSort.speedTest(80000);
//        shellSort.speedTest(8000000);
//
//        // 快速排序Demo
//        AbstractSort quickSort = new QuickSort().setArr(-9, 78, 0, 23, -567, 70);
//        System.out.printf("快速排序结果：%s\n", Arrays.toString(quickSort.sort()));
//        quickSort.basicTest(2, 10, 8, 22, 34, 5, 12, 28, 21, 11);
//        quickSort.speedTest(80000);
//        quickSort.speedTest(800000);
//        quickSort.speedTest(8000000);
//        quickSort.speedTest(80000000);
//
//        // 归并排序Demo
//        AbstractSort mergeSort = new MergeSort().setArr(8, 4, 5, 7, 1, 3, 6, 2);
//        System.out.printf("归并排序结果：%s\n", Arrays.toString(mergeSort.sort()));
//        mergeSort.basicTest(2, 10, 8, 22, 34, 5, 12, 28, 21, 11);
//        mergeSort.speedTest(80000);
//        mergeSort.speedTest(800000);
//        mergeSort.speedTest(8000000);
//        mergeSort.speedTest(80000000);

//        // 基数排序Demo
//        AbstractSort radixSort = new RadixSort()
//                .setArr(53, 3, 542, 748, 14, 214);
//        System.out.printf("归并排序结果：%s\n", Arrays.toString(radixSort.sort()));
//        radixSort.basicTest(53, 3, 542, 748, 14, 214);
//        radixSort.speedTest(80000);
//        radixSort.speedTest(800000);
//        radixSort.speedTest(8000000);
//        radixSort.speedTest(80000000);

        AbstractSort heapSort = new HeapSort()
                .setArr(4, 6, 8, 5, 9);
        System.out.printf("堆排序结果：%s\n", Arrays.toString(heapSort.sort()));
        heapSort.basicTest(4, 6, 8, 5, 9, 53, 3, 542, 748, 14, 214);
        heapSort.speedTest(80000);
        heapSort.speedTest(800000);
        heapSort.speedTest(8000000);
        heapSort.speedTest(80000000);
    }
}
