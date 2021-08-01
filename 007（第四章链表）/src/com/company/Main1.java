package com.company;

import java.util.Stack;

public class Main1 {
    public static void main(String[] args) {


    }
    public class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseList(ListNode head) {
        if(head.next==null||head.next.next==null){
            //如果当前链表为空，或者只有一个节点，直接返回即可
            return head;
        }
        //创建一个新的链表头
        ListNode reverseHead=new ListNode(0);
        ListNode temp=head.next;
        ListNode next=null;//用来保存当前节点的下一个节点
        while(temp!=null){
            next=temp.next;//先保存当前节点的下一个节点
            //将取出来的节点插入到新的链表头和它的下一节点之间
            temp.next=reverseHead.next;
            reverseHead.next=temp;
            //让temp后移，继续遍历原链表的1节点
            temp=next;
        }
        //将新的链表头接到原链表头的后面
        head.next=reverseHead.next;
        return head;
    }
}