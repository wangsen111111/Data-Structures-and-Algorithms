package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        int[] nums={3,30,34,5,9};
        String[] ss=new String[nums.length];
        //将字符串转为字符串数组
        for(int i=0;i<nums.length;i++){
            ss[i]=String.valueOf(nums[i]);
        }
        //对字符串数组进行排序
        Arrays.sort(ss, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        //将排好序的字符串数组拼接成一个字符串
        StringBuilder sb=new StringBuilder();
        for(String s:ss){
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}
