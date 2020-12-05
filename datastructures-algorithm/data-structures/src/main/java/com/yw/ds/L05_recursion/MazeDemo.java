package com.ds.L05_recursion;

/**
 * @author yangwei
 * @date 2020-05-11 07:20
 */
public class MazeDemo {
    /**
     * 迷宫类
     * 约定：map[i][j] = 0:该点没有走过, 1:墙, 2:通路可以走, 3:已经走过但走不通
     */
    private static class Maze {
        private int[][] map;
        private static final int WALL = 1;
        private static final int ROUTE = 2;
        private static final int NO_ROUTE = 3;
        /**
         * 起点x y坐标
         */
        private int startX;
        private int startY;
        /**
         * 终点x y坐标
         */
        private int endX;
        private int endY;

        public Maze(int[][] map, int startX, int startY, int endX, int endY) {
            this.map = map;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        private void printMap() {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.printf("%d ", map[i][j]);
                }
                System.out.println();
            }
        }

        private void findWay() {
            boolean result = findWayDRUL(startX, startY);
            if (result) {
                System.out.println("找到一条出迷宫的路~~");
            } else {
                System.out.println("没有找到出迷宫的路");
            }
        }

        /**
         * 需要确定一个策略(方法) 下(D)->右(R)->上(U)->左(L) , 如果该点走不通，再回溯
         * @param x
         * @param y
         * @return
         */
        private boolean findWayDRUL(int x, int y) {
            if (map[endX][endY] == ROUTE) {
                return true;
            } else {
                if (map[x][y] == 0) {
                    // 当前这个点还没有走过
                    // 假定该点是可以走通的
                    map[x][y] = ROUTE;
                    if (findWayDRUL(x + 1, y)) { // 向下走
                        return true;
                    } else if (findWayDRUL(x, y +1)) { // 向右走
                        return true;
                    } else if (findWayDRUL(x - 1, y)) { // 向上走
                        return true;
                    } else if (findWayDRUL(x, y - 1)) { // 向左走
                        return true;
                    } else {
                        // 说明该点走不通，是死路
                        map[x][y] = NO_ROUTE;
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 定义迷宫地图
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        // 创建迷宫
        Maze maze = new Maze(map, 1, 1, 6, 5);
        // 打印地图
        System.out.println("地图的情况：");
        maze.printMap();
        System.out.println("输出新的地图的情况：");
        maze.findWay();
        maze.printMap();
    }
}
