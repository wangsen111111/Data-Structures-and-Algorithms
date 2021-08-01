package com.company;
/*
 字符串匹配问题：：
 1) 有一个字符串  str1= ""硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好""，
    和一个子串    str2="尚硅谷你尚硅 你"
 2) 现在要判断 str1 是否含有 str2, 如果存在，就返回第一次出现的位置, 如果没有，则返回-1
 */
/*
1) KMP 是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法
2) 字符串查找算法，简称为 “KMP 算法”，常用于在一个文本串 S 内查找一个模式串 P 的 出现位置，
3) KMP 方法算法就利用之前判断过信息，通过一个 next 数组，保存模式串中前后最长公共子序列的长度，每次回溯时，通过 next 数组找到，前面匹配过的位置，省去了大量的计算时间
 */
/*
j=next[j-1];
回溯：等的时候 dest.charAt(i)==charAt(j):j++
     不等的时候 dest.charAt(i)!=charAt(j)【换满足j大于0】:我们就一直找这个相等，在没有找到这个相等之前，就next[j-1]==j，相当于回溯
 */

import java.util.Arrays;

public class kmp算法 {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        //输出字符串（字串）的部分匹配值表
        System.out.println(Arrays.toString(kmpNext(str2)));
        int[] next=kmpNext(str2);
        //输出子字符串最早出现的位置
       System.out.println(kmpSearch(str1,str2,next));
    }
    //这是kmp的搜索算法
    private static int kmpSearch(String str1, String str2, int[] next) {
    //遍历
        for(int i=0,j=0;i<str1.length();i++) {
            //如果拿取的元素不相同，就调整j的大小
            //kmp算法的核心点:能够让它不相等的时候，让它这个j往我们部分匹配表的前面去找，直到找到一个相等的位置才停下来
            while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }

            //如果第一个字符串中拿取的元素和第二个字符串拿取的元素相同，则相同的长度+1
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            //如果相同的长度等于str2的长度，则返回该索引
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（字串）的部分匹配值表
    private static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值表
        int[] next = new int[dest.length()];
        //如果字符串长度为1，部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i)!=dest.charAt(j)满足时,我们需要从next[j-1]获取新的j
            //知道我们发现有dest.charAt(i)==dest.charAt(j)成立时才退出（这是kmp算法的核心）
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //获取部分匹配值
                //能够让它不相等的时候，让它这个j往我们部分匹配表的前面去找，直到找到一个相等的位置才停下来
                j=next[j - 1];//如果发现有一个不相等的情况下，就从部分匹配值表的j-1位置去更新这个j（这是kmp算法的核心）
            }
            //当dest.charAt(i)==dest.charAt(j)满足时,部分匹配值表就+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            //将j的值赋给next数组，保存部分匹配值表
            next[i] = j;
        }
        return next;
    }
}
