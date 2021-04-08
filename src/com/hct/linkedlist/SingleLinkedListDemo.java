package com.hct.linkedlist;

import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.BrokenBarrierException;

/**
 * @Author HCT
 * @Date 2021/3/15 20:33
 * @Version 1.0
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //测试

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();
        System.out.println();

        reversePrint(singleLinkedList.getHead());
        System.out.println();

    }

    /**
     * 逆向输出链表的数据，利用栈
     * @param head
     */
    public static void reversePrint(HeroNode head){

        if (head.next ==null){
            return;
        }
        //创建一个栈，将各国结点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 对单链表的结点进行反转
     * @param head
     */
    public static void reverseLinkedList(HeroNode head){

        HeroNode reverseHeroNode = new HeroNode(0, "", "");

        if (head.next == null || head.next.next == null){

            return ;
        }
        HeroNode temp = head.next;
        HeroNode next = null;

        while (temp != null) {
            //先保存当前结点的下一个结点，否则会断链
            next = temp.next;
            temp.next = reverseHeroNode.next;
            reverseHeroNode.next = temp;
            temp = next;

        }
        head.next = reverseHeroNode.next;
    }


    /**
     * 查找倒数第index个结点
     * @param head 头节点
     * @param index
     * @return
     */
    public static HeroNode getHeroBodeById(HeroNode head, int index){

        HeroNode temp1 = head;
        HeroNode temp2 = head;

        int leng = 0;

        while (true){

            if (temp2.next == null){
                break;
            }
            if (leng == index-1) {
                temp2 = temp2.next;
                temp1 = temp1.next;
            } else {
                temp2 = temp2.next;
                leng++;
            }

        }
        return temp1;
    }

    /**
     * 统计链表中有效结点的个数，不包括单链表
     * @param head
     * @return
     */
    public static int getListLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int total = 0;
        HeroNode temp = head.next;

        while (temp != null){
            total++;
            temp = temp.next;
        }
        return  total;
    }
}


/**
 * 定义SingleLinkedList管理英雄
 */
class SingleLinkedList {

    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","" );
    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 不根据序号，尾插法添加结点
     * @param heroNode
     */
    // 1.找到最后一个结点
    // 2.将最后这个结点的next指向新的结点
    public void add(HeroNode heroNode){
        // 先建立一个辅助指针指向头节点
        HeroNode temp = head;
        // 遍历链表，找到最后一个结点
        while (true){
            // 当temp.next为null时，表示已经是最后一个结点
            if (temp.next == null){
                break;
            }
            //如果没找个最后一个，辅助指针temp继续后移
            temp = temp.next;
        }
        // 当退出while循环时，此时temp指向的便是最后一个结点
        temp.next = heroNode;
    }

    /**
     * 根据序号，尾插法添加结点
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        // 先建立一个辅助指针指向头节点
        HeroNode temp = head;
        // 创建一个标识，来表明已经存在相同序号的结点，不允许添加
        boolean flag = false;
        while (true){
            //遍历到最后一个结点，退出结点，执行尾插法
            if (temp.next == null){
                break;
            }
            // 发现需要插入的位置，退出循环，执行插入
            if(temp.next.no > heroNode.no){
                break;
            } else if (temp.next.no == heroNode.no){
               // 发现相同编号的结点，把flag置为true，禁止插入
                flag = true;
                break;
            }
            // 辅助指针后移
            temp = temp.next;
        }
        if (flag){
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入\n",heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 根据newHeroNode结点的no编号进行信息的修改，no不能修改
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode){

        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 先建立一个辅助指针指向头节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            // 遍历到最后一个结点退出循环
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
               flag = true;
               break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为 %d 的结点\n",newHeroNode.no);
        }
    }

    /**
     * 根据编号删除结点
     * @param no
     */
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;
        boolean flag = false;

        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
           temp = temp.next;
        }
        if (flag){
            //找到了，选择删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有编号为 %d 的结点\n", no);
        }
    }

    /**
     * 遍历链表
     */
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }
        //不为空时，因为头节点不能动，所以创建一个辅助指针temp进行遍历链表
        HeroNode temp = head.next;
        while (true){
            //判断链表是否到最后一个结点
            if (temp == null){
                break;
            }
            // 不是最后一个结点时，依次遍历输出结点的信息
            System.out.println(temp);
            //输出完一个结点的信息后，将辅助指针后移
            temp = temp.next;
        }
    }
}


/**
 * 定义HeroNode，每个heronode对象就是一个结点
 */
class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个结点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
