package com.alg.L08_prim;

import java.util.Arrays;

/**
 * 普里姆算法最佳实践-修路问题
 *
 * @author yangwei
 * @date 2020-06-14 15:59
 */
public class PrimCase {
    /**
     * 定义图
     */
    static class Graph {
        /**
         * 图的顶点数据
         */
        char[] vertices;
        /**
         * 图的边，采用邻接矩阵
         */
        int[][] matrix;
        /**
         * 图的顶点数
         */
        int vertexCount;

        Graph(int n) {
            vertices = new char[n];
            matrix = new int[n][n];
            vertexCount = n;
        }
    }

    /**
     * 定义最小生成树
     */
    static class MinTreeGraph {
        Graph graph;

        MinTreeGraph(Graph graph) {
            this.graph = graph;
        }

        MinTreeGraph createGraph(char[] vertices, int[][] matrix) {
            int vCount = graph.vertexCount;
            for (int i = 0; i < vCount; i++) {
                graph.vertices[i] = vertices[i];
                System.arraycopy(matrix[i], 0, graph.matrix[i], 0, vCount);
            }
            return this;
        }

        /**
         * 编写普里姆算法，得到最小生成树
         *
         * @param v 表示从图的第几个顶点开始生成
         */
        void primTree(int v) {
            int vCount = graph.vertexCount;
            // 标记结点是否被访问，1:被访问
            int[] visited = new int[vCount];
            visited[v] = 1;
            // 定义v1、v2记录两个顶点的下标
            int v1 = -1, v2 = -1;
            // 定义一个变量，存放最小权值的边
            int minW = Integer.MAX_VALUE;
            for (int k = 1; k < vCount; k++) {
                // 这个是确定每一次生成的子图，和那个结点的距离最近
                for (int i = 0; i < vCount; i++) {
                    // i结点表示被访问过的结点，j结点表示没有访问过的结点
                    for (int j = 0; j < vCount; j++) {
                        if (visited[i] == 1 && visited[j] == 0 && graph.matrix[i][j] < minW) {
                            minW = graph.matrix[i][j];
                            v1 = i;
                            v2 = j;
                        }
                    }
                }
                // for循环结束后，就找到了一条最小的边
                System.out.printf("边 <%s, %s>，权值：%d\n", graph.vertices[v1], graph.vertices[v2], minW);
                // 将当前这个结点标记为已访问
                visited[v2] = 1;
                // 重置minW
                minW = Integer.MAX_VALUE;
            }
        }

        void show() {
            for (int[] arr : graph.matrix) {
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    private static final int M = Integer.MAX_VALUE;

    public static void main(String[] args) {

        // 定义图中的顶点和边
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {M, 5, 7, M, M, M, 2}, // A
                {5, M, M, 9, M, M, 3}, // B
                {7, M, M, M, 8, M, M}, // C
                {M, 9, M, M, M, 4, M}, // D
                {M, M, 8, M, M, 5, 4}, // E
                {M, M, M, 4, 5, M, 6}, // F
                {2, 3, M, M, 4, 6, M}, // G
        };
        MinTreeGraph minTreeGraph = new MinTreeGraph(new Graph(vertices.length))
                .createGraph(vertices, matrix);
//        minTreeGraph.show();

        minTreeGraph.primTree(0);
    }
}
