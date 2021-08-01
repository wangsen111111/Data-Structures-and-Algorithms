package com.company;
/*
QuickSort(对冒泡排序的一种改进):[分两半，左递归快排，右递归快排]
通过一趟排序将要排序的数据分割成独立的两部分，其中一部分数据都比另一部分的所有数据都要小，
然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行
 */

import java.util.Arrays;

public class 快速排序9 {
    public static void main(String[] args) {
        int[] arr={-9,0,78,0,23,-567,0,70};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr,int left,int right){
        //1,通过一趟排序将要排序的数据分割成独立的两部分，其中一部分数据比另一部分数据要小
        int r=right;//左下表和右下表
        int l=left;
        int pivot=arr[(left+right)/2];//中轴值
        int temp=0;
        while(l<r){
            //在pivot的左边找，直到找到大于pivot的值才退出
            while(arr[l]<pivot){
                l+=1;
            }
            //在pivot的右边找，直到找到小于pivot的值才退出
            while(arr[r]>pivot){
                r-=1;
            }
            //如果l>=r说明左边的数都比右边的数小，就不用再交换数据
            if(l>=r){
                break;
            }
            //否则将数据进行交换
            temp=arr[r];
            arr[r]=arr[l];
            arr[l]=temp;
            //可能数组中有与pivot中轴相同的数，遇到相同的数，相同的数这一边位置不用移动，只需将另一边的位置推进，然后将这个相同的数一直交换
            //直到交换到pivot旁边
            if(arr[l]==pivot){
                r-=1;
            }
            if(arr[r]==pivot){
                l+=1;
            }
        }
        //如果l==r,必须l++，r--，否则会出现栈溢出,死递归
        if(l==r){
            l+=1;
            r-=1;
        }
        //2,左递归
        if(left<r){
            quickSort(arr,left,r);
        }
        //3,右递归
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}
