package com.company;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
BubbleSort:相邻的元素逆序就交换，每一趟确定一个位置的元素
1，一共进行(数组大小-1)次大的循环
2，如果发现在某一趟排序中，没有发生一次交换，可以提前结束冒泡排序(优化)
 */
public class 冒泡排序5 {
    public static void main(String[] args) {
        int[] arr=new int[80000];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*80000);
        }
        //排序前
        Date startDate=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateStr=simpleDateFormat.format(startDate);
        System.out.println("排序前的时间为："+startDateStr);
        //int[] arr={3,9,-1,10,-2};
        bubbleSort(arr);
        //排序后
        Date endDate=new Date();
        String endDateStr=simpleDateFormat.format(endDate);
        System.out.println("排序后的时间为："+endDateStr);
    }
    //将冒泡排序算法封装成一个方法
    public static void bubbleSort(int[] arr){
        int temp=0;//临时变量
        boolean flag=false;//标识变量，表示是否进行过交换
        for(int j=0;j<arr.length-1;j++) {
            //第一趟排序就是将最大的数排在最后
            for (int i = 0; i < arr.length - 1-j; i++) {
                //如果前面的数比后面的数大，就交换
                if (arr[i] > arr[i + 1]) {
                    flag=true;//发生交换，flag置为true
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            //System.out.println("第"+(j+1)+"趟排序后的数组为：");
            //System.out.println(Arrays.toString(arr));
            //如果发现在某一趟排序中，没有发生一次交换，可以提前结束冒泡排序(优化)
            if(!flag){//在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag=false;//重置flag进行下次判断
            }
        }
    }
}
