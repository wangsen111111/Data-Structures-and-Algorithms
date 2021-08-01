package com.company;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] nums={1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement(nums));
    }
    public static int majorityElement(int[] nums) {
        int res = nums[0];
        int num = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==res){
                num++;
            }else{
                num--;
                if(num==0){
                    res = nums[i];
                    num=1;
                }
            }
        }
        return res;
    }
    public static int majorityElement1(int[] nums) {
        int res = 0;
        //map
        Map<Integer, Integer> map = new HashMap<>();
        //将元素加到hashmap中
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (count > nums.length / 2) {
                return nums[i];
            }
                map.put(nums[i], count++);

            } else {
                map.put(nums[i], 1);
            }
        }
       /*
       //遍历map的值，如果其大于数组长度/2，就将其输出
       //遍历键值对对象的集合 ,得到每-一个键值对对象
       for(Map.Entry<Integer,Integer> mapValue:map.entrySet () ){
           if(mapValue.getValue()>nums.length/2){
               res=mapValue.getValue();
           }
       }
       return res;
       */
        return 0;
    }
}