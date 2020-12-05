package com.yw.t01;

/**
 * @author yangwei
 * @date 2020-06-09 23:10
 */
public class T02_Test {
    public static void main(String[] args) {
        T02_Test t = new T02_Test();
        System.out.println(t.convert("LEETCODEISHIRING", 4));
    }

    public String convert(String s, int numRows) {
        if (s.length() <= numRows) {
            return s;
        }
        return "";
    }
}
