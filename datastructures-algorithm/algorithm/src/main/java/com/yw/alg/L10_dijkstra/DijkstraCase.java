package com.yw.alg.L10_dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法最佳实践-求图中某一个顶点的到其它顶点的最短路径问题
 *
 * @author yangwei
 * @date 2020-06-14 21:44
 */
public class DijkstraCase {
    /**
     * 定义图
     */
    static class Graph {
        char[] vertices;
        int[][] matrix;
        VisitedVertex vv;

        Graph(char[] vertices, int[][] matrix) {
            this.vertices = vertices;
            this.matrix = matrix;
        }

        void show() {
            System.out.println("邻接矩阵：");
            for (int[] arr : matrix) {
                for (int a : arr) {
                    System.out.printf("%12d\t", a);
                }
                System.out.println();
            }
        }

        /**
         * 迪杰斯特拉算法实现
         *
         * @param index 出发顶点的索引下标
         */
        void dsj(int index) {
            System.out.printf("==>> 从顶点%s出发到各个顶点的最短路径情况：\n", vertices[index]);
            vv = new VisitedVertex(vertices.length, index);
            // 更新 index 顶点到周围顶点的距离和前驱顶点
            update(index);
            for (int j = 1; j < vertices.length; j++) {
                // 选择并返回新的访问顶点
                index = vv.updateArr();
                update(index);
            }

            // 显示结果
            vv.show();
            // 最短距离
            int count = 0;
            for (int i : vv.dis) {
                if (i != M) {
                    System.out.print(vertices[count] + "(" + i + ")");
                } else {
                    System.out.println("N ");
                }
                count++;
            }
            System.out.println();
        }

        /**
         * 更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
         */
        void update(int index) {
            int len;
            for (int j = 0; j < matrix[index].length; j++) {
                // len含义：出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
                len = vv.getDis(index) + matrix[index][j];
                // 如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
                if (!vv.in(j) && len < vv.getDis(j)) {
                    // 更新 j 顶点的前驱为 index 顶点
                    vv.updatePre(j, index);
                    // 更新出发顶点到 j 顶点的距离
                    vv.updateDis(j, len);
                }
            }
        }
    }

    static class VisitedVertex {
        /**
         * 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
         */
        int[] alreadyArr;
        /**
         * 每个下标对应的值为前一个顶点下标, 会动态更新
         */
        int[] preVisited;
        /**
         * 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求
         * 的最短距离就会存放到 dis
         */
        int[] dis;

        /**
         * @param length 表示顶点的个数
         * @param index  出发顶点对应的下标，比如G，下标为6
         */
        VisitedVertex(int length, int index) {
            this.alreadyArr = new int[length];
            this.preVisited = new int[length];
            this.dis = new int[length];
            // 初始化dis
            Arrays.fill(dis, M);
            // 设置出发顶点被访问
            this.alreadyArr[index] = 1;
            // 设置出发顶点的访问距离为0
            this.dis[index] = 0;
        }

        /**
         * 判断index顶点是否被访问过
         */
        boolean in(int index) {
            return alreadyArr[index] == 1;
        }

        /**
         * 更新出发顶点到index顶点的距离
         */
        void updateDis(int index, int len) {
            dis[index] = len;
        }

        /**
         * 更新pre这个顶点的前驱顶点为index顶点
         */
        void updatePre(int pre, int index) {
            preVisited[pre] = index;
        }

        /**
         * 返回出发顶点到index的距离
         */
        int getDis(int index) {
            return dis[index];
        }

        /**
         * 继续选择并返回新的访问顶点，比如这里的G完成后，就是A作为新的访问顶点(不是出发顶点)
         */
        int updateArr() {
            int min = M, index = 0;
            for (int i = 0; i < alreadyArr.length; i++) {
                if (alreadyArr[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    index = i;
                }
            }
            // 更新index顶点被访问
            alreadyArr[index] = 1;
            return index;
        }

        /**
         * 显示访问结果，即三个数组的情况
         */
        void show() {
            System.out.println("alreadyArr = " + Arrays.toString(alreadyArr));
            System.out.println("preVisited = " + Arrays.toString(preVisited));
            System.out.println("dis = " + Arrays.toString(dis));
        }
    }

    private static final int M = 65535;

    public static void main(String[] args) {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {M, 5, 7, M, M, M, 2},
                {5, M, M, 9, M, M, 3},
                {7, M, M, M, 8, M, M},
                {M, 9, M, M, M, 4, M},
                {M, M, 8, M, M, 5, 4},
                {M, M, M, 4, 5, M, 6},
                {2, 3, M, M, 4, 6, M},
        };
        // 创建图对象
        Graph graph = new Graph(vertices, matrix);
        // 打印图
        graph.show();

        graph.dsj(6);
        graph.dsj(2);
    }
}
