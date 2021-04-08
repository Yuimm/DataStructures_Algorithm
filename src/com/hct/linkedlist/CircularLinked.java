package com.hct.linkedlist;

/**
 * @Author HCT
 * @Date 2021/3/20 0:34
 * @Version 1.0
 */
public class CircularLinked {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1,2,5);

    }

}

//创建一个环形单向链表
class CircleSingleLinkedList{

    private Boy first = null;

    //添加孩子结点
    public void add(int num){
        if (num < 1){
            System.out.println("输入的数据有误！");
            return;
        }
        Boy curBoy = null;
        for (int i = 1;i <= num;i++){
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }


    public void showBoy(){

        if (first == null){
            System.out.println("链表为空！");
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     *
     * @param startNo 从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums) {
         //先对数据进行检验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数有误");
            return;
        }

        Boy helper = first;
        // 先把辅助指针helper移动到链表的最后一个结点
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0;j < startNo-1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是个循环操作，直到圈中只有一个结点
        while (true){
            if (helper == first){
                break;
            }
            // 让first和helper指针同时移动countNum - 1
            for (int j = 0;j < countNum - 1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的结点，就是要出圈的结点
            System.out.printf("小孩编号为%d的出圈\n",first.getNo());
            //将first指向的小孩结点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d\n",helper.getNo());
    }

}


class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}