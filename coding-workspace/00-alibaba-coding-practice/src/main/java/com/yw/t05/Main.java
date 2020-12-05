package com.yw.t05;

/**
 * @author yangwei
 * @date 2020-04-30 14:45
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().work(3, 6, 7, 5));
        System.out.println(new Solution().work(4, 3, 9, 6));
    }
}
class Solution {
    /**
     * @param n     int整型
     * @param seed1 long长整型
     * @param seed2 long长整型
     * @param seed3 long长整型
     * @return long长整型
     */
    public long work(int n, long seed1, long seed2, long seed3) {
        // write code here
        long res = 0;
        for (int i = 1; i < n; i++) {
            long seed4 = ((seed1 + seed2)) * seed3;
            res += seed2 * seed3;
            seed3 = seed2;
            seed2 = seed1;
            seed1 = seed4;
        }
        return res;
    }
}
