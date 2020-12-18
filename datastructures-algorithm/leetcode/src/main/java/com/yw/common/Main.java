package com.yw.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangwei
 * @create 2020/12/18 10:40
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes(499979));
    }
}
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        boolean f;
        for (int i = 3; i < n; i++) {
            f = true;
            for (Integer p : primes) {
                if (i % p == 0) {
                    f = false;
                    break;
                }
            }
            if (f) primes.add(i);
        }
        return primes.size();
    }
}
