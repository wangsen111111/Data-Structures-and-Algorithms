package com.company;

import java.util.Scanner;

/*
队列（银行排队叫号系统）：队列是一个有序列表，可以用数组（顺序存储）和链表(链式存储)来实现
原则：先入先出，先存入队列的数据，要先取出
 */
//测试类
public class Main {

    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案列~~");
        //创建环形队列对象
        /**
         * 设置为4，因为预留啦一个空间，所以有效数据最大是3
         */
        CircleArrayQueue queue=new CircleArrayQueue(4);
        //接受用户输入
        char key=' ';
        //创建输入流对象
        Scanner sc=new Scanner(System.in);
        //设计界面，使用while做一个菜单，让用户可以一直选
        boolean loop=true;
        while(loop){
            System.out.println("a(addQueue): 添加数据到队列");
            System.out.println("g(getQueue): 获取队列的数据，出队列");
            System.out.println("s(showQueue): 显示队列的所有数据");
            System.out.println("h(headQueue): 显示队列的头数据，注意不是取出数据");
            System.out.println("e(exit) :退出程序");
            //输入字符
            System.out.println("请输入一个字符来进行你的操作：");
            key=sc.next().charAt(0);
            switch(key){
                case'a':
                    System.out.println("请输入你要添加的数据为：");
                    queue.addQueue(sc.nextInt());
                    break;
                case'g':
                    //用try catch处理异常
                    try{
                        System.out.printf("取出的数据为：%d\n",queue.getQueue());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case's':
                    queue.showQueue();
                    break;
                case'h':
                    //用try catch处理异常
                    try{
                        System.out.printf("队列的头数据为：%d\n",queue.headQueue());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    //先关闭输入流通道
                    sc.close();
                    loop=false;
                    break;
                default:
                    System.out.println("你输入的字符无效，请重新输入 ^_^");
                    break;
            }
        }
        System.out.println("程序退出！~");
    }
}
//把数组模拟成一个环形队列，将数组使用算法，该进成一个环形的队列，取模；%，环形数组队列类
class CircleArrayQueue{
    private int maxSize;//数组最大容量
    private int rear;//指向队列为尾，指向队列最后一个元素的后一个位置，因为希望空出一个位置作为约定
    private int front;//指向队列头，front指向队列的第一个元素
    private int[] arr;//该数据用于存放数据，模拟队列
    //创建队列构造器
    public CircleArrayQueue(int MaxSize){
        maxSize=MaxSize;
        arr=new int[maxSize];
        //队列头与尾，初始值都为0
        rear=0;
        front=0;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //判断队列是否满
    public boolean isFull(){
        //rear指向队列尾，空出一个位置作为约定
        return (rear+1)%maxSize==front;
    }
    //给队列添加元素
    public void addQueue(int n){
        //添加元素，如果队列满啦，就输出一句话
        if(isFull()){
            System.out.println("队列已满,无法进行添加！");
        }
        //添加元素，将rear尾向后移，由于rear本身就指向队列的后一个位置，所以直接将数据添加即可
        arr[rear]=n;
        //添加完之后将rear后移，注意取模
        rear=(rear+1)%maxSize;//按以前那种，rear指针可能发生溢出
    }
    //拿出队列中的元素，出队列
    public int getQueue(){
        //拿元素，可能出现数组为空，这里抛出一个异常
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        }
        /**
         * 出队列：  front就指向队列的第一个元素
         * 1，先将front对应的值保留到一个临时变量
         * 2，将front后移（考虑取模），直接返回的话，front就没往后移的机会啦
         * 3，将临时变量返回
         */
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列的所有数据
    public void showQueue(){
        //如果队列为空
        if(isEmpty()){
            System.out.println("队列为空，没有数据！");
            return;
        }
        /*
        //这样写是把这个数组打印出来，有可能前面数据已经被取啦，打出来无意义
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
         */
        /**
         * 显示队列所有的数据时，一共要遍历多少个元素：
         *  环形队列一共有：(rear+maxSize-front)%maxSize个元素
         */
        for(int i=front;i<front+(rear+maxSize-front)%maxSize;i++){
            //注意：下表要取模才是数据的下标，i%maxSize
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //显示队列的头数据
    public int headQueue(){
        //如果队列为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front];
    }


}
