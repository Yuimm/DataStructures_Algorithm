package com.hct.avl;



/**
 * @Author HCT
 * @Date 2021/4/8 15:09
 * @Version 1.0
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //左旋数组
        //int[] arr1 = {4,3,6,5,7,8};
        //右旋数组
        //int[] arr2 = {10,12,8,9,7,6};
        //双旋数组
        int[] arr3 = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < arr3.length; i++){
            avlTree.add(new Node(arr3[i]));
        }

        avlTree.midOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
        System.out.println(avlTree.getRoot().right.left);
        System.out.println(avlTree.getRoot().right.right);
    }
}


//创建AVL树
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }
    //

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
        //当添加完一个结点后，如果（右子树的高度 - 左子树的高度） > 1,左旋
        if (rightHeight() - leftHeight() > 1){
            //是否需要双旋判断
            if(right!= null && right.leftHeight() > right.rightHeight()){
                //先对当前结点的右节点（右子树）-> 右旋转
                right.rightRotate();
                //再对当前结点进行左旋转
                leftRotate();
            } else {
                // 单旋，直接左旋
                leftRotate();
            }
            //每次添加一个节点后如果满足此条件，即可退出，不能在走下面的判断，如果不满足，继续走下面的判断
            return;

        }

        //当添加完一个结点后，如果（左子树的高度 - 右子树的高度） > 1,右旋
        if (leftHeight() - rightHeight() > 1){
            //是否需要双旋判断
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左节点（左子树）-> 左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                // 单旋，直接右旋
                rightRotate();
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

    //左旋
    public void leftRotate(){
        //创建新的结点，以当前结点的值
        Node newNode = new Node(value);
        //把当前结点的左子树设置成新的结点的左子树
        newNode.left = left;
        //把当前结点的右子树的左子树设置成新的结点的右子树
        newNode.right = right.left;
        //把当前结点的右子节点的值设置为当前结点的值
        value = right.value;
        //把当前结点的右子树的右子树设置为当前结点的右子树
        right = right.right;
        //把新的结点设置为当前结点的左子树
        left = newNode;

    }
    //右旋
    public void rightRotate(){
        //同理如上 左旋
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}
