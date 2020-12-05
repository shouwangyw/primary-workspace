package com.yw.t06;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-04-30 16:58
 */
public class Main {
    public static void main(String[] args) {
        int[] param = new int[]{4, 4};
        Point[] edge = new Point[]{new Point(1, 2), new Point(2, 3), new Point(3, 4), new Point(4, 1)};
        System.out.println(new Solution().solve(param, edge));
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    /**
     * 能回到1号点返回 Yes，否则返回 No
     *
     * @param param int整型一维数组 param[0] 为 n，param[1] 为 m
     * @param edge Point类一维数组 Point.x , Point.y 分别为一条边的两个点
     * @return string字符串
     */
    private boolean[] marked;
    private boolean hasCycle = false;

    /**
     * 能回到1号点返回 Yes，否则返回 No
     *
     * @param param int整型一维数组 param[0] 为 n，param[1] 为 m
     * @param edge  Point类一维数组 Point.x , Point.y 分别为一条边的两个点
     * @return string字符串
     */
    public String solve(int[] param, Point[] edge) {
        int n = param[0];
        int m = param[1];

        List<Integer>[] adj = new List[n + 1];
        for (int v = 0; v <= n; v++) {
            adj[v] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int u = edge[i].x;
            int v = edge[i].y;

            adj[u].add(v);
            adj[v].add(u);
        }

        marked = new boolean[n + 1];
        dfs(adj, 1, 1);

        if (hasCycle) {
            return "Yes";
        } else {
            return "No";
        }

    }

    void dfs(List<Integer>[] adj, int v, int u) {
        marked[v] = true;
        for (int w : adj[v]) {
            if (!marked[w]) {
                dfs(adj, w, v);
            } else if (w != u) {
                hasCycle = true;
                return;
            }
        }

    }
}
