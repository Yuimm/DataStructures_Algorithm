package com.hct.tree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @Author HCT
 * @Date 2021/3/23 12:34
 * @Version 1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }
    //创建赫夫曼树的方法
    public static Node  createHuffmanTree(int[] arr){
        //第一步 为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3,将Node放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //排序，从小到大
            Collections.sort(nodes);

            // 取出根节点权值最小的两颗二叉树
            // (1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            // (2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            // (3)构建一颗新的二叉树
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // (4)从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // (5)将parent加入到nodes
            nodes.add(parentNode);


        }
        // 返回哈夫曼树的root结点
        return nodes.get(0);
    }


    // 编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历~~");
        }
    }
}





//创建结点类
class Node implements Comparable<Node>{
    int value;  //结点的权值
    Node left;  //指向左子节点
    Node right; //指向右子节点
    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从大到小排序
       // return o.value - this.value;
        //表示从小到大排序
        return this.value - o.value;
    }

    // 写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}