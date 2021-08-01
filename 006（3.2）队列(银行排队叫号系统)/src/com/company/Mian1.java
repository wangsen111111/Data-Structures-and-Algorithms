package com.company;

import java.util.Arrays;

public class Mian1 {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(maxSlidingWindow(nums,1));

    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(k==1){
            Arrays.sort(nums);
            int[] newArr=new int[nums.length];
            int j=0;
            for(int i=nums.length;i>0;i--){
                newArr[j]=nums[i];
            }
            return newArr;
        }
        if(nums==null||nums.length==0||k<0){
            return new int[0];
        }
        int[] arr=new int[nums.length-k+1];
        int temp=0;
        for(int i=0;i<nums.length-k+1;i++){
            if(nums[i]>nums[i+1]){
                temp=nums[i];
            }else{
                temp=nums[i+1];
            }
            if(temp>nums[i+2]){
                temp=temp;
            }else{
                temp=nums[i+2];
            }
            arr[i]=temp;
        }
        return arr;
    }
}
