package com.company;
/*
1，单向链表，查找的方向只能是一个方向，而双向链表可以向前或向后查找
2，单向链表不能自我删除，需要借助辅助节点，而双向链表，则可以自我删除，所以前面单链表删除节点，总是找到temp，temp是待删除节点的前一个节点
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试:");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(4, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(2, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //将节点添加到双向链表的最后
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        //遍历双向链表
        System.out.println("添加节点到双向链表:(当考虑编号顺序时)遍历如下：");
        doubleLinkedList.list();

        //双向链表的修改思路和原理都和单向链表一样
        doubleLinkedList.update(new HeroNode2(4,"林冲","林大头"));
        System.out.println("修改后的双向链表为：");
        doubleLinkedList.list();
        //双向链表的删除
        System.out.println("删除后的双向链表为：");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }
    /**
     *  添加节点到双向链表:(当考虑编号顺序时)
     *  第二种方式再添加英雄时，根据排名将英雄插入到指定位置
     *  （如果有这个英雄排名，则添加失败，并给出提示）
     *  1，首先找到新添加节点的位置，是通过辅助变量(指针)，通过遍历来完成
     *  2. 将该英雄编号插入到temp和temp.next之间
     */
    public void addByOrder(HeroNode2 heroNode){
        //1，首先找到新添加节点的位置，是通过辅助变量(指针)，通过遍历来完成
        //因为head节点不能动，所以我们需要一个辅助遍历temp，从头开始遍历
        HeroNode2 temp=head;
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
        }else if(temp.next!=null){
            //待添加的节点不存在，且temp不是指向双链表的最后一个节点，将该编号插入到temp和temp.next之间(画个图即可)
            //使用一个变量来保存temp.next
            HeroNode2 count=temp.next;
            heroNode.next=count;
            count.pre=heroNode;
            temp.next=heroNode;
            heroNode.pre=temp;

        }else{
            //待添加的节点不存在，且temp指向双链表的最后一个节点
            temp.next=heroNode;
            heroNode.next=null;
            heroNode.pre=temp;
        }
    }

    /**
     * 双向链表的添加(默认添加到双向链表的最后)
     * 1，先找到双向链表的最后这个节点
     * 2，temp.next=newHeroNode
     * 3,newHeroNode.pre=temp
     * @param heroNode
     */
    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head头节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到链表最后位置
        while (true) {
            //如果到链表最后，就退出
            if (temp.next == null) {
                break;
            }
            //如果没到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        //形成一个双向来年表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //遍历双向链表的方法和单链表一样，只是可以向前，也可以向后查找
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来帮助我们来进行遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否已到链表最后
            if (temp== null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
    //修改一个节点的内容，和单链表一样
    public void update(HeroNode2 newHeroNode) {
       //判断链表是否为空
        if(newHeroNode==null){
            System.out.println("链表为空~");
            return;
        }
        //定义一个辅助变量
        HeroNode2 temp=head.next;
        //找到需要修改的节点，根据其no编号
        //表示是否找到该节点
        boolean flag=false;
        while(true){
            if(temp==null){
                //已经遍历完该链表
                break;
            }
            if(temp.no==newHeroNode.no){
                //找到需要修改的节点
                flag=true;
                break;
            }
            //将temp后移
            temp=temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else{
            //没有找到
            System.out.printf("没有找到编号为%d的节点，不能修改\n",newHeroNode.no);
        }
    }

    /**
     * 双向链表的删除
     * 1，因为是双向链表，因此我们可实现自我删除某个节点
     * 2，直接找到要删除的这个节点，比如temp
     * 3，temp.pre.next=temp.next
     * 4,temp.next.pre=temp.pre
     * @param no
     */
    public void delete(int no) {
        //判断当前链表是否为空
        if(head.next==null){
            System.out.println("链表为空，不能删除~");
            return;
        }
        //辅助变量
        HeroNode2 temp=head.next;
        boolean flag=false;//标记变量，标记是否找到待删除节点
        while(true){
            //已经到链表的最后
            if(temp==null){
                break;
            }
            if(temp.no==no){
                //找到待删除节点
                flag=true;
                break;
            }
            //将temp后移，进行遍历
            temp=temp.next;
        }
        //判断flag
        if(flag){
            temp.pre.next=temp.next;
            /*
            这里代码有问题：
               如果是最后一个节点，就不需要执行下面这句话，否则就会出现空指针异常
               是最后一个的话， temp.next==null
             */
            if(temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }
}

//定义一个HeroNode2类，每一个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;//编号
    public String name;//姓名
    public String nickname;//昵称
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向上一个节点，默认为null

    //构造器，构造一个节点
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重写toString方法

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
