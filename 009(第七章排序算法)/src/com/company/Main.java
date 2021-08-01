package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        //1，定义一个字符串
        String str="dacgebf ";
        //2，将这个字符串转为字符数组
        char[] chs=str.toCharArray();
        //3，把字符数组进行排序
        bubbleSort(chs);
        //4，把排序后的数组转成字符串
        String result=String.valueOf(chs);
        //5，输出最后的字符串
        System.out.println(result);
    }
    //冒泡排序功能
    public static void bubbleSort(char[] chs){
        for(int x=0;x<chs.length-1;x++){
            for(int y=0;y<chs.length-1-x;y++){
                if(chs[y]>chs[y+1]){
                    char temp=chs[y];
                    chs[y]=chs[y+1];
                    chs[y+1]= temp;
                }
            }
        }
    }
}




