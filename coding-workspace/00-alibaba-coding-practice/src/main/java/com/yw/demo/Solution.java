package com.yw.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by hp on 2019/5/6.
 */
public class Solution {
    public static void main(String[] args){
        randomPrint(10000);
//        randomPrint(1, 10000, 10000, new HashSet<Integer>());
    }
    public static void randomPrint(int min, int max, int n, HashSet set){
        if(n>(max-min+1) || max<min){
            return;
        }
        for(int i=0;i<n;i++){
            int num = (int)(Math.random()*(max-min))+min;
            System.out.println(num);
            set.add(num);
        }
        int size = set.size();
        if(size<n){
            randomPrint(min, max, n-size, set);
        }
    }
    public static int[] randomPrint(int num) {
        Random rd = new Random();
        int[] rds = new int[num];
        List<Integer> lst = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < num; i++) {
            lst.add(i);
        }
        for (int i = 0; i < num; i++) {
            index = rd.nextInt(num - i);
            rds[i] = lst.get(index);
            System.out.println(rds[i]);
            lst.remove(index);
        }
        return rds;
    }
}
