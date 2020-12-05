package com.yw.alg.L05_dynamic;

/**
 * 动态规划最佳实践-背包问题
 * @author yangwei
 * @date 2020-06-13 23:07
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // 物品重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] val = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 物品的个数
        int n = val.length;
        // 为了记录存放商品的情况，
        int[][] path = new int[n + 1][m + 1];
        // 创建二维数组
        // v[i][j]: 表示在前i个物品中，能够转入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        // 1、初始化第一行、第一列，在本程序中可以不处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        // 2、根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // 公式
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 说明：因为i是从1开始的，因此公式需要调整成如下
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录商品存放到背包的情况，不能直接用上面的公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        // 输出最后我们是放入了哪些商品
        // 下面这样遍历，会输出所有放入背包的情况，但其实我们只需要最后放入的
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d个商品放入背包\n", i);
//                }
//            }
//        }
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
