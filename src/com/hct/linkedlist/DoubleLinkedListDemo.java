package com.hct.linkedlist;

/**
 * @Author HCT
 * @Date 2021/3/15 23:03
 * @Version 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        //双向链表的测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");


        DoubleleLinkedList doubleleLinkedList = new DoubleleLinkedList();

        doubleleLinkedList.add(hero1);
        doubleleLinkedList.add(hero2);
        doubleleLinkedList.add(hero3);
        doubleleLinkedList.add(hero4);

        doubleleLinkedList.list();
        System.out.println();

        HeroNode2 newHeroNode2 = new HeroNode2(3, "wuyong", "fqfqwqf");
        doubleleLinkedList.update(newHeroNode2);
        doubleleLinkedList.list();
        System.out.println();


        doubleleLinkedList.delete(3);
        doubleleLinkedList.list();
        System.out.println();


    }
}



/**
 * 定义SingleLinkedList管理英雄
 */
class DoubleleLinkedList {

    //先初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }



    /**
     * 根据编号删除结点
     * 对于双向链表，可以直接找到要删除的这个结点，找到之后直接删除即可
     * @param no
     */
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;

        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //找到了，选择删除
            temp.pre.next = temp.next;

            if(temp.next != null){
                //如果是最后一个结点，就不需要执行下面这句话，否则出现空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有编号为 %d 的结点\n", no);
        }
    }




    /**
     * 根据newHeroNode结点的no编号进行信息的修改，no不能修改
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode){

        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 先建立一个辅助指针指向头节点
        HeroNode2 temp = head.next;
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
     * 尾插法添加结点
     * @param heroNode
     */
    // 1.找到最后一个结点
    // 2.将最后这个结点的next指向新的结点
    public void add(HeroNode2 heroNode){
        // 先建立一个辅助指针指向头节点
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
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
        HeroNode2 temp = head.next;
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
 * 双向链表的结点类
 * 定义HeroNode，每个heronode对象就是一个结点
 */
class HeroNode2 {

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 pre;  //指向前一个结点 默认为null
    public HeroNode2 next; //指向下一个结点 默认为null

    public HeroNode2(int no, String name, String nickName) {
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
