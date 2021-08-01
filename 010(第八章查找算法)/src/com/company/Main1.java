package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        String s="123456579";
        System.out.println(splitIntoFibonacci(s));
    }
    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        backtrack(S.toCharArray(), res, 0);
        return res;
    }

    public static boolean backtrack(char[] charArr, List<Integer> list, int index) {
        //边界条件判断，如果截取完了，并且list长度大于等于3，表示找到了一个组合。
        if (index == charArr.length && list.size() >= 3) {
            return true;
        }
        for (int i = index; i < charArr.length; i++) {
            //两位以上的数字不能以0开头
            if (charArr[index] == '0' && i > index) {
                break;
            }
            //将截取的字符数组拼接成一个字符串
            StringBuilder sb=new StringBuilder();
            for(int j=index;j<i+1;j++){
                sb.append(charArr[j]);
            }
            //将截取的字符串转化为数字
            long num = Integer.parseInt(String.valueOf(sb));
            //如果截取的数字大于int的最大值，则终止截取
            if (num > Integer.MAX_VALUE) {
                break;
            }
            //如果截取的数字大于list中前两个数字的和，说明这次截取的太大，直接终止，因为后面越截取越大
            if (list.size() >= 2 && num > list.get(list.size() - 1) + list.get(list.size() - 2)) {
                break;
            }
            if (list.size() <= 1 || num == list.get(list.size() - 1) + list.get(list.size() - 2)) {
                //把数字num添加到集合list中
                list.add((int) num);
                //如果找到了就直接返回
                if (backtrack(charArr, list, i + 1))
                    return true;
                //如果没找到，就会走回溯这一步，然后把上一步添加到集合list中的数字给移除掉
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
}
