package com.company;
/*
insertValueSearch:对二分查找的优化，将mid改为-->自适应mid
当数据量较大时,并且数据之间跳跃性不大时(关键字分布比较均匀)，采用插值查找, 速度较快，mid自适应
mid=left+(right-left) * (findVal-arr[left]) / (arr[right]-arr[left])
 */

public class 插值查找算法 {
    public static void main(String[] args) {
        int[] arr=new int[100];
        for(int i=1;i<100;i++){
            arr[i]=i+1;
        }
        int index=insertValueSearch(arr,0,arr.length-1,4);
        System.out.println(index);
    }
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if(left>right||findVal<arr[0]||findVal>arr[arr.length-1]){
            return -1;
        }
        int mid=left+(findVal-arr[left])*(right-left)/(arr[right]-arr[left]);
        if(findVal>arr[mid]){
            //要找的值在arr[mid]的右边，递归向右查找
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if(findVal<arr[mid]){
            //要找的值在arr[mid]的做百年
            return insertValueSearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
}
