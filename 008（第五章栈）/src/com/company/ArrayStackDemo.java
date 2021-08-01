package com.company;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试ArrayStack
        //先创建一个对象
        ArrayStack arrayStack=new ArrayStack(4);
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
                    arrayStack.push(sc.nextInt());
                    break;
                case"pop":
                    try{
                        System.out.printf("出栈的数据为%d\n",arrayStack.pop());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
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
//定义一个ArrayStack表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就在该数组
    private int top=-1;//top表示栈顶，初始化为-1
    //构造器
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈——push
    public void push(int value){
        //先判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈——pop(将栈顶的数据返回)
    public int pop(){
        //先判断栈是否为空
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        //因为要把栈顶取出，同时要把栈顶-1，所以需要一个变量来保存取出的值
        int value=stack[top];
        top--;
        return value;
    }
    //遍历栈(出栈是从栈顶出，从栈顶往下遍历)
    public void list(){
        //判断栈是否为空
        if(isEmpty()){
            System.out.println("栈为空，没有数据~");
        }
        //需要从栈顶开始显示数据
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}