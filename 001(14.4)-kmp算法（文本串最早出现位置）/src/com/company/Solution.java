package com.company;

public class Solution {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack,needle));
    }
    public static int strStr(String haystack, String needle) {
        if("".equals(needle)){
            return 0;
        }
        if("".equals(haystack)){
            return -1;
        }
        //获取到一个字符串（字串）的部分匹配值表（keyNext）
        //System.out.println(Arrays.toString(kmpNext(needle)));
        int[] next=kmpNext(needle);
        //写出kmp搜素算法（kmpSearch）使用部分搜索表完成kmp匹配
        //System.out.println(kmpSearch(haystack,needle,next));
        return kmpSearch(haystack,needle,next);
    }
    //写出kmp搜索算法，使用部分搜索表完成kmp匹配
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for(int i=0,j=0;i<str1.length();i++){
            //如果两个元素不同,就调整j的大小，
            //kmp算法的核心：能够让他不相等的时候，让它这个j往部分匹配表的前面去找，直到找到一个相等的位置，       //才停下
            while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }
            //如果两个元素相同
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            //如果相同元素的长度等于str2的长度，就输出元素索引
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }
    //部分匹配值表
    public static int[] kmpNext(String dest){
        int[] next=new int[dest.length()];
        next[0] = 0;
        //遍历目标字符串
        for(int i=1,j=0;i<dest.length();i++){
            //如果字符串中两个字符不相同，我们需要从next[j-1]中获取新的j，直到我们发现两个元素相同时才退出
            while(j>0&&dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];
            }
            //如果字符串中两个字符相同，就j++
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            //将得到的部分匹配值放到next数组
            next[i]=j;
        }
        //返回next数组
        return next;
    }

}
