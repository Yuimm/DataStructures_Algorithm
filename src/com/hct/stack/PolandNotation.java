package com.hct.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author HCT
 * @Date 2021/3/21 0:02
 * @Version 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //先将表达式放到一个ArrayList中
        //给ArrayList传递一个方法，遍历ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList" + list);
        int res = calculate(list);
        System.out.println("计算的结果=" + res);
    }

    //完成对逆波兰表达式的运算
	/*
	 * 1)从左至右扫描，将3和4压入堆栈；
		2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
		3)将5入栈；
		4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
		5)将6入栈；
		6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static  int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for (String item : ls){
            if (item.matches("\\d+")){
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

}
