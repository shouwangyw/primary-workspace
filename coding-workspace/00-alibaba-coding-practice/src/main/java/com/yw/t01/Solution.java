package com.yw.t01;

import java.util.Scanner;

/**
 * Created by hp on 2019/5/6.
 */
// 定义一个坐标类
class Point {
    // (x,y)坐标
    int x, y;
    // 判断是否遍历过的标志变量
    boolean isVisited;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        // 初始化为false，若遍历过则置为true
        this.isVisited = false;
    }

    // 获得该点到指定点的距离
    public int getLength(Point p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }
}

public class Solution {
    // 起始点
    static Point startPoint = new Point(0, 0);
    // 最小路径先设为系统最大值
    static int MIN_PATH = Integer.MAX_VALUE;

    /**
     * 定义获取给定点到其余点的最小路径的方法，递归调用
     *
     * @param p        给定的起始点
     * @param points   其余的点
     * @param totalLen 起始点到遍历点的距离和
     * @param count    用来对遍历过的点计数
     * @return
     */
    public static int calculate(Point p, Point[] points, int totalLen, int count) {
        // 判断停止条件,如果所有的点遍历完，则返回
        if (points.length == count) {
            MIN_PATH = Math.min(MIN_PATH, totalLen + p.getLength(startPoint));
            return MIN_PATH;
        }
        // 否则遍历其余的点并进行路径和的计算
        for (int i = 0; i < points.length; i++) {
            // 判断此点是否遍历过
            if (!points[i].isVisited) {
                // 计算起始点到遍历点之间的距离，从而更新最小路径
                totalLen += points[i].getLength(p);
                // 若小于最小路径，则更遍历此点继续下一步
                if (totalLen < MIN_PATH) {
                    // 该点的标志位置为true
                    points[i].isVisited = true;
                    // 每遍历一个点，计数器加1，起始点更改为遍历后的点,继续计算其余点
                    calculate(points[i], points, totalLen, count + 1);
                }
                // 将路径和倒减，标志置为false，进行下一个方案的计算
                totalLen -= points[i].getLength(p);
                points[i].isVisited = false;
            }
        }
        return MIN_PATH;
    }

    public static void main(String[] args) {
        int totalLen = 0, count = 0;
        //从键盘获取输入
        Scanner input = new Scanner(System.in);
        int num = Integer.parseInt(input.nextLine());
        int n = num;
        Point[] points = new Point[n];
        //获得输入点的坐标并存入坐标数组中
        for (int i = 0; i < points.length; i++) {
            String[] strings = input.nextLine().trim().split(",");
            points[i] = new Point(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }
        // 计算最优路径并打印到控制台
        MIN_PATH = calculate(startPoint, points, totalLen, count);
        System.out.println(MIN_PATH);
    }
}
