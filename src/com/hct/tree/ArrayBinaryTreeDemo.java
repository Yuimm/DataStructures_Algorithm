package com.hct.tree;

/**
 * @Author HCT
 * @Date 2021/3/22 23:07
 * @Version 1.0
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.infixOrder();
    }
}


//编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;// 存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 顺序存储二叉树的前序遍历
     */
    // 重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    // index ：数组下标
    public void preOrder(int index){
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    /**
     * 顺序存储二叉树的中序遍历
     */

    // 重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }

    public void infixOrder(int index){
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            infixOrder(2 * index + 1);
        }

        //输出当前这个元素
        System.out.println(arr[index]);

        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            infixOrder(2 * index + 2);
        }
    }

    /**
     * 顺序存储二叉树的后序遍历
     */

    // 重载postOrder
    public void postOrder() {
        this.postOrder(0);
    }

    public void postOrder(int index) {
        // 如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能执行二叉树的后序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
    }

}