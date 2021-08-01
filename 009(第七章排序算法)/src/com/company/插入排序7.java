package com.company;
/*
插入排序(insertSort)每次插入时，从无序表中取出第一个元素，将它插入到有序表的适当位置
1，插入排序要进行(数组大小-1)次插入
 */

import java.util.Arrays;

public class 插入排序7 {
    public static void main(String[] args) {
        int[] arr={101,34,119,1};
        insertSort(arr);
    }
    public static void insertSort(int[] arr){
        for(int i=1;i<=arr.length-1;i++) {
            //第一轮34.101，119，1
            int insertVal = arr[i];//定义待插入的数
            int insertIndex = i - 1;//要插入的索引
            //给insertVal找到插入的位置,
            //insertVal < arr[insertIndex]待插入的数还没有找到插入的位置
            //insertIndex >= 0防止数组越界
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//将插入位置的元素后移
                insertIndex--;
            }
            //当退出循环时，说明插入的位置找到，insertIndex+1
            if(i!=insertIndex+1) {
                arr[insertIndex + 1] = insertVal;
            }
            System.out.println("第"+i+"次插入后的数组为：" + Arrays.toString(arr));
        }
    }
}
