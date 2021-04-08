package com.hct.stack;

/**
 * 中缀表达式通过栈运算结果
 * @Author HCT
 * @Date 2021/3/20 13:27
 * @Version 1.0
 */
public class Calculator {
    public static void main(String[] args) {
        
        String expression = "9-7*1+2";
        ArrayStack2 numStack2 = new ArrayStack2(10);
        ArrayStack2 operStack2 = new ArrayStack2(10);

        //定义相关的变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //将每次扫描得到的char保存到ch
        String keepNum = ""; //字符串用来拼接多位数
        //开始while循环扫描expression
        while(true){
            //一次得到expression的每一个字符
            ch = expression.substring(index, index+1).charAt(0);
            //判断ch是什么，然后做出相应的处理
            if (operStack2.isOper(ch)){  //如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack2.isEmpty()){
                    //如果当前的符号栈有操作符，进行比较，如果当前操作符的优先级小于或者等于栈中的操作符
                    if(operStack2.priority(ch) <= operStack2.priority(operStack2.peek())){
                        //就需要从栈中pop出两个数,再从符号栈中pop出一个符号
                        num1 = numStack2.pop();
                        num2 = numStack2.pop();
                        oper = operStack2.pop();
                        //进行运算，得到结果
                        res = numStack2.cal(num1, num2, oper );
                        //结果入数栈s
                        numStack2.push(res);
                        //将当前的操作符入符号栈
                        operStack2.push(ch);

                    } else {
                        //如果当前的符号栈有操作符，进行比较，如果当前操作符的优先级大于等于栈中的操作符，入符号栈
                        operStack2.push(ch);
                    }
                } else {
                    //如果栈为空，直接入栈
                    operStack2.push(ch);

                }
            } else {
                //如果是数，直接入数栈
                //numStack2.push(ch - 48);
                /**
                 * 此时多位数还不能得到正确的结果，只能进行一位数的运算
                 * 因为上面扫描到一个字符就直接入栈了，没有考虑多位数
                 *
                 * 所以处理数时，需要expression的表达式的index继续向后扫描，直到扫描到符号才入栈
                 * 使用上面的字符串 keepNum 用于多位数的拼接
                 */
                keepNum += ch;
                //如果ch已经是expression的最后以为，就直接入栈
                if (index == expression.length()-1){
                    numStack2.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack2.isOper(expression.substring(index+1, index+2).charAt(0))){
                        //如果后一位是运算符，则入栈
                        numStack2.push(Integer.parseInt(keepNum));
                        //重要的！！！keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index + 1，并判断是否是expression最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺粗的从数栈和符号栈中pop出相应的数字和符号，并运行
        while(true){
            //如果此时符号栈为空，则数栈中此时只有一个数字，即最后的结果
            if (operStack2.isEmpty()){
                break;
            }
            num1 = numStack2.pop();
            num2 = numStack2.pop();
            oper = operStack2.pop();
            res = numStack2.cal(num1, num2, oper );
            numStack2.push(res);
        }
        //将数栈中的结果，pop出，得到结果
        System.out.printf("表达式 %s = %d",expression,numStack2.pop());
    }
}

//定义一个栈类
class ArrayStack2{

    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈
    private int top = -1;

    //初始化栈
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满，不能进栈。");
            return;
        }
        stack[++top] = value;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，不能出栈。");
        }
        int value = stack[top--];
        return value;
    }

    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空。");
            return;
        }
        for (int i = top;i >= 0;i-- ){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //查看栈顶的符号
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级，优先级使用数字表示，数字越大，优先级越高
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-'){
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                //注意顺序，先弹出来的是减数
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

}