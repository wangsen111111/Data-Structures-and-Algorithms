package com.company;

import java.util.Scanner;

//测试类
public class CircleArrayQueueDemo {
    //测试类
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~");
        //创建环形队列对象
        /**
         * 设置为4，其队列的有效数据最大是3，因为预留啦一个空间作为约定
         */
        ArrayQueue1 queue= new ArrayQueue1(4);
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
                        System.out.printf("队列的头数据为：%d\n",res);
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
class ArrayQueue1{
    private int maxSize;//表示数组最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素 //front 的初始值 = 0
    private int front;//队列头
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定. //rear 的初始值 = 0
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列
    //创建队列的构造器
    public ArrayQueue1(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=0;
        rear=0;
    }
    //判断队列是否空
    public boolean isEmpty(){
        return rear==front;
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    //添加数据到队列(a)
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列已经满啦，不能再添加数据啦！");
        }
        //因为rear本身就指向后一个元素，所以直接将数据加入就可以啦
        arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;//按以前那种，数组可能越界
    }
    //获取队列的数据，出队列(g)
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //取数据
        //这里需要分析出front是指向队列的第一个元素
        /**
         * 1，先把front对应的值保留到一个临时变量
         * 2，将front后移(考虑取模)，直接返回的话，front就没有往后移的机会啦
         * 3，将临时保存的变量返回
         */
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列的所有数据(s)
    public void showQueue(){
        //遍历
        //如果队列为空
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        /*
        //这样写是把这个数组打印出来，有可能前面数据已经被取啦，打出来无意义
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
         */
        /**
         * 思路：从front开始遍历，遍历多少个元素
         *     环形队列元素一共有：(rear+maxSize-front)%maxSize 个
         */
     for(int i=front;i<front+(rear+maxSize-front)%maxSize;i++){
            //这个时候的数组下表应该为：i%maxSize
            //这个时候的数组元素应该为：arr[i%maxSize]
         System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }
    //显示队列的头数据，注意不是取出数据(h)
    public int headQueue(){
        //如果队列为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}


