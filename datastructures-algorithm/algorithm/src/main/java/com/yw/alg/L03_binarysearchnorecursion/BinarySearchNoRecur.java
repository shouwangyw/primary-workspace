package com.yw.alg.L03_binarysearchnorecursion;

/**
 * 二分查找-非递归实现
 * @author yangwei
 * @date 2020-06-13 19:05
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        for (int a : arr) {
            int index = search(arr, a);
            System.out.printf("非递归二分查找 %d, index = %d\n", a, index);
        }
        System.out.printf("非递归二分查找 %d, index = %d\n", -8, search(arr, -8));
    }

    private static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
