package com.ds.L05_recursion.test;

/**
 * @author yangwei
 * @date 2020-05-11 07:20
 */
public class RecursionTest {
    public static void main(String[] args) {
        System.out.println("打印问题");
        test(4);
        System.out.printf("阶乘计算：%d\n", factorial(4));
    }
    private static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n = " + n);
    }
    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
