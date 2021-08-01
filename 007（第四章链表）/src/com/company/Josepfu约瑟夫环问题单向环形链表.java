package com.company;

public class Josepfu约瑟夫环问题单向环形链表 {
    public static void main(String[] args) {
        //创建单向环形链表
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        //给单向环形链表添加5个小孩
        circleSingleLinkedList.addBoy(5);
        //遍历单向环形链表
        circleSingleLinkedList.showBoy();
        //根据用户输入生成小孩出圈顺序(约瑟夫环问题)
        System.out.println("小孩出圈顺序如下：");
        circleSingleLinkedList.countBoy(1,2,5);
    }
}
//创建一个单向的环形链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first=null;
    //添加小孩节点，构成一个环形的链表
    /**
     * 构建一个单向环形链表的思路
     * 1，先创建第一个节点，让first指向该节点，并形成环形
     * 2，后面当我们每创建一个新的节点，就把该节点加入到已有的环形链表中即可
     * @param nums 代表环形链表有几个小孩
     */
    public void addBoy(int nums){
        //nums做一个数据校验
        if(nums<1){
            System.out.println("nums的值不正确~");
            return;
        }
        //辅助指针，帮助构建环形链表，因为first头指针不能动
        Boy curBoy=null;
        //根据for，将nums个小孩添加到单向环形链表中,因为已经创建了一个null的first指针，如果for从0开始会出现空指针异常
        for(int i=1;i<=nums;i++){
            //根据编号创建小孩节点,,第几个小孩
            Boy boy=new Boy(i);
            if(i==1){
                //如果是第一个小孩,单独考虑，形成环状
                first=boy;//让first节点指向boy
                first.setNext(first);//让first指向first构成一个环状
                curBoy=first;//让curBoy指向first节点，first不能动
            }else{
                curBoy.setNext(boy);//先让curBoy指向boy节点
                boy.setNext(first);//再让boy节点next指向first节点
                curBoy=boy;//让curBoy指向boy节点，因为后面可能还有节点
            }
        }
    }
    /**
     * 遍历环形链表
     * 1，先让一个辅助指针(变量)curBoy，指向first节点
     * 2，然后通过一个while循环遍历该环形链表即可，curBoy.next=first结束
     */
    public void showBoy(){
        //判断链表是否为空
        if(first==null){
            System.out.println("没有任何小孩~~");
            return;
        }
        //因为first不能动，所以我们需要一个辅助指针来帮助我们进行遍历
        Boy curBoy=first;
        while(true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if(curBoy.getNext()==first){
                //说明已遍历完毕
                break;
            }
            //将curBoy后移
            curBoy=curBoy.getNext();
        }
    }
    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * 1，需要创建一个辅助指针(变量)helper,事先应该指向环形链表的最后这个节点
     *    当小孩报数前，先让 first 和 helper 移动(startNo-1)次     先让first和helper移动到开始数的的地方
     * 2，当小孩报数时，让first和helper指针同时移动(countNum-1)次    再让first和helper移动几下
     * 3，这时就可以将first指向的小孩节点出圈
     *   (1)first=first.getNext();
     *   (2)helper.setNext(first);
     * @param startNo  从第几个小孩开始数的
     * @param countNum  数几下
     * @param nums   有几个小孩
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if(first==null||startNo<1 ||startNo>nums){
            System.out.println("输入参数有误，请重新输入~");
            return;
        }
        //1，需要创建一个辅助指针(变量)helper,事先应该指向环形链表的最后这个节点
        Boy helper=first;
        while(true){
            if(helper.getNext()==first){
                //说明helper已经指向最后小孩节点
                break;
            }
            //helper没指向环形链表的最后那个节点，将helper后移
            helper=helper.getNext();
        }
        //2，先让first和helper移动到开始数的的地方(当小孩报数前)
        for(int i=0;i<startNo-1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //3，当小孩报数时，让first和helper指针同时移动(countNum-1)次, 然后出圈
        //这是一个循环操作，直到圈中只剩下一个节点
        while(true){
            if(helper==first){
                //圈中只剩下一个节点
                break;
            }
            //当小孩报数时，让first和helper指针同时移动(countNum-1)次
            for(int i=0;i<countNum-1;i++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点就是要出圈的小孩节点
            System.out.printf("编号为%d的小孩出圈\n",first.getNo());
            //这时就可以将first指向的小孩节点出圈 *(1)first=first.getNext() (2)helper.setNext(first);
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为：%d\n",first.getNo());
    }
}
//创建一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//默认为null，指向下一个节点
    //构造器
    public Boy(int no) {
        this.no=no;
    }
    //生成set和get方法

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}