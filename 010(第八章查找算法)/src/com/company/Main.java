package com.company;
/*
常用查找算法：
(1)顺序(线性)查找
   按照顺序比对，找到我们需要的数据
(2)二分查找/折半查找
(3)插值查找
(4)斐波那契查找(黄金分割查找)
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] nums = {3, 3, 3};
        System.out.println(Arrays.toString(searchRange(nums, 3)));
    }

    //使用二分查找即可
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        //如果该数组无这个元素，就返回-1，-1
        if (binarySearch(nums, 0, nums.length - 1, target) == -1) {
            return new int[]{-1, -1};
        }

        int index;
        int count = 0;
        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                //如果target在数组中只出现了一次，就直接返回该索引
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target) {
                        count++;
                    }
                }
                if (count == 0) {
                    arr[0] = i;
                    arr[1] = i;
                } else {
                    arr[0] = i;
                    arr[1] = i + count;
                    return arr;
                }
            }
        }
        return arr;
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (findVal > arr[mid]) {
            //要找的值在mid右边，向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
