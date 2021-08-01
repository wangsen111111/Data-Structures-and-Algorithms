package com.company;
/*
使用数组模拟队列
 */

import java.util.Scanner;

//测试类
public class ArrayQueueDemo {
    //测试类
    public static void main(String[] args) {
      //创建队列对象
       ArrayQueue queue= new ArrayQueue(3);
       //接受用户输入
        char key = ' '; //接收用户输入
        Scanner sc=new Scanner(System.in);
        //用while循环做一个菜单，可让用户选择
        boolean loop=true;
        while(loop){
            System.out.println("a(addQueue): 添加数据到队列");
            System.out.println("g(getQueue): 获取队列的数据，出队列");
            System.out.println("s(showQueue): 显示队列的所有数据");
            System.out.println("h(headQueue): 显示队列的头数据，注意不是取出数据");
            System.out.println("e(exit) :退出程序");
            System.out.println("请输入一个字符来进行你的操作：");
            key=sc.next().charAt(0);//接收一个字符
            switch(key){
                case'a':
                    System.out.println("请输入一个数：");
                    int value=sc.nextInt();
                    queue.addQueue(value);
                    break;
                case'g':
                    //取数据，可能队列为空，用try catch捕获异常
                    try{
                        int res=queue.getQueue();
                        System.out.printf("取出的数据为:%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                }
                    break;
                case's':
                    queue.showQueue();
                    break;
                case'h':
                    //显示队列的头数据，如果队列为空，用try，catch处理异常
                    try{
                        int res=queue.headQueue();
                        System.out.printf("取出的队列的头数据为：%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    //退出程序，先将输入关掉
                    sc.close();
                    loop=false;
                    break;
                default:
                    System.out.println("你输入的字符无效，请重新输入 ^_^");
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}
//使用数组模拟队列编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列
    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=-1;//指向队列头部，分析出front是指向队列头的前一个位置
        rear=-1;//指向队列尾部，分析出rear是指向队列尾的数据（即队列的最后一个位置）
    }
    //判断队列是否空
    public boolean isEmpty(){
        return rear==front;
    }
    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //添加数据到队列(a)
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
           System.out.println("队列已经满啦，不能再添加数据啦！");
        }
        //没满，添加数据
        rear++;
        arr[rear]=n;
    }
    //获取队列的数据，出队列(g)
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //取数据
        front++;//front后移
        return arr[front];
    }
    //显示队列的所有数据(s)
    public void showQueue(){
        //遍历
        //如果队列为空
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据，注意不是取出数据(h)
    public int headQueue(){
        //如果队列为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }
}

