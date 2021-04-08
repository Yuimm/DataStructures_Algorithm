package com.hct.tree;

import java.util.function.IntToDoubleFunction;

/**
 * 二叉树的 前 中 后序遍历
 *
 * @Author HCT
 * @Date 2021/3/22 21:26
 * @Version 1.0
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {

        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

//        // 测试前序遍历
//        System.out.println("前序遍历:"); // 1,2,3,5,4
//        binaryTree.preOrder();
//
//        // 测试中序遍历
//        System.out.println("中序遍历:");
//        binaryTree.infixOrder(); // 2,1,5,3,4
//
//        // 测试后序遍历
//        System.out.println("后序遍历:");
//        binaryTree.postOrder(); // 2,5,4,3,1
//
//        // 前序遍历查找
//        System.out.println("前序遍历方式~~~");
//        HeroNode resNode = binaryTree.preOrderSearch(11);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为 no=%d name=%s\n", resNode.getId(), resNode.getName());
//        } else {
//            System.out.printf("没有找到此英雄\n");
//        }
//        System.out.println();
//
//        // 中序遍历查找
//        System.out.println("中序遍历方式~~~");
//        resNode = binaryTree.infixOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为 no=%d name=%s\n", resNode.getId(), resNode.getName());
//        } else {
//            System.out.printf("没有找到此英雄\n");
//        }
//        System.out.println();
//
//        // 后序遍历查找
//        System.out.println("后序遍历方式~~~");
//        resNode = binaryTree.postOrderSearch(6);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为 no=%d name=%s\n", resNode.getId(), resNode.getName());
//        } else {
//            System.out.printf("没有找到此英雄\n");
//        }
//        System.out.println();


        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
        System.out.println();

        binaryTree.delNode(5);
        System.out.println("删除节点 5 后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4
        System.out.println();

        binaryTree.delNode(3);
        System.out.println("删除子树 3  后，前序遍历");
        binaryTree.preOrder(); // 1,2




    }
}

//定义 BinaryTree 二叉树
class BinaryTree {
    //定义根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 前序遍历查找
    public HeroNode preOrderSearch(int id){
        if (root != null){
            return root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int id) {
        if (root != null) {
            return root.infixOrderSearch(id);
        } else {
            return null;
        }
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    // 删除结点
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if (root.getId() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }



}

//创建HeroNode结点
class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 编写前序遍历的方法
    public void preOrder(){
        // 先输出父结点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父结点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    // 后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 递归删除结点
     * 	1.如果删除的节点是叶子节点，则删除该节点
     * 	2.如果删除的节点是非叶子节点，则删除该子树
     */
    public void delNode(int id) {
        // 思路
        /*
         * 1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
         * 2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
         * 3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
         * 4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除 5. 如果第4步也没有删除结点，则应当向右子树进行递归删除.
         *
         */

        // 2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.id == id){
            this.left = null;
            return;
        }
        // 3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        // 4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(id);
        }
        // 5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(id);
        }

    }




    /**
     * 前序遍历查找
     * @param id
     * @return 找到返回 Node， 否则返回空
     */
    public HeroNode preOrderSearch(int id){
        // 比较当前结点是不是
        if (this.id == id){
            return this;
        }
        HeroNode resNode = null;
        // 1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        // 2.如果左递归前序查找，找到结点，则返回
        if (this.left != null){
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null){
            return resNode;
        }
        // 1.左递归前序查找，找到结点，则返回，否继续判断，
        // 2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        //已经到了前序排列的最后一个结点，不过结果是否找到，都直接返回就行，不用再判断是否为空
        //如果为空，即没找到
        return resNode;

    }

    /**
     * 中序遍历查找
     * @param id
     * @return 找到返回 Node， 否则返回空
     */
    public HeroNode infixOrderSearch(int id) {
        // 判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        // 如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if (this.id == id) {
            return this;
        }
        // 否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     * @param id
     * @return 找到返回 Node， 否则返回空
     */
    public HeroNode postOrderSearch(int id) {
        // 判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {// 说明在左子树找到
            return resNode;
        }

        // 如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        // 如果左右子树都没有找到，就比较当前结点是不是
        if (this.id == id) {
            return this;
        }
        return resNode;
    }




}
