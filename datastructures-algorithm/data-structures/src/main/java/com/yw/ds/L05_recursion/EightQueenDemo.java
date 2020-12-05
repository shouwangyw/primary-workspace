package com.yw.ds.L05_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 *
 * @author yangwei
 * @date 2020-05-12 12:31
 */
public class EightQueenDemo {
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen(8);
        // 得到所有解
        List<int[]> allScheme = eightQueen.getAllScheme();
        System.out.printf("一共有 %d 种解法\n", allScheme.size());
        System.out.printf("一共判断冲突 %d 次\n", eightQueen.getConflictCount());
    }
}
class EightQueen {
    /**
     * 定义有多少个皇后
     */
    private int size;
    /**
     * 定义数组，保存皇后摆放位置的结果，如array = {0, 4, 7, 5, 2, 6, 1, 3}
     */
    private int[] array;
    /**
     * 统计有多少种解法
     */
    private List<int[]> schemes = new ArrayList<>(128);
    /**
     * 冲突次数统计
     */
    private int conflictCount = 0;

    public EightQueen(int size) {
        this.size = size;
        this.array = new int[this.size];
    }
    /**
     * 得到所有解
     */
    public List<int[]> getAllScheme() {
        settingQueen(0);
        return schemes;
    }
    /**
     * 获取冲突判断次数
     */
    public int getConflictCount() {
        return conflictCount;
    }
    /**
     * 放置第n个皇后
     * 每一次递归进入到settingQueen，都有for (int i = 0; i < size; i++)，因此会有回溯
     * @param n 表示第n个皇后
     */
    private void settingQueen(int n) {
        if (n == size) {
            // 得到一个解
            print();
            return;
        }
        // 依次放置皇后，并判断是否冲突
        for (int i = 0; i < size; i++) {
            // 先把当前的皇后n，放置到该行的第i列
            array[n] = i;
            if (!isConflict(n)) {
                // 不冲突，则接着放n+1个皇后
                settingQueen(n + 1);
            }
            // 如果冲突，就继续执行array[n] = i
            // 即将第n个皇后，放置到本行的 后移的一个位置
        }
    }
    /**
     * 当放置第n个皇后时，就去检测该皇后和前面已摆放的皇后是否冲突
     * @param n 表示第n个皇后
     * @return true 表示冲突 false 表示不冲突
     */
    private boolean isConflict(int n) {
        conflictCount++;
        for (int i = 0; i < n; i++) {
            // 同一列，同一行不需要判断（n==i）
            boolean isSameColumn = array[i] == array[n];
            // 同一斜线
            boolean isSameSlash = Math.abs(n - i) == Math.abs(array[n] - array[i]);
            if (isSameColumn || isSameSlash) {
                return true;
            }
        }
        return false;
    }
    /**
     * 打印皇后摆放的位置输出
     */
    private void print() {
        schemes.add(array);
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
