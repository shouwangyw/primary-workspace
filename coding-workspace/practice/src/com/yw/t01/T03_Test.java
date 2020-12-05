package com.yw.t01;

import java.util.*;

/**
 * @author yangwei
 * @date 2020-07-06 23:11
 */
public class T03_Test {
    public static void main(String[] args) {
//        System.out.println(new Solution().romanToInt("III"));
//        System.out.println(new Solution().romanToInt("IV"));
//        System.out.println(new Solution().romanToInt("MCMXCIV"));

//        String[] strs = {"flower","flow","flight"};
//        String[] strs = {};
//        System.out.println(new Solution().longestCommonPrefix(strs));

//        System.out.println(new Solution().isValid("]"));
        System.out.println(new Solution().isValid("([)]"));
        System.out.println(new Solution().isValid("{[]}"));
    }
}

class Solution {
//    private static final Map<Character, Integer> ROMAN_MAP = new HashMap<>(8);
//
//    static {
//        ROMAN_MAP.put(null, 0);
//        ROMAN_MAP.put('I', 1);
//        ROMAN_MAP.put('V', 5);
//        ROMAN_MAP.put('X', 10);
//        ROMAN_MAP.put('L', 50);
//        ROMAN_MAP.put('C', 100);
//        ROMAN_MAP.put('D', 500);
//        ROMAN_MAP.put('M', 1000);
//    }
//
//    public int romanToInt(String s) {
//        if (s == null) {
//            return 0;
//        }
//        int result = 0;
//        for (int i = 0; i < s.length() - 1; i++) {
//            Integer m = ROMAN_MAP.get(s.charAt(i));
//            Integer n = ROMAN_MAP.get(s.charAt(i + 1));
//            if (m < n) {
//                result -= m;
//            } else {
//                result += m;
//            }
//        }
//        return result + ROMAN_MAP.get(s.charAt(s.length() - 1));
//    }

//    public String longestCommonPrefix(String[] strs) {
//        if (strs == null || strs.length == 0) {
//            return "";
//        }
//        int min = Integer.MAX_VALUE;
//        for (String str : strs) {
//            if (str.length() < min) {
//                min = str.length();
//            }
//        }
//
//        Set<Character> set = new HashSet<>(strs.length);
//        int i = 0;
//        while (i < min) {
//            for (String str : strs) {
//                set.add(str.charAt(i));
//            }
//            if (set.size() > 1) {
//                break;
//            } else {
//                i++;
//                set.clear();
//            }
//        }
//        return strs[0].substring(0, i);
//    }

    private static final Map<Character, Character> MAP = new HashMap<>(4);
    static {
        MAP.put(')', '(');
        MAP.put('}', '{');
        MAP.put(']', '[');
    }
    public boolean isValid(String s) {
        if (s == null || s.length() == 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character mc = MAP.get(c);
            if (stack.contains(mc) && stack.peek().equals(mc)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
