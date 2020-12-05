package com.ds.L08_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-06-13 16:24
 */
public class GraphDemo {
    static class Graph {
        /**
         * 存储顶点的集合
         */
        private List<String> vertices;
        /**
         * 存储边
         */
        private int[][] matrix;
        /**
         * 边的个数
         */
        private int edgeCount;
        /**
         * 定义一个数组boolen[]，记录某个结点是否被访问
         */
        private boolean[] isVisited;

        Graph(int n) {
            // 初始化
            matrix = new int[n][n];
            vertices = new ArrayList<>(n);
            edgeCount = 0;
            isVisited = new boolean[n];
        }

        /**
         * 添加顶点
         */
        void addVertex(String vertex) {
            vertices.add(vertex);
        }

        /**
         * 添加边
         */
        void addEdge(int v1, int v2, int weight) {
            matrix[v1][v2] = weight;
            matrix[v2][v1] = weight;
            edgeCount++;
        }

        /**
         * 返回结点的个数
         */
        int getVertexCount() {
            return vertices.size();
        }

        /**
         * 返回边的个数
         */
        int getEdgeCount() {
            return edgeCount;
        }

        /**
         * 返回结点i(下标)对应的数据
         */
        String getValueByIndex(int i) {
            return vertices.get(i);
        }

        /**
         * 返回v1和v2的权值
         */
        int getWeight(int v1, int v2) {
            return matrix[v1][v2];
        }

        /**
         * 显示图对应的邻接矩阵
         */
        void show() {
            for (int[] arr : matrix) {
                System.out.println(Arrays.toString(arr));
            }
        }
        /**
         * 得到第一个邻接结点的下标 w
         */
        int getFirstNeighbor(int index) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[index][j] > 0) {
                    return j;
                }
            }
            return -1;
        }
        /**
         * 根据前一个邻接结点的下标来获取下一个邻接结点
         */
        int getNextNeighbor(int v1, int v2) {
            for (int j = v2 + 1; j < vertices.size(); j++) {
                if (matrix[v1][j] > 0) {
                    return j;
                }
            }
            return -1;
        }
        /**
         * 清空访问记录
         */
        void clearVisited() {
            for (int i = 0; i < isVisited.length; i++) {
                isVisited[i] = false;
            }
        }
        /**
         * 对dfs进行一个重载，遍历我们所有的结点，并进行dfs
         */
        void dfs() {
            // 遍历所有的结点，进行dfs【回溯】
            for (int i = 0; i < getVertexCount(); i++) {
                if (!isVisited[i]) {
                    dfs(isVisited, i);
                }
            }
            // 遍历结束后把isVisited中的记录清空、便于第二次遍历
            clearVisited();
            System.out.println();
        }
        /**
         * 对一个结点进行深度优先遍历
         * @param i 第一次就是0
         */
        private void dfs(boolean[] isVisited, int i) {
            // 首先访问该结点，并输出
            System.out.print(getValueByIndex(i) + " -> ");
            // 将该结点设置为已访问
            isVisited[i] = true;
            // 查找结点i的第一个邻接结点w
            int w = getFirstNeighbor(i);
            while (w != -1) {
                if (!isVisited[w]) {
                    dfs(isVisited, w);
                }
                // 如果w已经被访问
                w = getNextNeighbor(i, w);
            }
        }
        /**
         * 广度优先遍历
         */
        void bfs() {
            // 遍历所有的结点，进行bfs【回溯】
            for (int i = 0; i < getVertexCount(); i++) {
                if (!isVisited[i]) {
                    bfs(isVisited, i);
                }
            }
            // 遍历结束后把isVisited中的记录清空、便于第二次遍历
            clearVisited();
            System.out.println();
        }
        /**
         * 对一个结点进行广度优先遍历
         */
        private void bfs(boolean[] isVisited, int i) {
            // u:队列的头结点对应的下标,w:邻接结点
            int u, w;
            // 队列：记录结点访问的顺序
            LinkedList<Integer> queue = new LinkedList<>();
            // 访问结点
            System.out.print(getValueByIndex(i) + " -> ");
            isVisited[i] = true;
            // 将结点加入队列
            queue.addLast(i);
            while (!queue.isEmpty()) {
                // 取出队列的头结点下标
                u = queue.removeFirst();
                // 得到第一个邻接结点的下标
                w = getFirstNeighbor(u);
                while (w != -1) {
                    if (!isVisited[w]) {
                        System.out.print(getValueByIndex(w) + " -> ");
                        isVisited[w] = true;
                        // 入队列
                        queue.addLast(w);
                    }
                    // 以u为前驱结点，找w后面的邻接点
                    w = getNextNeighbor(u, w); // 体现广度优先
                }
            }
        }
    }

    public static void main(String[] args) {
        // 结点的个数
        int n = 5;
        // 结点
        String[] vs = {"A", "B", "C", "D", "E"};
        // 创建图
        Graph graph = new Graph(n);
        // 循环添加顶点
        for (String v : vs) {
            graph.addVertex(v);
        }
        // 添加边
        // A-B A-C B-C B-D B-E
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        // 显示
        graph.show();

        // 测试一下DFS
        System.out.println("深度优先遍历：");
        graph.dfs();
        System.out.println("广度优先遍历：");
        graph.bfs();
    }
}
