package com.yw;

import java.util.*;

/**
 * 5
 * 1 1 2 22 23
 *
 * @author yangwei
 * @date 2020-06-07 14:29
 */
public class Solution2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int target = 24;
        int answer = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i != j && (nums[i] + nums[j] == target)) {
//                    answer++;
//                }
//            }
//        }
//        System.out.println(answer/2);

        Map<Integer, Integer> map = new HashMap<>(2);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                list.add(map.get(target - nums[i]) + "," + i);
            }
            if (map.containsKey(nums[i])) {

            }
            map.put(nums[i], i);
        }
        System.out.println(list);
    }
}
