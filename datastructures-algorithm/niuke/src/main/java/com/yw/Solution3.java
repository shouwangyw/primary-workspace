package com.yw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * *c*m*b*n*t*
 *
 * @author yangwei
 * @date 2020-06-07 14:29
 */
public class Solution3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        int target = 24;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (list.contains(target - list.get(i))) {
                answer++;
            }
        }
        System.out.println(answer/2);

    }
}
