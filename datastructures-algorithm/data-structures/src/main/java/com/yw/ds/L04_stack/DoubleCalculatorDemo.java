package com.ds.L04_stack;

/**
 * 计数器增强版：可以计算小数
 * <p>
 * 中缀表达式：12.8 + ( 2 - 3.55 ) * 4 + 10 / 5.0
 * 后缀表达式：4 5 * 8 - 60 + 8 2 / +
 * 结果：76
 *
 * @author yangwei
 * @date 2020-05-09 15:21
 */
public class DoubleCalculatorDemo {
    public static void main(String[] args) {
        new DoubleCalculator().play();
    }
}

class DoubleCalculator extends Calculator {
    @Override
    public String doCalculate(String ele1, String ele2, String symbol) {
        Double num1 = Double.parseDouble(ele1);
        Double num2 = Double.parseDouble(ele2);
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
