package com.company;

import java.util.Arrays;

/*
14.4 KMP算法：应用场景：字符串匹配问题
 字符串匹配问题：：
 1) 有一个字符串  str1= ""硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好""，
    和一个子串    str2="尚硅谷你尚硅 你"
 2) 现在要判断 str1 是否含有 str2, 如果存在，就返回第一次出现的位置, 如果没有，则返-1
 */
public class Main {
    public static void main(String[] args) {
        //字符串匹配
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        System.out.println(Arrays.toString(kmpNext(str2)));
        //得到str2部分匹配表
        int[] next=kmpNext(str2);
        //kmp搜索算法，求出字符串最早出现的索引
        System.out.println(kmpSearch(str1,str2,next));
    }

    /**
     * 这是kmp的搜索算法
     * @param str1
     * @param str2
     * @param next
     * @return  文本串最早出现位置的索引
     */
    private static int kmpSearch(String str1, String str2, int[] next) {
        //遍历str1，拿出str1中的元素和str2比较
        for(int i=0,j=0;i<str1.length();i++){
            /**
             * 如果拿取的元素不相同，就调整j的大小，
             * kmp算法的核心就是：能够让他不相等的时候，让它这个j往部分匹配表的前面去找，直到找到一个相等的位置，才停下来
             */
            if(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }
            //如果str1和str2的元素相同，j就++
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            //如果j的长度等于str2的长度就返回其下表，又因为j和i都是从0开始的，故其下标要加1
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * @param dest 目标字符串
     * @return  获取字符串的部分匹配值表
     */
    private static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next=new int[dest.length()];
        //如果字符串长度为0，则其部分匹配值就为0
        next[0]=0;
        //遍历字符串，获取其部分匹配值表,i从第一个开始
        for(int i=1,j=0;i<dest.length();i++){
            /**
             *当dest.charAt(i)!=dest.charAt(j)时，我们需要从next数组中next[j-1]中获取一个新的j，直到我们发现i的元素和j的元素想等时退出
             */
            while(j>0&&dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];
            }
            //当dest.charAt(i)==dest.charAt(j)时，部分匹配值就加1
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            //将获取的部分匹配值加入到next数组中
            next[i]=j;
        }
        return next;
    }
}
