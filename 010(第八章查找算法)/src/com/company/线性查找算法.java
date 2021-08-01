package com.company;
/*
seqSearch:线性查找是逐一比对，发现有相同值，就返回下标
 */

public class 线性查找算法 {
    public static void main(String[] args) {
        int[] arr={1,9,11,-1,34,89};
        int index=seqSearch(arr,-1);
        if(index==-1){
            System.out.println("没有找到该元素的索引~");
        }else{
            System.out.println(index);
        }
    }
    //线性查找
    public static int seqSearch(int[] arr,int value){
        //遍历数组，与要查找的数进行比较，相同时返回其索引
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
