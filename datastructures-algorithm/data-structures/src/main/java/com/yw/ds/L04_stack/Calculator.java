package com.ds.L04_stack;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author yangwei
 * @date 2020-05-10 22:51
 */
public abstract class Calculator {
    protected static final String ADD = "+";
    protected static final String SUB = "-";
    protected static final String MUL = "*";
    protected static final String DIV = "/";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";
    private static final int PRIORITY_ZERO = 0;
    private static final int PRIORITY_LOW = 1;
    private static final int PRIORITY_HIGH = 2;

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入一个中缀表达式：");
        String infixExp = (sc.nextLine()).trim();
        String suffixExp = parseSuffixExp(infixExp);
        System.out.printf("对应的后缀表达式：%s\n", suffixExp);
        System.out.printf("后缀表达式计算结果：%s\n", calculate(suffixExp));
    }

    /**
     * 解析中缀表达式为后缀表达式
     */
    private String parseSuffixExp(String infixExp) {
        if (infixExp == null || "".equals(infixExp)) {
            return infixExp;
        }
        // 定义一个栈用于存放运算符
        Stack<String> stack = new Stack<>();
        // 定义一个StringBuffer用于拼接后缀表达式
        StringBuffer sb = new StringBuffer();

        String[] elements = infixExp.split(" ");
        for (String ele : elements) {
            parseElement(ele, stack, sb);
        }
        // 栈中剩余的操作数依次出栈，并进行结果拼接
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString();
    }

    private void parseElement(String ele, Stack<String> stack, StringBuffer sb) {
        if (isNumber(ele)) {
            // 如果是数字，直接sb拼接（多位数也一样）
            sb.append(ele).append(" ");
        } else if (isSymbol(ele)) {
            if (stack.isEmpty()) {
                // 如果栈空，则运算符直接入栈
                stack.push(ele);
            } else {
                // 否则，比较栈顶的元素运算符的优先级
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(ele)) {
                    sb.append(stack.pop()).append(" ");
                }
                // 最后还得将当前的运算符入栈
                stack.push(ele);
            }
        } else if (LEFT.equals(ele)) {
            // 如果是左括号，则直接入栈
            stack.push(ele);
        } else if (RIGHT.equals(ele)) {
            // 如果是右括号，则依次弹出栈顶的运算符，并加入到输出队列中(进行sb拼接)，直至遇到"("
            while (!stack.peek().equals(LEFT)) {
                sb.append(stack.pop()).append(" ");
            }
            // 最后消除左括号，将"("弹出
            stack.pop();
        } else {
            throw new RuntimeException("未知符号：" + ele);
        }
    }
    /**
     * 根据后缀表达式计算
     */
    private String calculate(String suffixExp) {
        if (suffixExp == null || "".equals(suffixExp)) {
            throw new RuntimeException("运算表达式空~~");
        }
        // 创建一个栈，用于后缀表达式的计算
        Stack<String> stack = new Stack<>();
        // 按空格拆分表达式
        String[] elements = suffixExp.split(" ");
        // 遍历
        for (String ele : elements) {
            // 如果是数字，则直接入栈
            if (isNumber(ele)) {
                stack.push(ele);
            } else {
                // 否则，弹出两个数，进行运算后，结果再入栈
                String pop2 = stack.pop();
                String pop1 = stack.pop();
                stack.push(doCalculate(pop1, pop2, ele));
            }
        }
        // 最后留在 stack 中的数据就是运算结果
        return stack.pop();
    }

    /**
     * 具体计算
     */
    public abstract String  doCalculate(String ele1, String ele2, String symbol);

    /**
     * 判断是否为整数
     */
    protected boolean isNumber(String ele) {
        return ele.matches("^[-\\+]?[.\\d]+$");
    }
    /**
     * 判断是否为（+-*\）运算符
     */
    protected boolean isSymbol(String ele) {
        return Arrays.asList(ADD, SUB, MUL, DIV).contains(ele);
    }
    /**
     * 获取运算符优先级
     */
    protected int getPriority(String symbol) {
        if (ADD.equals(symbol) || SUB.equals(symbol)) {
            return PRIORITY_LOW;
        }
        if (MUL.equals(symbol) || DIV.equals(symbol)) {
            return PRIORITY_HIGH;
        }
        return PRIORITY_ZERO;
    }
}
