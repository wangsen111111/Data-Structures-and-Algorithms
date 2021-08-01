package com.company;

public class 平衡二叉树 {
    public static void main(String[] args) {
        //int[] arr={4,3,6,5,7,8};
        int[] arr={10,11,7,6,8,9};
        BinarySortTree avlTree=new BinarySortTree();
        //添加结点
        for(int i=0;i<arr.length;i++){
            avlTree.add(new Node1(arr[i]));
        }
        System.out.println("中序遍历~");
        avlTree.infixOrder();
        System.out.println("树的高度"+avlTree.getRoot().height());
        System.out.println("左子树的高度"+avlTree.getRoot().leftHeight());
        System.out.println("右子数的高度"+avlTree.getRoot().rightHeight());


    }
}
//Node结点在二叉排序数