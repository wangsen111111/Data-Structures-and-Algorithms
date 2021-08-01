package com.company;
import java.util.Scanner;
/*
栈(Stack)是一个先入后出的有序列表，变化的一端称为栈顶(Top)，另一端为固定的一端称为栈底(Bottom)
栈(stack)是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。
 */
/**
 * 使用单链表来模拟栈:注意我们在入栈的时候采用的是链表的头插法
 *               即就是入栈时，将该节点插入到(头节点)和(头节点的下一个节点)之间，遍历的时候从头节点开始遍历，仿照栈先进后出的特点
 */
public class Main {

    public static void main(String[] args) {
        //测试SingleLinkedListStack
        //先创建一个对象
        SingleLinkedListStack arrayStack=new SingleLinkedListStack();
        //扫描器
        Scanner sc=new Scanner(System.in);
        //控制是否退出菜单
        boolean loop=true;
        String key="";
        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key=sc.next();
            switch(key){
                case"show":
                    arrayStack.list();
                    break;
                case"push":
                    System.out.println("请输入一个数");
                    Node newNode=new Node(sc.nextInt());
                    arrayStack.push(newNode);
                    break;
                case"pop":
                    arrayStack.pop();
                    break;
                case"exit":
                    //关闭输入流
                    sc.close();
                    //退出循环
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~");
    }
}
//构建链表的节点有了，接下来就是构建链表了——SingleLinkedListStack类,注意我们在入栈的时候采用的是链表的头插法
class SingleLinkedListStack{
    //定义一个头指针，代表栈顶
    private Node top=new Node(-1);
    //判断栈是否为空
    public boolean isEmpty(){
        return top.getNext()==null;
    }
    //入栈——push--采用的是头插法
    public void push(Node node){
        //插入第一个节点
        if(top.getNext()==null){
            top.setNext(node);
            return;
        }
        //定义一个变量使其指向top的下一个节点,将每个节点插入到头节点和其后一位节点之间
        Node temp=top.getNext();
        top.setNext(node);
        node.setNext(temp);
    }
    //出栈
    public void pop(){
        if(top.getNext()==null){
            System.out.println("栈空，没有数据~");
        }
        System.out.println("节点为："+top.getNext().getValue());
        //将top后移
        top=top.getNext();
    }
    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空~");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        Node temp=top.getNext();
        while(true){
            //判断链表是否到最后
            if(temp==null){
                return;
            }
            //输出节点的信息，并将辅助变量后移
            System.out.println("节点为："+temp);
            temp=temp.getNext();
        }
    }
}
//定义链表节点
class Node{
    private int value;//链表值
    private Node next;//指向下一个节点
    //构造器
    public Node(int value){
        this.value=value;
    }
    //set和get方法
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node next){
        this.next=next;
    }
    //重写toString方法
    @Override
    public String toString() {
        return "HeroNode值为："+value;
    }
}