package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-07-27 18:01
 */
public class Solution3 {
    public static void main(String[] args) {
        Solution3 s3 = new Solution3();
        System.out.println(s3.compressString("aabcccccaaa"));
    }

    public String compressString(String S) {
        if (S == null || S.length() <= 2) {
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char c0 = S.charAt(0);
        for (char c : S.toCharArray()) {
            if (c == c0) {
                count++;
            } else {
                sb.append(c0).append(count);
                c0 = c;
                count = 1;
            }
        }
        String res = sb.append(c0).append(3).toString();
        return res.length() < S.length() ? res : S;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(nums[k]);
                    result.add(res);
                    j++;
                    k--;
                    while (j < nums.length && nums[j] == nums[j - 1]) j++;
                    while (k > j && nums[k] == nums[k + 1]) k--;
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);

        int len = cs1.length;
        for (int i = 0; i < len; i++) {
            if (cs1[i] != cs2[i]) {
                return false;
            }
        }
        return true;
    }
}
