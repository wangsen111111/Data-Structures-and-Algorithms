package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root=createHuffmanTree(arr);
        //preOrder(root);
        postOrderSearch(root);
    }
    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr){
        List<Node> nodes=new ArrayList<Node>();
        for(int value:arr){
            nodes.add(new Node(value));
        }
        while(nodes.size()>1){
            Collections.sort(nodes);
            //取出权值最小的结点
            Node leftNode=nodes.get(0);
            //取出权值第二小的结点
            Node rightNode=nodes.get(1);
            //构建一个新的结点
            Node parent=new Node(leftNode.value+rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //从ArrayList删除已经处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新得到的结点加入到链表
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //前序遍历
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("空数，无法遍历！");
        }
    }
    //后序遍历查找
    public static void postOrderSearch(Node root){
        if(root!=null){
            root.postOrder();
        }else{
            System.out.println("空数，无法遍历！");
        }
    }

}

//创建结点类
//为了让Node对象持续排序Collections集合排序，让Node实现Comparable
class Node implements Comparable<Node> {
    int value;//结点数值
    char c;//字符
    Node left;//指向左子节点
    Node right;//指向右子节点
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        //如果左子节点不为空
        if(this.left!=null){
            this.left.preOrder();
        }
        //如果右边结点不为空
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //如果左子节点不为空，继续后续遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //如果右子节点不为空，继续后续遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        //输出当前节点
        System.out.println(this);
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node[" + "value=" + value + ']';
    }
    @Override
    public int compareTo(Node o) {
        //从小到大排
        return this.value-o.value;
    }

}

 */
