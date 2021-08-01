package com.company;
/*
mergeSort:采用分治策略，将问题分成一些小问题，然后递归求解
 */

import java.util.Arrays;

public class 归并排序10 {
    private static int count;
    public static void main(String[] args) {
        int[] arr={7,5,6,4};
        count=0;//逆序对
        int[] temp=new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("归并排序后="+ Arrays.toString(arr));
        System.out.println("逆序对个数为:"+count);
    }
    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     * @param temp 做中转的数组
     * 1，先把左右两边(有序)的数据按照规则填充到temp数组，直到左右两边的有序序列，有一边处理完毕为止
     * 2，把有剩余数据的一边的数据依次全部填充到temp中
     * 3，将temp数组的元素拷贝到arr
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i=left;//左边有序序列的初始索引
        int j=mid+1;//右边有序序列的初始索引
        int t=0;//指向temp数组的当前索引
        //*1
        while(i<=mid&&j<=right){
            //如果左边有序序列的当前元素<右边有序序列的当前元素，就将左边的元素加到temp数组
            if(arr[i]<=arr[j]){
                temp[t]=arr[i];
                t+=1;
                i+=1;
            }else{
                count+=(mid-i+1);//每次合并时，都会在此逆序，由于又都有序所以，该数后面的数与其结合也算逆序
                //相反
                temp[t]=arr[j];
                t+=1;
                j+=1;
            }
        }
        //*2
        //如果左边有序序列有剩余，就将其加到temp中
        while(i<=mid){
            temp[t]=arr[i];
            t+=1;
            i+=1;
        }
        //右边有剩余
        while(j<=right){
            temp[t]=arr[j];
            t+=1;
            j+=1;
        }
        //*3
        //上面的递归，并不是每次都拷贝所有
        t=0;
        int tempLeft=left;
        //{ 第三次合并[第一次合并(8,4),第二次合并(5,7)],      1,3,6,2}
        //合并1：tempLeft=0  right=1
        //合并2：tempLeft=2  right=3
        //~
        //合并7：tempLeft=0  right=7
        System.out.println("tempLeft="+tempLeft+"     right="+right);
        while(tempLeft<=right){
            arr[tempLeft]=temp[t];
            t+=1;
            tempLeft+=1;
        }
    }

}
