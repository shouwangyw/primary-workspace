package com.alg;

import com.alg.L02_search.AbstractSearch;
import com.alg.L02_search.FibonacciSearch;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-05-17 21:09
 */
public class T02SearchTest {
    private static int result;
    private static List<Integer> results;
    private static final String NOT_FOUND = "Not Found";
    private static final String FOUNDED = "Found:";

    public static void main(String[] args) {
//        AbstractSearch seqSearch = new SeqSearch().setArr(1, 8, 10, 89, 89, 1000, 1234);
//        result = seqSearch.findOne(11);
//        System.out.println("顺序查找：" + printResult());
//        result = seqSearch.findOne(89);
//        System.out.println("顺序查找：" + printResult());
//        results = seqSearch.findAll(89);
//        System.out.println("顺序查找：" + results);
//
//        AbstractSearch binarySearch = new BinarySearch().setArr(1, 8, 10, 89, 100, 100, 100, 1234);
//        result = binarySearch.findOne(10);
//        System.out.println("二分查找：" + printResult());
//        result = binarySearch.findOne(88);
//        System.out.println("二分查找：" + printResult());
//        results = binarySearch.findAll(100);
//        System.out.println("二分查找：" + results);

//        AbstractSearch insertValueSearch = new InsertValueSearch().setArr(1, 8, 10, 89, 100, 100, 100, 1234);
//        result = insertValueSearch.findOne(10);
//        System.out.println("插值查找：" + printResult());
//        result = insertValueSearch.findOne(88);
//        System.out.println("插值查找：" + printResult());
//        results = insertValueSearch.findAll(100);
//        System.out.println("插值查找：" + results);

        AbstractSearch fibonacciSearch = new FibonacciSearch().setArr(1, 8, 10, 89, 1000, 1000, 1234);
        result = fibonacciSearch.findOne(10);
        System.out.println("斐波那契查找：" + printResult());
        result = fibonacciSearch.findOne(88);
        System.out.println("斐波那契查找：" + printResult());
        results = fibonacciSearch.findAll(1000);
        System.out.println("斐波那契查找：" + results);
    }

    private static String printResult() {
        return result == -1 ? NOT_FOUND : FOUNDED + result;
    }
}
