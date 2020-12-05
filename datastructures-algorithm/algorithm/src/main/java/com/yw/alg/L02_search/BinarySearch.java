package com.yw.alg.L02_search;

import java.util.Collections;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-05-17 21:45
 */
public class BinarySearch extends AbstractSearch {
    public BinarySearch() {
        super("二分查找");
    }

    @Override
    public int findOne(int findVal) {
        if (isEmpty()) {
            return -1;
        }
        return findOne(0, arr.length - 1, findVal);
    }

    private int findOne(int left, int right, int findVal) {
        // left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 向右递归
            return findOne(mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return findOne(left, mid - 1, findVal);
        } else {
            // 找到了
            return mid;
        }
    }

    @Override
    public List<Integer> findAll(int findVal) {
        return findAll(0, arr.length - 1, findVal);
    }

    private List<Integer> findAll(int left, int right, int findVal) {
        // left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return Collections.emptyList();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 向右递归
            return findAll(mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return findAll(left, mid - 1, findVal);
        } else {
            // 找到了
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
            return results;
        }
    }
}
