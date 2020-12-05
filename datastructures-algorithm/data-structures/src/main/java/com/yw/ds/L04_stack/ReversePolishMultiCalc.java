package com.yw.ds.L04_stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author yangwei
 */
public class ReversePolishMultiCalc {
    private static final String LEFT = "(";
    private static final String RIGHT = ")";
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";

    /**
     * 加減 + -
     */
    private static final int LEVEL_01 = 1;
    /**
     * 乘除 * /
     */
    private static final int LEVEL_02 = 2;
    /**
     * 括号
     */
    private static final int LEVEL_HIGH = Integer.MAX_VALUE;

    public static void main(String[] args) {
        doCalc(doMatch("9+(3-1)*3+10/2"));
        doCalc(doMatch("12.8 + (2 - 3.55)*4+10/5.0"));
    }

    /**
     * 去除所有空白符
     */
    private static String replaceAllBlank(String s) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\s+", "");
    }

    /**
     * 判断是不是数字 int double long float
     */
    private static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(s).matches();
    }

    /**
     * 判断是不是运算符
     */
    private static boolean isSymbol(String s) {
        return s.matches("\\+|-|\\*|/|\\(|\\)");
    }

    /**
     * 匹配运算等级
     */
    private static int calcLevel(String s) {
        if (ADD.equals(s) || SUB.equals(s)) {
            return LEVEL_01;
        } else if (MUL.equals(s) || DIV.equals(s)) {
            return LEVEL_02;
        }
        return LEVEL_HIGH;
    }

    /**
     * 匹配
     */
    private static List<String> doMatch(String s) {
        if (s == null || "".equals(s.trim())) {
            throw new RuntimeException("data is empty");
        }
        if (!isNumber(s.charAt(0) + "")) {
            throw new RuntimeException("data illeagle,start not with a number");
        }

        s = replaceAllBlank(s);

        String each;
        int start = 0;

        List<String> data = new ArrayList();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (isSymbol(s.charAt(i) + "")) {
                each = s.charAt(i) + "";
                //栈为空，(操作符，或者 操作符优先级大于栈顶优先级 && 操作符优先级不是( )的优先级 及是 ) 不能直接入栈
                if (stack.isEmpty() || LEFT.equals(each)
                        || ((calcLevel(each) > calcLevel(stack.peek())) && calcLevel(each) < LEVEL_HIGH)) {
                    stack.push(each);
                } else if (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())) {
                    //栈非空，操作符优先级小于等于栈顶优先级时出栈入列，直到栈为空，或者遇到了(，最后操作符入栈
                    while (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())) {
                        if (calcLevel(stack.peek()) == LEVEL_HIGH) {
                            break;
                        }
                        data.add(stack.pop());
                    }
                    stack.push(each);
                } else if (RIGHT.equals(each)) {
                    // ) 操作符，依次出栈入列直到空栈或者遇到了第一个)操作符，此时)出栈
                    while (!stack.isEmpty() && LEVEL_HIGH >= calcLevel(stack.peek())) {
                        if (LEVEL_HIGH == calcLevel(stack.peek())) {
                            stack.pop();
                            break;
                        }
                        data.add(stack.pop());
                    }
                }
                //前一个运算符的位置
                start = i;
            } else if (i == s.length() - 1 || isSymbol(s.charAt(i + 1) + "")) {
                each = start == 0 ? s.substring(start, i + 1) : s.substring(start + 1, i + 1);
                if (isNumber(each)) {
                    data.add(each);
                    continue;
                }
                throw new RuntimeException("data not match number");
            }
        }
        //如果栈里还有元素，此时元素需要依次出栈入列，可以想象栈里剩下栈顶为/，栈底为+，应该依次出栈入列，可以直接翻转整个stack 添加到队列
        Collections.reverse(stack);
        data.addAll(new ArrayList<>(stack));

        System.out.printf("后缀表达式：%s\n", data);
        return data;
    }

    /**
     * 算出结果
     */
    private static Double doCalc(List<String> list) {
        Double d = 0d;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            System.out.println(list);
            d = Double.valueOf(list.get(0));
            return d;
        }
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i));
            if (isSymbol(list.get(i))) {
                Double d1 = doTheMath(list.get(i - 2), list.get(i - 1), list.get(i));
                list1.remove(i);
                list1.remove(i - 1);
                list1.set(i - 2, d1 + "");
                list1.addAll(list.subList(i + 1, list.size()));
                break;
            }
        }
        doCalc(list1);
        return d;
    }

    /**
     * 运算
     */
    private static Double doTheMath(String s1, String s2, String symbol) {
        Double result;
        switch (symbol) {
            case ADD:
                result = Double.valueOf(s1) + Double.valueOf(s2);
                break;
            case SUB:
                result = Double.valueOf(s1) - Double.valueOf(s2);
                break;
            case MUL:
                result = Double.valueOf(s1) * Double.valueOf(s2);
                break;
            case DIV:
                result = Double.valueOf(s1) / Double.valueOf(s2);
                break;
            default:
                result = null;
        }
        return result;

    }
}
