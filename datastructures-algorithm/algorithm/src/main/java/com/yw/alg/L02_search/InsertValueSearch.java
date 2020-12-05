package com.alg.L02_search;

import java.util.Collections;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-05-18 22:10
 */
public class InsertValueSearch extends AbstractSearch {
    public InsertValueSearch() {
        super("插值查找");
    }

    @Override
    public int findOne(int findVal) {
        if (isEmpty()) {
            return -1;
        }
        return findOne(0, arr.length - 1, findVal);
    }

    public int findOne(int left, int right, int findVal) {
        // 注意:findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
        // 否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return findOne(mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return findOne(left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    @Override
    public List<Integer> findAll(int findVal) {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        return findAll(0, arr.length - 1, findVal);
    }

    public List<Integer> findAll(int left, int right, int findVal) {
        // 注意:findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
        // 否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return Collections.emptyList();
        }
        // 求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return findAll(mid + 1, right, findVal);
        } else if (findVal < midVal) {
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
