package com.company;

import java.util.Stack;

/**
 * 使用带 head 头的单向链表实现 –水浒英雄排行榜管理完成对英雄人物的增删改查操作
 */
public class 不考虑编号顺序时添加英雄 {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1=new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        //将节点加入到链表中
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);
        //将节点加入到链表中，按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        //测试修改节点的代码
        HeroNode newHeroNode=new HeroNode(1, "宋江~", "及时雨~");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.delete(3);
        //显示英雄信息
        singleLinkedList.show(4);
        //显示链表
        singleLinkedList.list();
        System.out.println("问题1：单链表中有效节点的个数为："+getLength(singleLinkedList.getHead()));
        System.out.println("问题2：单链表中的倒数第"+2+"个节点:"+findLastIndexNode(singleLinkedList.getHead(),2));
        System.out.println("问题3：将单链表反转如下：");
        reverseList(singleLinkedList.getHead());
        //显示反转后的链表
        singleLinkedList.list();
        System.out.println("问题4:从尾到头打印单链表如下：");
        reversePrint(singleLinkedList.getHead());
    }
    /**
     * 问题1：求单链表中有效节点的个数
     * 方法：获取到单链表有效节点的个数(如果是带头节点的链表，需要不统计头节点)
     * @param head 链表的头节点
     * @return  返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        //如果链表为空
        if(head.next==null){
            return 0;
        }
        //定义一个计数变量length来统计来年表有效节点的个数
        int length=0;
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        //不统计头节点head.next
        HeroNode temp=head.next;
        while(temp!=null){
            //没有到最后，遍历链表，得到每一个节点length++
            length++;
            //将temp后移
            temp=temp.next;
        }
        return length;
    }
    /**
     * 问题2：查找单链表中的倒数第k个节点
     * 分析：倒数第k个节点即就是寻找第：（单链表有效结点的个数-k）个节点，返回这个节点即可
     *     1,编写一个方法，来接收头节点和索引
     *     2，遍历链表，找到(单链表有效结点的个数-k)个节点，并将其返回即可
     * @param head  头节点
     * @param index  倒数第k个节点
     * @return   该节点
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //先判断链表是否为空，为空的话，返回一个null
        if(head.next==null){
            return null;
        }
        //第一个遍历得到链表的长度
        int size=getLength(head);
        //先做一个index的校验
        if(index<=0||index>size){
            return null;
        }
        //2，遍历链表，找到(单链表有效结点的个数-k)个节点，并将其返回即可
        //该节点的索引就为：getLength(head)-index
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        //不统计头节点head.next
        HeroNode temp=head.next;
        //遍历链表得到第getLength(head)-index个节点
        for(int i=0;i<getLength(head)-index;i++){
            //没有到该节点，将temp后移
            temp=temp.next;
        }
        //返回该节点
        return temp;
    }

    /**
     * 问题3：将单链表反转
     * @param head 原头节点  reverseHead新头节点
     * 思路：1，遍历单链表拿出每一个节点
     *      2，创建一个新的链表头reverseHead=new HeroNode();
     *      3.将原单链表中拿出的节点，添加到新的链表头reverseHead中的最前端
     *             接着让temp指向原头的下一个节点
     *      4,再让原来的链表头指向新的链表头的下一个节点
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，直接返回即可
        if(head.next==null||head.next.next==null){
            return;
        }
        // 2，创建一个新的链表头reverseHead
        HeroNode reverseHead=new HeroNode(0,"","");
        //1，遍历单链表拿出每一个节点
        //因为头节点不能动，所以我们需要一个辅助变量来遍历原来的链表
        HeroNode temp=head.next;
        HeroNode next = null;// 指向当前节点[temp]的下一个节点
        while(temp!=null){
            //拿走了temp节点，而没有记录下一节点，单链表就断开了，无法进行下面拿原单链表中的节点
            next = temp.next;//先暂时保存原头的下一个节点，因为后面需要使用
            //将摘下来的节点放在新的链表和它下一个节点之间
            //将temp的下一个节点指向新的链表头的最前端
            temp.next=reverseHead.next;
            //将temp连接到新的链表头上
            reverseHead.next=temp;
            //让next后移，继续遍历原头后面的节点
            temp=next;
        }
        //4,再让原来的链表头指向新的链表头的下一个节点
        //将head.Next指向新的链表头，实现单链表的反转
        head.next=reverseHead.next;
    }
    /**
     * 问题4:从尾到头打印单链表
     * 方式一：先将单链表进行反转操作，然后再遍历即可（会破坏原来单链表的结构）
     * 方式二:利用栈(Stack)这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     * 1,创建一个栈stack
     * 2，遍历单链表得到每一个节点，将每一个节点添加到栈中（入栈）
     * 3，遍历栈，出栈
     */
    public static void reversePrint(HeroNode head){
        //如果是空链表不能打印
        if(head.next==null){
            return;
        }
        //1,创建一个栈stack
        Stack<HeroNode> stack=new Stack<HeroNode>();
        // 2，遍历单链表得到每一个节点，将每一个节点添加到栈中（入栈）
        //因为头节点不能动，所以需要一个辅助变量来遍历链表
        HeroNode temp=head.next;
        //入栈
        while(temp!=null){
            //2，遍历单链表得到每一个节点，将每一个节点添加到栈中（入栈）
            stack.add(temp);
            //将temp后移
            temp=temp.next;
        }
        //出栈
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
//定义 SingleLinkedList管理HeroNode节点对象
class SingleLinkedList{
    //先创建一个头节点，作用就是表示单链表的头，头节点不动，不存放具体的数据
    private HeroNode head=new HeroNode(0,"","");
    //返回一个头节点，用来解决问题一
    public HeroNode getHead(){
        return head;
    }
    /**
     * 添加节点到单向链表:(当不考虑编号顺序时)
     * 1，找到当前链表的最后节点
     * 2，将最后这个节点的next指向新的节点
     */
    /**
    单向链表的创建，并直接加入到链表的尾部
    第一种方法添加英雄，直接将英雄添加到链表的尾部(按照添加的顺序添加，也就是说这个add没有考虑编号)
    */
    public void add(HeroNode heroNode){
        //1,找到当前链表的最后节点
        //因为head节点不能动，所以我们需要一个辅助遍历temp，从头开始遍历
        HeroNode temp=head;
        //遍历链表，找到最后
        while(true){
            //如果链表为空，表明已经找到了最后
            if(temp.next==null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向链表的最后，将最后这个节点的next执行那个新的节点heroNode
       temp.next=heroNode;
    }

    /**
     *  添加节点到单向链表:(当考虑编号顺序时)
     *  第二种方式再添加英雄时，根据排名将英雄插入到指定位置
     *  （如果有这个英雄排名，则添加失败，并给出提示）
     *  1，首先找到新添加节点的位置，是通过辅助变量(指针)，通过遍历来完成
     *  2. 将该英雄编号插入到temp和temp.next之间
     */
    public void addByOrder(HeroNode heroNode){
        //1，首先找到新添加节点的位置，是通过辅助变量(指针)，通过遍历来完成
        //因为head节点不能动，所以我们需要一个辅助遍历temp，从头开始遍历
        HeroNode temp=head;
        //因为是单链表，所以我们找的temp是位于添加位置的前一个节点，否则插入不了
        boolean flag=false;//flag标志添加的编号是否存在，默认为false
        //while循环来进行遍历,找到新添加节点的位置
        while(true){
            if(temp.next==null){
                //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no>heroNode.no){
                //位置找到，就在temp的后面插入
                //该值小于temp.next.no，说明该值就在temp和temp.next之间，直接插入到它们之间即可
                break;
            }else if(temp.next.no==heroNode.no){
                //说明希望添加的heroNode的编号已经存在了
                flag = true;//说明编号存在
                break;
            }
            //将temp后移，遍历当前链表，继续找看是否有符合上述条件的
            temp=temp.next;
        }
        //判断flag的值
        if(flag){
            //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",heroNode.no);
        }else{
            //2.插入到链表中, temp 的后面,将该编号插入到temp和temp.next之间
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改，no编号改相当于添加数据
     * 1.找到需要修改的节点，根据no编号
     * 2，根据newHeroNode的no来修改即可
     */
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空~");
        }
        //1.找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false;//表示是否找到该节点
        while(true){
            if(temp==null){
                //表示已经遍历完链表
                break;
            }
            if(temp.no==newHeroNode.no){
                //找到了该节点
                flag=true;
                break;
            }
            //如果没有找到最后，就将temp后移，遍历
            temp=temp.next;
        }
        //2，根据newHeroNode的no来修改即可
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else{
            //没有找到
            System.out.printf("没有找到编号%d的节点，不能修改\n",newHeroNode.no);
        }
    }

    /**
     *删除节点思路
     * 分析：head头节点不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 1，先找到需要删除的这个节点的前一个节点temp
     *    说明我们在比较时，是temp.next.no和需要删除的节点的no进行比较
     * 2，temp.next=temp.next.next;
     * 说明：被删除的节点，将不会有其它引用指向，会被垃圾回收机制回收
     */
    public void delete(int no){
        //1，先找到需要删除的这个节点的前一个节点temp
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false;//表示是否找到待删除节点
        while(true){
            if(temp==null){
                //表示已经到了链表的最后
                break;
            }
            if(temp.next.no==no){
                //找到了待删除节点的前一个节点temp
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
    /**
     * 根据no查看英雄信息
     */
    public void show(int no){
        //定义一个辅助变量
        HeroNode temp=head.next;
        while(true){
            if(temp==null){
                System.out.println("该英雄不存在~");
                //表示已经到了链表的最后
                break;
            }
            if(temp.no==no){
                //找到了该英雄节点，将其信息输出
                System.out.println("编号："+no+" 姓名："+temp.name+" 昵称："+temp.nickname);
                break;
            }
            //如果没有找到最后，就将temp后移，遍历
            temp=temp.next;
        }
    }
    //显示链表，遍历
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空！");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode temp=head.next;
        while(true){
            //判断链表是否到最后
            if(temp==null){
                return;
            }
            //输出节点的信息，并将辅助变量后移
            System.out.println(temp);
            temp=temp.next;
        }
    }
}
//定义HeroNode,每一个HeroNode的对象，就是一个节点
class HeroNode{
    public int no;//英雄编号
    public String name;//英雄名字
    public String nickname;//英雄昵称
    public HeroNode next;//指向下一个节点

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

    //构造器，构造一个节点
    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
        //为了显示节点，重写toString方法
    }
}
