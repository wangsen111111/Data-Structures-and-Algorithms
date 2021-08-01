package com;

import java.util.Stack;

public class 逆序 {
    public static void main(String[] args) {
            String str="abcd";
            System.out.println(solve(str));

    }
    public static String solve (String str) {
        char[] stringChar=str.toCharArray();
        StringBuilder stringBuilder=new StringBuilder(str.length());
        Stack stack=new Stack<>();
        for(int i=0;i<str.length();i++){
            stack.push(stringChar[i]);
        }
        while(stack.size()>0){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }
}
