package com.yw.alg.L11_floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法最佳实践-求图的各个顶点的到其它顶点的最短路径问题
 *
 * @author yangwei
 * @date 2020-06-14 23:09
 */
public class FloydCase {
    /**
     * 定义图
     */
    static class Graph {
        /**
         * 顶点数组
         */
        char[] vertices;
        /**
         * 记录各个顶点出发到其它各个顶点的距离，最后的结果也是保留在该数组中
         */
        int[][] dis;
        /**
         * 保存到达目标顶点的前驱顶点
         */
        int[][] pre;

        Graph(char[] vertices, int[][] matrix) {
            this.vertices = vertices;
            this.dis = matrix;
            int len = vertices.length;
            this.pre = new int[len][len];
            // 对数组pre初始化
            for (int i = 0; i < len; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        /**
         * 显示pre和dis
         */
        void show() {
            for (int k = 0; k < dis.length; k++) {
                // 输出pre的一行数据
                for (int i = 0; i < dis.length; i++) {
                    System.out.printf("%8s\t", vertices[pre[k][i]]);
                }
                System.out.println();
                // 输出dis的一行数据
                for (int i = 0; i < dis.length; i++) {
                    System.out.printf("%8s\t", String.format("%s->%s(%s)", vertices[k], vertices[i], dis[k][i] == N ? "N" : dis[k][i]));
                }
                System.out.println();
                System.out.println();
            }
        }

        /**
         * 弗洛伊德算法实现
         */
        void floyd() {
            // 保存距离
            int len = 0;
            // 对中间顶点进行遍历，k就是中间顶点的索引下标
            for (int k = 0; k < dis.length; k++) {
                // 从i顶点出发[A, B, C, D, E, F, G]
                for (int i = 0; i < dis.length; i++) {
                    for (int j = 0; j < dis.length; j++) {
                        len = dis[i][k] + dis[k][j];
                        if (len < dis[i][j]) {
                            // 更新距离
                            dis[i][j] = len;
                            // 更新前驱结点
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
            System.out.println("==>> 弗洛伊德算法求图的各个顶点的到其它顶点的最短路径输出：");
            show();
        }
    }

    private static final int N = 65535;

    public static void main(String[] args) {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0},
        };
        Graph graph = new Graph(vertices, matrix);
        System.out.println("初始情况：");
        graph.show();

        graph.floyd();
    }
}
