package com.company;

/*
题目5：合并两个有序的单链表，合并之后的链表依然有序
1，创建两个有序的链表
2,分析：创建一个新的链表，发现两个链表哪一个更小，就把它加入到新的链表
 */
public class singleLinkedListSolution {
    public static void main(String[] args) {
        //链表1
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(7, "林冲", "豹子头");
        //创建链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        //将节点加入到链表中，按照编号的顺序
        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero2);
        //链表2
        //先创建节点
        HeroNode hero11 = new HeroNode(2, "林允儿", "小美女");
        HeroNode hero22 = new HeroNode(4, "成德善", "大美女");
        HeroNode hero33 = new HeroNode(6, "李知恩", "大大美女");
        HeroNode hero44 = new HeroNode(8, "王祖贤", "大大大美女");
        //创建链表
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //将节点加入到链表中，按照编号的顺序
        singleLinkedList2.addByOrder(hero11);
        singleLinkedList2.addByOrder(hero22);
        singleLinkedList2.addByOrder(hero33);
        singleLinkedList2.addByOrder(hero44);
        System.out.println("链表1：");
        singleLinkedList1.list();
        System.out.println("链表2：");
        singleLinkedList2.list();
        //合并两个链表,节点
        System.out.println("链表1和链表2合并后如下：");
        HeroNode heroNode = mergeList(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        print(heroNode);
    }

    //遍历节点
    public static void print(HeroNode head) {
        while (head!= null) {
            head = head.next;
            if(head==null){
                return;
            }
            System.out.println(head);
        }
    }

    /**
     * 合并两个有序的单链表 使合并完成之后的新的链表依旧有序
     * @param hero1  链表1的头节点
     * @param hero2  链表2的头节点
     */
    public static HeroNode mergeList(HeroNode hero1, HeroNode hero2) {

        //创建一个新的创建一个新的链表头
        HeroNode hero3 = new HeroNode(0, "", "");
        //因为头节点不能动，所以我们需要两个辅助变量来遍历原来的两个链表
        HeroNode temp1 = hero1.next;
        HeroNode temp2 = hero2.next;
        HeroNode temp3 = hero3;

        HeroNode next1 = null;//指定链表1的下一个节点用于保存下一个节点
        HeroNode next2 = null;
        while (temp1 != null && temp2 != null) { //如果1和2链表都不为空
            if (temp1.no < temp2.no) {  //如果1的编号小于2，就将1加入到节点3的后面
                next1 = temp1.next;//先保存下一个节点
                temp3.next = temp1;//将较小的节点插入到新的节点后面
                temp3 = temp3.next;//将新的节点后移一位 注意:一定要后移，向后继续插入较小的节点
                temp1 = next1;//将链表1中的节点后移一位
            } else {
                next2 = temp2.next;
                temp3.next = temp2;
                temp3 = temp3.next;
                temp2 = next2;
            }
        }
        if (temp1 == null) { //如果链表1为空，链表2不为空，直接将链表2插入到temp3的后面
            while (temp2 != null) {
                next2 = temp2.next;
                temp3.next = temp2;
                temp3 = temp3.next;
                temp2 = next2;
            }
        } else {
            while (temp1 != null) {
                next1 = temp1.next;//先保存下一个节点
                temp3.next = temp1;//将较小的节点插入到新的节点后面
                temp3 = temp3.next;//将新的节点后移一位 注意:一定要后移
                temp1 = next1;//将节点后移一位
            }
        }
        return hero3;  //返回新的节点
    }
}
