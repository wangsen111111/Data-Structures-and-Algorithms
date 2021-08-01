package com.company;
/*
kmp:
    1,先得到字串的部分匹配表
    2，使用部分匹配表完成kmp匹配
 */
/*
 字符串匹配问题：：
 1) 有一个字符串  str1= ""硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好""，
    和一个子串    str2="尚硅谷你尚硅 你"
 2) 现在要判断 str1 是否含有 str2, 如果存在，就返回第一次出现的位置, 如果没有，则返回-1
    // 初始化两个数组下标，循环比较两个字符串的字符是否相等，相等则下标同时进一，比较下个元素直到匹配成功；
   // 不相等则使匹配的串下标回退到 0，被匹配的串下标回退到比较的初始位置+1，循环往复。
*/
/*
1，如果当前字符匹配成功(即str1[i]==str2[j])，则i++，j++，继续匹配下一个字符
2，如果失配(即str1[i]!=str2[j])，令i=i-(j-1)，j=0。相当于每次匹配失败时，i回溯，j被置为0。
  用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间。(不可行!)
 */

public class 暴力匹配算法 {
    public static void main(String[] args) {
        String str1= "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2="尚硅谷你尚硅你";
        System.out.println(violenceMatch(str1,str2));
    }

    /**
     *
     * @param str1
     * @param str2
     * @return  文本串最早出现的索引
     */
    private static int violenceMatch(String str1, String str2) {
        //把两个字符串转为字符数组
        char[] s1=str1.toCharArray();
        char[] s2=str2.toCharArray();
        //i的索引指向s1，j的索引指向s2
        int i=0,j=0;
        //保证匹配时，不越界，得出匹配的长度j
        while(i<s1.length&&j<s2.length){
            //拿出s1和s2中的每一个字符进行比较，如果相同，将i和j的索引向后推
            if(s1[i]==s2[j]){
                i++;j++;
            }else{
                //如果比到i和j不相等时，i回溯，从它的下一位开始，又和s2的第一个开始比，又将其往后退，直到比完为止
                i=i-(j-1);
                j=0;
            }
        }
        //判断是否匹配成功,上面得出的j极为匹配的长度，如果匹配的长度和s2相同，则返回它的初始下标i-j
        if(j==s2.length){
            return i-j;
        }else{
            return -1;
        }
    }
}
