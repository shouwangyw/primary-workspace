package com.alg.L02_search;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-05-17 21:35
 */
public class SeqSearch extends AbstractSearch {
    public SeqSearch() {
        super("顺序查找");
    }

    @Override
    public int findOne(int findVal) {
        // 线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findVal) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Integer> findAll(int findVal) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findVal) {
                results.add(i);
            }
        }
        return results;
    }
}
