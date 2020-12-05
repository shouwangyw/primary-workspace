package com.yw.alg.L12_horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 马踏棋盘算法
 *
 * @author yangwei
 * @date 2020-06-14 23:52
 */
public class HorseChessboard {
    /**
     * 棋盘的行数和列数
     */
    private static int X;
    private static int Y;
    /**
     * 创建一个数组，标记棋盘的各个位置是否被访问过
     */
    private static boolean[] visited;
    /**
     * 使用一个属性，标记是否棋盘的所有位置都被访问
     * 如果为 true,表示成功
     */
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        System.out.printf("骑士周游[%d * %d]算法，开始运行~~\n", X, Y);
        // 马儿初始位置的行、列
        int row = 0, column = 0;
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        // 初始值都是 false
        visited = new boolean[X * Y];

        long startTime = System.currentTimeMillis();
        traversalChessboard(chessboard, row, column, 1);
        long endTime = System.currentTimeMillis();

        System.out.println("共耗时: " + (endTime - startTime) + " 毫秒");
        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    private static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        // 标记已访问
        visited[row * X + column] = true;
        // 获取当前位置可以走的下一个位置的集合
        List<Point> points = next(new Point(column, row));
        // 对 points 进行排序，排序的规则就是对 points 的所有的 Point 对象的下一步的位置的数目，进行非递减排序
        sort(points);
        // 遍历points
        while (points.size() > 0) {
            // 取出下一个可以走的位置
            Point p = points.remove(0);
            // 判断是否访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        // 判断马儿是否完成了任务，使用 step 和应该走的步数比较，如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        // 说明: step < X * Y 成立的情况有两种:1、棋盘到目前位置,仍然没有走完，2、棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    private static List<Point> next(Point curPoint) {
        List<Point> points = new ArrayList<>();
        Point p = new Point();
        /*0*/
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) points.add(new Point(p));
        /*1*/
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) points.add(new Point(p));
        /*2*/
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) points.add(new Point(p));
        /*3*/
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) points.add(new Point(p));
        /*4*/
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) points.add(new Point(p));
        /*5*/
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) points.add(new Point(p));
        /*6*/
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) points.add(new Point(p));
        /*7*/
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) points.add(new Point(p));
        return points;
    }

    /**
     * 根据当前这一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
     */
    private static void sort(List<Point> points) {
        points.sort((p1, p2) -> {
            //获取到 p1 的下一步的所有位置个数
            int count1 = next(p1).size();
            //获取到 p2 的下一步的所有位置个数
            int count2 = next(p2).size();
            if (count1 < count2) {
                return -1;
            } else if (count1 == count2) {
                return 0;
            } else {
                return 1;
            }
        });
    }
}
