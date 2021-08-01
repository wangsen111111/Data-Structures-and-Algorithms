package com.company;

import java.util.Stack;

/*
链表：
1，链表以节点的方式来存储，是链式存储
2，每个节点包含一个date域，next域指向下一个节点
3，链表的各个节点不一定是连续存储
4，链表分带头节点的链表和不带头节点的链表，根据实际需求来确定
 */
public class Main {
//演示栈Stack的基本使用
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<Integer>();
        //入栈
        stack.add(1);
        stack.add(2);
        stack.add(3);
        //出栈
        //pop()就是将栈顶的数据取出
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
