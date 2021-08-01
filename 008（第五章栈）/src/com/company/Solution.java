package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        String s =  "3 4 + 5 * 6 -";
        System.out.println(calculate1(s));
    }
    public static int calculate1(String s) {
        //1,去除字符串所有的空格
        String[] ss=s.split(" ");
        //2,将字符串中的数据加入到链表
        List<String> list=getListString(ss);
        //3,遍历链表
        Stack<String> stack=new Stack<>();
        for (String item : list) {
            //如果是数，就入栈(使用正则表达式)
            if (item.matches("\\d+")) {
                //匹配的是多位数，就入栈
                stack.push(item);
            } else {
                //如果是运算符，就从栈中弹出两个数，计算它们的值，然后再将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符错误~");
                }
                //然后将运算得到的值入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
    //依次将数据和运算符加入到链表，方便遍历
    public static List<String> getListString(String[] ss){
        List<String> list=new ArrayList<>();
        for(String s:ss){
            list.add(s+"");
        }
        return list;
    }




    public static int calculate(String s) {
        //去除字符串所有空格
        String ss = s.replaceAll(" ", "");
        return calculate1(ss);
    }
    public static int calculate2(String s) {
        //1,将字符串转为字符串数组
        char[] ch = s.toCharArray();

        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int res = 0;//目标值
        //2,遍历字符串数组，有运算符的将运算结果放入栈
        while (i < s.length()) {
            //(1),如果是数字(多位数)，就直接入栈
            int num = 0;
            char c = ch[i];
            if(c == '*' || c == '/' || c == '+' || c == '-'){
                i++;
            }
            while (i<ch.length&&Character.isDigit(ch[i])) {
                num = num * 10 + ch[i] - '0';
                i++;
            }
            //(2),如果碰到 '+' '-' '*' '/', 则查找下一个数字num,将运算的结果入栈
            switch (c) {
                case '-':
                    num = -num;
                    break;
                case '*':
                    num = stack.pop() * num;
                    break;
                case '/':
                    num = stack.pop() / num;
                    break;
                default:
                    break;
            }
            //将数字及有运算符的运算之后的结果入栈
            stack.push(num);
        }
        //3，将栈中所有的数加起来
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
