package com.hct.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        //创建一个队列s
        ArrayQueueDemo2 arrayQueueDemo = new ArrayQueueDemo2(4);
        char key =' ';
        Scanner scanner =new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):推出程序");
            System.out.println("a(add):入队");
            System.out.println("g(get):出队");
            System.out.println("h(head):查看头部数据");
            key = scanner.next().charAt(0);
            switch (key){
                case  's':
                    arrayQueueDemo.showQueue();
                    break;
                case  'e':
                    scanner.close();
                    loop = false;
                    break;
                case  'a':
                    System.out.println("请输入一个数据：");
                    int value= scanner.nextInt();
                    arrayQueueDemo.addQueue(value);
                    break;
                case  'g':
                    try {
                        int res =  arrayQueueDemo.deleteQueue();
                        System.out.println("出队的是：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case  'h':
                    try {
                        int res =  arrayQueueDemo.headQueue();
                        System.out.println("头部数据是：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }

        }

    }
}

//使用数组模拟队列
class ArrayQueueDemo2 {

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueueDemo2(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; //指向队列头元素
        rear = 0; //指向队列尾的下一个位置，即最后一个数据的下一个位置
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //入队
    public void addQueue(int n){
        if (isFull()){
            System.out.println("满了，无法添加！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }
    //出队
    public int deleteQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，不需要删除！");
        }

        int value =  arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //显示所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return ;
        }
        //从front开始遍历，遍历 (rear - front + maxSize) % maxSize 个元素
        for (int i = front;i < front +(rear - front + maxSize) % maxSize;i++){
            System.out.printf("arr[%d] = %d\ns",i % maxSize,arr[i % maxSize]);
        }
    }
    //显示头部数据
    public int headQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            throw new RuntimeException("队列为空，没有头数据！");
        }
        return arr[front];
    }



}
