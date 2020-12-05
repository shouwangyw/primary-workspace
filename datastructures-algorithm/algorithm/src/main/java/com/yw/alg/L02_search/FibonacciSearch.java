package com.yw.alg.L02_search;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-05-18 22:28
 */
public class FibonacciSearch extends AbstractSearch {
    private static int maxSize = 20;
    private static int[] f = fib();

    public FibonacciSearch() {
        super("斐波那契查找");
    }

    @Override
    public int findOne(int findVal) {
        if (isEmpty()) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        // 斐波那契分割数值的下标
        int k = 0;
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用 Arrays 类，构造一个新的数组，并指向 temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 实际上需求使用 a 数组最后的数填充 temp
        // 举例：temp = {1,8, 10, 89, 1000, 1234, 0, 0} => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        return findOne(low, high, k, temp, findVal);
    }

    private int findOne(int low, int high, int k, int[] temp, int findVal) {
        int mid;
        // 使用 while 来循环处理，找到我们的数 findVal
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (findVal < temp[mid]) {
                // 应该继续向数组的前面查找(左边)
                high = mid - 1;
                // 全部元素 = 前面的元素 + 后边元素；f[k] = f[k-1] + f[k-2]
                // 因为前面有 f[k-1] 个元素, 所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                // 即 在 f[k-1] 的前面继续查找 k--, 即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (findVal > temp[mid]) {
                // 应该继续向数组的后面查找(右边)
                low = mid + 1;
                // 因为后面我们有 f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                // 即在 f[k-2] 的前面进行查找 k -=2, 即下次循环 mid=f[k-1-2]-1
                k -= 2;
            } else {
                // 需要确定，返回的是哪个下标
                if (mid < high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }

    @Override
    public List<Integer> findAll(int findVal) {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        int low = 0;
        int high = arr.length - 1;
        // 斐波那契分割数值的下标
        int k = 0;
        // 获取到斐波那契分割数值的下标
        while (f[k] < arr.length) {
            k++;
        }
        // 因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用 Arrays 类，构造一个新的数组，并指向 temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 实际上需求使用 a 数组最后的数填充 temp
        // 举例：temp = {1,8, 10, 89, 1000, 1234, 0, 0} => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        return findAll(low, high, k, temp, findVal);
    }

    private List<Integer> findAll(int low, int high, int k, int[] temp, int findVal) {
        int mid;
        // 使用 while 来循环处理，找到我们的数 findVal
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (findVal < temp[mid]) {
                // 应该继续向数组的前面查找(左边)
                high = mid - 1;
                // 全部元素 = 前面的元素 + 后边元素；f[k] = f[k-1] + f[k-2]
                // 因为前面有 f[k-1] 个元素, 所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                // 即 在 f[k-1] 的前面继续查找 k--, 即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (findVal > temp[mid]) {
                // 应该继续向数组的后面查找(右边)
                low = mid + 1;
                // 因为后面我们有 f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                // 即在 f[k-2] 的前面进行查找 k -=2, 即下次循环 mid=f[k-1-2]-1
                k -= 2;
            } else {
                // 需要确定，返回的是哪个下标
                if (mid < high) {
                    results.add(mid);
                    int index = mid - 1;
                    while (index >= 0 && arr[index] == findVal) {
                        results.add(index);
                        // 左移
                        index--;
                    }
                    index = mid + 1;
                    while (index < arr.length && arr[index] == findVal) {
                        results.add(index);
                        // 右移
                        index++;
                    }
                } else {
                    results.add(high);
                }
                break;
            }
        }

        return results;
    }

    /**
     * 因为后面我们 mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
     * 非递归方法得到一个斐波那契数列
     */
    private static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
