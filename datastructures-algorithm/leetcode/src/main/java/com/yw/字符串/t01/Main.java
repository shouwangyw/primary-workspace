package com.yw.字符串.t01;

/*
无重复字符的最长子串

 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

 示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

 示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 示例 4:
输入: s = ""
输出: 0

提示：
0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成

Related Topics 哈希表 双指针 字符串 Sliding Window
👍 4676 👎 0
*/
import org.junit.Assert;
import org.junit.Test;

public class Main {
    @Test
    public void test() {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.lengthOfLongestSubstring("abc"));
        Assert.assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(3, solution.lengthOfLongestSubstring("dvdf"));
        Assert.assertEquals(0, solution.lengthOfLongestSubstring(""));
    }
}
/*
Test Case：
"abc"
"abcabcbb"
"bbbbb"
"pwwkew"
"dvdf"
""
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        // 双指针
        int i = 0, j = 0;
        while (i <= j && j < s.length()) {
            if (s.substring(i, j).indexOf(s.charAt(j)) > -1) {
                ans = Math.max(ans, j - i);
                i++;
            } else {
                j++;
            }
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}