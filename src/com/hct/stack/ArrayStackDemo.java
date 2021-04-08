package com.hct.stack;

import java.util.Scanner;

/**
 * @Author HCT
 * @Date 2021/3/20 12:13
 * @Version 1.0
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack arrayStack = new ArrayStack(4);
         String key = "";
         boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
             System.out.println("show:显示栈");
             System.out.println("exit:退出程序");
             System.out.println("push:压栈");
             System.out.println("pop:出栈");
             System.out.println("请输入你的选择：");
             key = scanner.next();
             switch (key){
                 case "show":
                     arrayStack.list();
                     break;
                 case "push":
                     System.out.println("请输入一个数字");
                     int value = scanner.nextInt();
                     arrayStack.push(value);
                     break;
                 case "pop":
                     try {
                         int res = arrayStack.pop();
                         System.out.printf("出栈的数据是 %d\n",res);
                     } catch (Exception e) {
                         System.out.println( e.getMessage());
                     }
                     break;
                 case "exit":
                     scanner.close();
                     loop = false;
                     break;
                 default:
                     break;
             }

         }
        System.out.println("程序退出了");

    }
}



//定义一个栈类
class ArrayStack{

    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈
    private int top = -1;

    //初始化栈
    public ArrayStack(int maxSize){
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

}