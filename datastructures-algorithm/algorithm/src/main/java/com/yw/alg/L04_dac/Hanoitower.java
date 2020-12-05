package com.alg.L04_dac;

/**
 * 分治算法最佳实践-汉罗塔问题
 * @author yangwei
 * @date 2020-06-13 19:33
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }
    private static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            // 1、如果只有一个盘， A->C
            System.out.printf("第 %d 个盘从 %s -> %s\n", num, a, c);
        } else {
            // 2、如果我们有 n >= 2 情况，我们总是可以看做是两个盘：一个是最下边的盘，一个是上面所有的盘
            // 先把最上面的盘 A->B
            hanoiTower(num - 1, a, c, b);
            // 把最下边的盘 A->C
            System.out.printf("第 %d 个盘从 %s -> %s\n", num, a, c);
            // 把B塔的所有盘 从 B->C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
