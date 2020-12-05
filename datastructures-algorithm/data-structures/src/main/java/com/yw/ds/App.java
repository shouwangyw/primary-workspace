package com.yw.ds;

import java.util.HashSet;
import java.util.Set;

/**
 * LCIRETOESIIGEDHN
 * LCI E O S   EDH
 * LCIEOTSEEDH
 * @author yangwei
 * @date 2020-05-13 11:00
 */
public class App {
    public static void main(String[] args) {
        System.out.println(new App().getColumnNum(16, 3));
//        System.out.println(new App().convert("LEETCODEISHIRING", 3));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int sLen = s.length();
        int numColumns = getColumnNum(sLen, numRows);

        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int k1 = 2 * (numRows - 1) * ((j + 1) / numRows) + i;
                int k2 = k1 - 2 * i;
                if (k1 < sLen && !set.contains(k1)) {
                    set.add(k1);
                    sb.append(s.charAt(k1));
                }
                if (k2 > 0 && k2 < sLen && !set.contains(k2)) {
                    set.add(k2);
                    sb.append(s.charAt(k2));
                }
            }
        }
        return sb.toString();
    }

    private int getColumnNum(int sLen, int numRows) {
        if (sLen <= numRows) {
            return 1;
        }
        int m = sLen / numRows;
        int n = sLen % numRows;
        if (n == 0 && m * numRows == sLen) {
            n = numRows - 2;
        }
        return m + n;
    }
}
