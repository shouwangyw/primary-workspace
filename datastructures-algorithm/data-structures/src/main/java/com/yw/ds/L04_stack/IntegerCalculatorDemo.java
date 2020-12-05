package com.ds.L04_stack;

/**
 * 整数计数
 * <p>
 * 中缀表达式：4 * 5 - 8 + 60 + 8 / 2
 * 后缀表达式：4 5 * 8 - 60 + 8 2 / +
 * 结果：76
 *
 * @author yangwei
 * @date 2020-05-09 15:21
 */
public class IntegerCalculatorDemo {
    public static void main(String[] args) {
        new IntegerCalculator().play();
    }
}
class IntegerCalculator extends Calculator {

    @Override
    public String doCalculate(String ele1, String ele2, String symbol) {
        Integer num1 = Integer.parseInt(ele1);
        Integer num2 = Integer.parseInt(ele2);
        switch (symbol) {
            case ADD:
                return String.valueOf(num1 + num2);
            case SUB:
                return String.valueOf(num1 - num2);
            case MUL:
                return String.valueOf(num1 * num2);
            case DIV:
                return String.valueOf(num1 / num2);
            default:
                throw new RuntimeException("运算符有误");
        }
    }
}