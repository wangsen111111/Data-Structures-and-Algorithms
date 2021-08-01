package com.company;

public class 二叉排序树 {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node1(arr[i]));
        }
        //中序遍历
        System.out.println("中序遍历二叉树：");
        binarySortTree.infixOrder();


        //(1)
        binarySortTree.delNode1(2);
        binarySortTree.infixOrder();
        //(3)
        binarySortTree.delNode1(1);
        binarySortTree.infixOrder();
        //(2)
        binarySortTree.delNode1(7);
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node1 root;//根节点

    public Node1 getRoot() {
        return root;
    }
    //求二叉树最小结点的值，并将最小值这个结点删除
    public int delRightTreeMin(Node1 node){
        Node1 target=node;
        //循环查找左节点，就会找到最小值
        while(target.left!=null){
            target=target.left;
        }
        int minVal=target.value;
        //删除最小结点
        delNode1(target.value);
        return minVal;
    }

    //删除结点
    public void delNode1(int value) {
        if (root == null) {
            return;
        } else {
            //先找到需要删除的结点
            Node1 targetNode = search(value);
            //如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这个二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父节点
            Node1 parent = searchParent(value);

            if (targetNode.left == null && targetNode.right == null) {
                //(1)删除叶子结点
                if (parent.left != null && parent.left.value == value) {
                    //是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    //是右子节点
                    parent.right = null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                //(2)删除有两颗子树的结点
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.value=minVal;



            }else{
                //(3)删除只有一颗子树的结点
                if(targetNode.left!=null){
                    if(parent!=null) {
                        //如果targetNode是父节点的左子节点
                        if (parent.left.value == value) {
                            //如果targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    }else{
                        root=targetNode.left;
                    }
                }else{
                    if(parent!=null) {
                        //如果targetNode是父节点的右子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else{
                        root=targetNode.right;
                    }
                }

            }
        }
    }

    //查找要删除的结点
    public Node1 search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除结点的父节点
    public Node1 searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //添加结点的方法
    public void add(Node1 node) {
        if (root == null) {
            root = node;//如果根节点为空，就让root直接指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历~");
        }
    }

}

//创建结点
class Node1 {
    int value;
    Node1 left;
    Node1 right;

    @Override
    public String toString() {
        return "Node1[" + "value=" + value + ']';
    }

    public Node1(int value) {
        this.value = value;
    }

    /**
     *
     */
    //返回左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }
    //返回右子数的高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }
    //返回以该节点根节点的树的高度
    public int height() {
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }
    //左旋转方法
    private void leftRotate(){
        //创建一个新的结点newNode，值等于当前节点根节点的值
        Node1 newNode=new Node1(value);
        //把新节点的左子树设置为当前结点的左子树
        newNode.left=left;
        //把新节点的右子树设置为当前系欸但的右子数的左子树
        newNode.right=right.left;
        //把当前结点的值换位右子节点的值
        value=right.value;
        //把当前结点的右子数设置为右子数的右子数
        right=right.right;
        //把当前结点的左子树设置为新的结点
        left=newNode;
    }
    //右旋转方法
    private void rightRotate(){
        Node1 newNode=new Node1(value);
        newNode.right=right;
        newNode.left=left.right;
        value=left.value;
        left=left.left;
        right=newNode;
    }

    /**
     *
     */

    //查找要删除的结点
    public Node1 search(int value) {
        if (value == this.value) {
            //就是当前这个结点
            return this;
        } else if (value < this.value) {
            //如果当前左子节点为空
            if (this.left == null) {
                return null;
            }
            //向左子树查找
            return this.left.search(value);
        } else {
            //向右子数查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父节点
    public Node1 searchParent(int value) {
        //如果当前结点就是要删除结点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }else {
            if (value < this.value && this.left != null) {
                //如果当前结点的左子树不为空并且查找的那个值小于当前结点的值
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //添加结点
    //递归的形式添加结点，注意要满足二叉树的要求
    public void add(Node1 node) {
        if (node == null) {
            return;
        }
        //判断传入结点的值和当前子树根结点的值的大小
        if (node.value < this.value) {
            if (this.left == null) {
                //如果当前结点的左子节点位null
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子数添加
                this.right.add(node);
            }
        }
        //当添加完一个结点后，如果(右子数高度-左子树高度)>1,左旋转
        if(rightHeight()-leftHeight()>1){
            if(right!=null&&right.leftHeight()>right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate();
            }
            return;//加完直接返回
        }
        //当添加完一个结点后，如果(左子数高度-右子树高度)>1,右旋转
        if(leftHeight()-rightHeight()>1){
            if(left!=null&&left.rightHeight()>left.leftHeight()) {
                //如果它的左子树的右子数的高度大于它的左子树的高度
                //<1>先对当前这个结点的左子节点进行左旋转
                left.leftRotate();;
                //<2>再对当前结点进行右旋转
                rightRotate();
            }else{
                //直接进行右旋转即可
                rightRotate();;
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}

