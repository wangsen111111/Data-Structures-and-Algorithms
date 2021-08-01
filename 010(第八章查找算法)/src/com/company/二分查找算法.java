package com.company;
/*
binarySearch:
注意：使用二分查找的前提是该数组是有序的
 */

import java.util.ArrayList;
import java.util.List;

public class 二分查找算法 {
    public static void main(String[] args) {
        int[] arr = { 1, 8, 10, 89,1000,1234 };
        int resIndex=binarySearch(arr,0,arr.length-1,1000);
        System.out.println(resIndex);

        int[] arr2={1, 8, 10, 89,1000,1000,1234 };//数组中有多个相同的数值时，将所有数据都找到
        List<Integer> resIndexList=binarySearch2(arr2,0,arr.length-1,1000);
        System.out.println(resIndexList);

    }
    //二分查找
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        //当left>right时，说明递归啦整个数组但是没有找到
        if(left>right){
            return -1;
        }
        int mid=(left+right)/2;
        if(findVal>arr[mid]){
            //要找的值在中间位置的右边，所以向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if(findVal<arr[mid]){
            return binarySearch(arr,left,mid-1,findVal);
        }else{
            //找到该值
            return mid;
        }
    }

    /**
     *数组中有多个相同的数值时，将所有数据都找到
     * 思路分析：
     * 1，在找到mid索引值后，不要马上返回
     * 2，向mid索引值的左边扫描，将所有满足相同的元素索引，加入到集合ArrayList
     * 3，向mid索引值的右边扫描，将所有满足相同的元素的索引，加入到集合ArrayList
     * 4，将ArrayList返回
     */
    public static List<Integer> binarySearch2(int[] arr,int left,int right,int findVal){
        //当left>right时，说明递归啦整个数组但是没有找到
        if(left>right){
            return new ArrayList<Integer>();
        }
        int mid=(left+right)/2;
        if(findVal>arr[mid]){
            //要找的值在中间位置的右边，所以向右递归
            return binarySearch2(arr,mid+1,right,findVal);
        }else if(findVal<arr[mid]){
            return binarySearch2(arr,left,mid-1,findVal);
        }else{
            List<Integer> resIndexList=new ArrayList<>();
            //2*左边
            int temp=mid-1;
            while (true){
                if(temp<0||arr[temp]!=findVal){
                    break;
                }
                resIndexList.add(temp);
                temp-=1;//temp左移
            }
            //1中间
            resIndexList.add(mid);
            //3*右边
            temp=mid+1;
            while(true){
                if(temp>arr.length||arr[temp]!=findVal){
                    break;
                }
                resIndexList.add(temp);
                temp+=1;//temp右移
            }
            //找到该值
            return resIndexList;
        }
    }
}
