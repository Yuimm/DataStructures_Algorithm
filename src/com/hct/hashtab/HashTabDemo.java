package com.hct.hashtab;

import java.util.Scanner;

/**
 * 哈希表
 *
 * @Author HCT
 * @Date 2021/3/22 1:29
 * @Version 1.0
 */
public class HashTabDemo {
    public static void main(String[] args) {

        //创建哈希表
        HahTab hahTab = new HahTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            System.out.println();
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建 雇员
                    Emp emp = new Emp(id, name);
                    hahTab.add(emp);
                    break;
                case "list":
                    hahTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hahTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

/**
 * 创建HashTab管理多条链表
 */
class HahTab{

    private EmpLinkedList[] empLinkedListsArray;
    private int size; //表示有多少条链表

    public HahTab(int size){
        this.size = size;
        empLinkedListsArray = new EmpLinkedList[size];
        /**
         * 不要忘记初始化每一条链表
         */
        for (int i = 0; i < size; i++){
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据雇员的id，得到该雇员应当添加到哪条链条
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListsArray[empLinkedListNo].add(emp);

    }

    //散列函数，使用取模法
    public int hashFun(int id){
        return id % size;
    }

    //遍历所有的链表
    public void list(){
        for (int i = 0; i < size; i++){
            empLinkedListsArray[i].list(i);
        }
    }

    //根据id查找雇员
    public void findEmpById(int id){
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListsArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {// 找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }

    }

}



/**
 * 表示一个雇员资源类
 */
class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name){
        super();
        this.id = id;
        this.name = name;
    }
}



/**
 * 创建EmpLinkedList，表示单条链表的操作
 */
class EmpLinkedList{
    //头指针，执行第一个Emp，因此我们这个链表的head，是直接指向第一个Emp
    private Emp head; //默认为null

    //添加雇员到链表
    //假定id时自增长的，直接把雇员加入到本链表的最后
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，需要一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){
               break;
            }
            curEmp = curEmp.next;
        }
        //退出时，直接把emp加到链表的最后
        curEmp.next = emp;
    }

    //遍历链表的信息
    public void list(int no){
        if (head == null){ //链表为空
            System.out.println("第" + (no+1) + "条链表为空！");
            return;
        }
        System.out.println("第" + (no+1) + "条链表的信息为：");
        Emp curEmp = head;
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);

            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针帮忙查找
        Emp curEmp = head;
        while (true){

            if (curEmp.id == id){
                break;
            }
            curEmp = curEmp.next;
            // 退出
            if (curEmp == null) {// 说明遍历当前链表没有找到该雇员
                break;
            }
        }
        return curEmp;
    }

}