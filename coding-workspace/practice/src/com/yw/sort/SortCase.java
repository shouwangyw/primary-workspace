package com.yw.sort;

import java.util.Arrays;

/**
 * @author yangwei
 * @date 2020-07-05 15:43
 */
public class SortCase {
    private static int[] arr = {2, 10, 8, 22, 34, 5, 12, 28, 21, 11};

    public static void main(String[] args) {
//        bubbleSort();
//        System.out.println("BubbleSort: " + Arrays.toString(arr));
//        selectSort();
//        System.out.println("SelectSort: " + Arrays.toString(arr));
//        insertSort();
//        System.out.println("InsertSort: " + Arrays.toString(arr));
//        shellSort();
//        System.out.println("ShellSort: " + Arrays.toString(arr));
//        quickSort();
//        System.out.println("QuickSort: " + Arrays.toString(arr));
//        mergeSort();
//        System.out.println("MergeSort: " + Arrays.toString(arr));
//        radixSort();
//        System.out.println("RadixSort: " + Arrays.toString(arr));
        heapSort();
        System.out.println("HeapSort: " + Arrays.toString(arr));
    }

    private static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void bubbleSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    swap(j, j + 1);
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    private static void selectSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(minIndex, i);
            }
        }
    }

    private static void insertSort() {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    private static void shellSort() {
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            for (int i = grap; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i;
                while (insertIndex - grap >= 0 && insertVal < arr[insertIndex - grap]) {
                    arr[insertIndex] = arr[insertIndex - grap];
                    insertIndex -= grap;
                }
                arr[insertIndex] = insertVal;
            }
        }
    }

    private static void quickSort() {
        quickSort(0, arr.length - 1);
    }

    private static void quickSort(int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            swap(l, r);
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(left, r);
        }
        if (l < right) {
            quickSort(l, right);
        }
    }

    private static void mergeSort() {
        mergeSort(0, arr.length - 1, new int[arr.length]);
    }

    private static void mergeSort(int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid, tmp);
            mergeSort(mid + 1, right, tmp);
            // 合并
            merge(left, mid, right, tmp);
        }
    }

    private static void merge(int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = tmp[t++];
        }
    }

    private static void radixSort() {
        int max = getMaxValue();
        int maxLength = String.valueOf(max).length();
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounter = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounter[digitOfElement]] = arr[j];
                bucketElementCounter[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketElementCounter.length; k++) {
                if (bucketElementCounter[k] != 0) {
                    for (int l = 0; l < bucketElementCounter[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounter[k] = 0;
            }
        }
    }

    private static int getMaxValue() {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    private static void heapSort() {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            swap(0, j);
            adjustHeap(0, j);
        }
    }

    private static void adjustHeap(int i, int length) {
        int tmp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > tmp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }
}
