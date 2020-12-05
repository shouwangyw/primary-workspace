package com.yw.alg.L02_search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-05-17 21:54
 */
public abstract class AbstractSearch {
    /**
     * 查找算法名称
     */
    protected String searchName;
    /**
     * 待查找数组
     */
    protected int[] arr;
    /**
     * 查找到的元素的索引下标集合
     */
    protected final List<Integer> results;

    protected AbstractSearch(String searchName) {
        this.searchName = searchName;
        results = new ArrayList<>(4);
    }

    public AbstractSearch setArr(int... arr) {
        this.arr = arr;
        return this;
    }

    /**
     * 数组判空
     */
    protected boolean isEmpty() {
        return arr == null || arr.length == 0;
    }

    /**
     * 查找单个
     *
     * @param findVal 需要查找的值
     * @return 返回找到的元素的索引下标，-1表示没找到
     */
    public abstract int findOne(int findVal);

    /**
     * 查找所有
     *
     * @param findVal 需要查找的值¬
     * @return 返回所有找到的元素的索引下标的集合
     */
    public abstract List<Integer> findAll(int findVal);
}
