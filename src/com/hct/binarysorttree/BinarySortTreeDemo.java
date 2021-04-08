package com.hct.binarysorttree;

import com.sun.source.tree.ReturnTree;

/**
 * @Author HCT
 * @Date 2021/4/7 22:34
 * @Version 1.0
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 0 };
        BinartSortTree binartSortTree = new BinartSortTree();
        for (int i = 0; i < arr.length; i++){
            binartSortTree.add(new Node(arr[i]));
        }

        binartSortTree.midOrder();

        //测试删除叶子结点
        //binartSortTree.delNode(2);
        //binartSortTree.delNode(5);
        //binartSortTree.delNode(9);
        binartSortTree.delNode(10);
        System.out.println("删除结点后");
        binartSortTree.midOrder();

    }
}

class BinartSortTree{
    private Node root;

    //添加节点
    public void add(Node node){
        if (root == null){
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void midOrder(){
        if (root != null){
            root.midOrder();
        } else {
            System.out.println("二叉排序树为空");
        }
    }

    //查找要删除的结点
    public Node search(int value){
        if (root == null){
            return null;
        } else {
            return root.serach(value);
        }
    }
    //查找父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        } else {
            return root.serachParent(value);
        }
    }

    /**
     * 删除有两颗子树的结点的辅助方法
     * @param node 传入的结点（当作二叉排序树的根节点）
     * @return 返回的以node为根节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点，找到最小值
        while (target.left != null){
            target = target.left;
        }
        //这是target就指向了最小结点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value){
        if ((root == null)) {

            return;
        }else{
            // 1.需要先去找要删除的结点 targetNode
            Node targetNode = search(value);
            if (targetNode == null){
                return;
            }
            //如果我们发现这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            //去找targetNode的父结点
            Node parent = searchParent(value);

            if (targetNode.left == null && targetNode.right == null){ //如果要删除的结点是叶子结点
                //判断targetNode 是父节点的左子节点还是右子节点
                if (parent.left !=null && parent.left.value == value){
                    parent.left = null;
                } else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){  //如果要删除的结点有两颗子树
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else { //如果要删除的结点只有一颗子树
                //如果要删除的结点有左子节点
                if (targetNode.left != null){
                    //如果targetNode是parent的左子节点
                    if (parent.left.value == value){
                        parent.left = targetNode.left;
                    } else{  //如果targetNode是parent的右子节点
                        parent.right = targetNode.left;
                    }
                }else{ //如果要删除的结点有右子节点
                    //如果targetNode是parent的左子节点
                    if (parent.left.value == value){
                        parent.left = targetNode.right;
                    } else{  //如果targetNode是parent的右子节点
                        parent.right = targetNode.right;
                    }
                }

            }

        }
    }

}

//创建结点
 class Node {
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //中序遍历
    public void midOrder(){
        if (this.left != null){
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.midOrder();
        }
    }

    //添加结点
    public void add(Node node){
        if (node == null){
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null){
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //查找要删除的节点
    public Node serach(int value){
        if (value == this.value) {
            return this;
        } else if (value <  this.value){
            if (this.left == null){
                return null;
            }
           return this.left.serach(value);
        } else {
            if (this.right == null){
                return null;
            }
            return this.right.serach(value);
        }
    }

    //查找要删除结点的父节点
    public Node serachParent(int value){
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else if(value < this.value && this.left != null){
            return this.left.serachParent(value);
        } else if(value >= this.value && this.right != null){
            return this.right.serachParent(value);
        } else {
            return null;
        }
    }
}
