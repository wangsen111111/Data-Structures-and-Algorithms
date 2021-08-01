package com.company;

import java.util.Scanner;

/**
 * HashTap:散列表(也叫哈希表)，是根据关键码值(Key value)而直接进行访问的数据结构，也就是说它通过吧关键码值映射到表中一个位置来访问记录，以加快查找
 *         的速度，这个映射函数叫做散列函数，存放记录的数组叫做散列表
 */

public class 使用哈希表来管理雇员信息 {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab=new HashTab(7);
        String key="";
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit: 退出系统");

            key=sc.next();
            switch(key){
                case"add":
                    System.out.println("输入id");
                    int id=sc.nextInt();
                    System.out.println("输入名字");
                    String name=sc.next();
                    //创建雇员
                    Emp emp=new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case"list":
                    hashTab.list();
                    break;
                case"find":
                    System.out.println("请输入要查找的id:");
                    id=sc.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case"delete":
                    System.out.println("请输入要删除的雇员id为：");
                    id=sc.nextInt();
                    hashTab.delete(id);
                    break;
                case"exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
//创建哈希表，用来管理多条链表
class HashTab{
    private int size;//表示共有多少条链表
    private EmpLinkedList[] empLinkedListArray;
    //构造器
    public HashTab(int size){
        this.size=size;
        //对数组进行初始化
        empLinkedListArray=new EmpLinkedList[size];//java.lang.NullPointerException
        //创建链表时，仅仅将外面的数组创建起来啦，里面的链表为空
        //勿忘：初始每一条链表
        for(int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNo=hashFun(emp.id);
        //将emp添加到对用的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //删除雇员
    public void delete(int id){
        //根据员工的id，得到该员工在哪条链表
        int empLinkedListNo=hashFun(id);
        //将emp删除
        empLinkedListArray[empLinkedListNo].delete(id);
    }


    //遍历所有的链表,遍历哈希表
    public void list(){
        for(int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }
    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定在哪条链表找
        int empLinkedListNO=hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到雇员id=%d\n",(empLinkedListNO+1),id);

        }else{
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    //来个链表，应该先根据id进行哈希，也就是散开，散列
    //散列函数，使用简单取模法
    public int hashFun(int id){
        return id%size;
    }



}
//创建一个EmpLinkedList,表示链表，链表里面存放数据
class EmpLinkedList{
    //头指针，指向第一个Emp，因此我们这个链表的head，时直接指向第一个Emp
    private Emp head;//默认为空
    //添加雇员到链表,直接添加到链表的最后即可
    //当添加雇员时，id是自增长，即id的分配是从小到大
    public void  add(Emp emp){
        //如果是添加第一个雇员
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp=head;
        while(true){
            if(curEmp.next==null){
                //说明到链表的最后
                break;
            }
            curEmp=curEmp.next;
        }
        curEmp.next=emp;
    }
    //删除雇员,删除链表节点
    public void delete(int no){
        //1，先找到需要删除的这个节点的前一个节点temp
        //定义一个辅助变量
        Emp temp=head;
        boolean flag=false;//表示是否找到待删除节点
        while(true){
            if(temp==null){
                //表示已经到了链表的最后
                break;
            }
            if (head.id==no){
                head=head.next;
                return;
            }
            if(temp.next.id==no){
                //找到了待删除节点的前一个节点temp,将其删除
                flag=true;
                break;
            }
            //如果没有找到最后，就将temp后移，遍历
            temp=temp.next;
        }
        //2，根据newHeroNode的no来修改即可
        //根据flag判断是否找到要修改的节点
        if(flag){
            //找到了，进行删除
            temp.next=temp.next.next;
        }else{
            //没有找到
            System.out.printf("要删除的节点%d不存在",no);
        }
    }


    //遍历链表的雇员信息
    public void list(int no){
        if(head==null){
            System.out.println("第"+(no+1)+"链表为空~");
            return;
        }
        System.out.print("第"+(no+1)+"链表的信息为：");
        Emp curEmp=head;
        while(true){
            System.out.printf("=> id=%d,name=%s\t\n",curEmp.id,curEmp.name);
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
    }
    //根据id查找雇员
    public Emp findEmpById(int id){
        //判断链表是否为空
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp=head;
        while(true){
            if(curEmp.id==id){
                //找到
                break;
            }
            //退出
            if(curEmp.next==null){
                //说明遍历完当前链表没有找到
                curEmp=null;//否则其就指向最后一个
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;
    //构造器
    public Emp(int id,String name){
        super();
        this.id=id;
        this.name=name;
    }
}
