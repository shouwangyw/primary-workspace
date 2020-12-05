package com.alg.L09_kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 克鲁斯卡尔算法最佳实践-最小生成树（公交站问题）
 *
 * @author yangwei
 * @date 2020-06-14 19:35
 */
public class KruskalCase {
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
        /**
         * 图的边数
         */
        int edgeCount;

        Graph(int n) {
            vertices = new char[n];
            matrix = new int[n][n];
            vertexCount = n;
        }
    }

    /**
     * 定义边
     */
    static class Edge implements Comparable<Edge> {
        /**
         * 边的起点
         */
        char fromV;
        /**
         * 边的终点
         */
        char toV;
        /**
         * 边的权值
         */
        int weight;

        Edge(char fromV, char toV, int weight) {
            this.fromV = fromV;
            this.toV = toV;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge <" + fromV + "--" + toV + ">=" + weight;
        }

        @Override
        public int compareTo(Edge o) {
            // 从小到大排序
            return this.weight - o.weight;
        }
    }

    /**
     * 定义最小生成树
     */
    static class MinTreeGraph {
        Graph graph;
        List<Edge> edges = new ArrayList<>();

        MinTreeGraph(Graph graph) {
            this.graph = graph;
        }

        MinTreeGraph createGraph(char[] vertices, int[][] matrix) {
            int vCount = graph.vertexCount;
            for (int i = 0; i < vCount; i++) {
                graph.vertices[i] = vertices[i];
                System.arraycopy(matrix[i], 0, graph.matrix[i], 0, vCount);
            }
            // 统计边
            for (int i = 0; i < vCount; i++) {
                for (int j = i + 1; j < vCount; j++) {
                    if (matrix[i][j] != MAX) {
                        edges.add(new Edge(vertices[i], vertices[j], matrix[i][j]));
                    }
                }
            }
            graph.edgeCount = edges.size();
            return this;
        }

        List<Edge> kruskal() {
            // 用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
            int[] ends = new int[graph.edgeCount];
            // 创建结果集合, 保存最后的最小生成树
            List<Edge> results = new ArrayList<>();

            // /遍历 edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 rets, 否则不能加入
            List<Edge> edges = getSortedEdges();
            for (Edge edge : edges) {
                // 获取边的两个顶点
                int formIndex = getPosition(edge.fromV);
                int toIndex = getPosition(edge.toV);
                // 分别获取这两个顶点的终点
                int fromEnd = getEndIndex(ends, formIndex);
                int toEnd = getEndIndex(ends, toIndex);
                // 是否构成回路
                if (fromEnd != toEnd) {
                    // 设置fromEnd在"已有最小生成树"中的终点
                    ends[fromEnd] = toEnd;
                    results.add(edge);
                }
            }
            return results;
        }

        List<Edge> getSortedEdges() {
            Collections.sort(edges);
            return edges;
        }

        /**
         * 对边进行排序处理
         */
        void sortEdge() {
            Collections.sort(edges);
        }

        /**
         * 返回顶点的下标
         */
        int getPosition(char c) {
            for (int i = 0; i < graph.vertices.length; i++) {
                if (graph.vertices[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
         *
         * @param ends 记录了各个顶点对应的终点是哪个
         * @param i    表示传入的顶点对应的下标
         */
        int getEndIndex(int[] ends, int i) {
            while (ends[i] != 0) {
                i = ends[i];
            }
            return i;
        }

        /**
         * 打印边
         */
        void showEdges() {
            System.out.println(edges);
        }

        /**
         * 打印邻接矩阵
         */
        void show() {
            System.out.println("邻接矩阵：");
            for (int[] arr : graph.matrix) {
                for (int a : arr) {
                    System.out.printf("%12d\t", a);
                }
                System.out.println();
            }
        }
    }

    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // 定义图中的顶点和边
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/{0, 12, MAX, MAX, MAX, 16, 14},
                /*B*/{12, 0, 10, MAX, MAX, 7, MAX},
                /*C*/{MAX, 10, 0, 3, 5, 6, MAX},
                /*D*/{MAX, MAX, 3, 0, 4, MAX, MAX},
                /*E*/{MAX, MAX, 5, 4, 0, 2, 8},
                /*F*/{16, 7, 6, MAX, 2, 0, 9},
                /*G*/{14, MAX, MAX, MAX, 8, 9, 0},
        };
        MinTreeGraph minTreeGraph = new MinTreeGraph(new Graph(vertices.length))
                .createGraph(vertices, matrix);
        minTreeGraph.show();

//        System.out.println(minTreeGraph.graph.edgeCount);
        System.out.println("排序前：");
        minTreeGraph.showEdges();
        minTreeGraph.sortEdge();
        System.out.println("排序后：");
        minTreeGraph.showEdges();

        List<Edge> edges = minTreeGraph.kruskal();
        System.out.println("克鲁斯卡尔最小生成树结果:" + edges);
    }
}
