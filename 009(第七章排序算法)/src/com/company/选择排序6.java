package com.company;
/*
selectSort:依次将每轮最小的数据交换到前面的位置
(1)选择排序共有(数组大小-1)轮排序
(2)每一轮排序，找到这一轮中最小的数及其下标
   找出这一轮中的最小的数
   1，先假定当前这个数是最小数
   2，然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到其下标
   3，当遍历到数组的最后时，就得到本轮最小数和下标
   4，交换
 */

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class 选择排序6 {
    public static void main(String[] args) {
        int[] arr=new int[80000];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*80000);
        }
        //int[] arr={34,119,101,1};
        //排序前
        Date startDate=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateStr=simpleDateFormat.format(startDate);
        System.out.println("排序前的时间为："+startDateStr);
        selectSort(arr);
        //排序后
        Date endDate=new Date();
        String endDateStr=simpleDateFormat.format(endDate);
        System.out.println("排序后的时间为："+endDateStr);
    }
    public static void selectSort(int[] arr) {
        for (int i=0;i<arr.length-1;i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = minIndex + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的值并不是最小
                    //重置min和minIndex
                    min = arr[j];
                    minIndex = j;
                }
            }
            //依次将每轮最小的数据交换到前面的位置
            if(minIndex!=i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第"+(i+1)+"轮排序后的数组为：");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
