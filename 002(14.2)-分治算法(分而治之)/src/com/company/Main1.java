package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        //创建集合数组
        List<Integer> A=new ArrayList<Integer>();
        List<Integer> B=new ArrayList<Integer>();
        List<Integer> C=new ArrayList<Integer>();
        A.add(2);
        A.add(1);
        A.add(0);
        hanota(A,B,C);
    }

    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        movePlate(A.size(), A, B, C);
    }

    private static void movePlate(int sum, List<Integer> A, List<Integer> B, List<Integer> C) {
        //如果A为空
        if (sum == 0) {
            System.out.println("C=[]");
        }
        //如果只有一个盘
        if (sum == 1) {
            //将A集合中的这一个元素，添加到C中
            C.add(A.remove(A.size() - 1));
            //输出集合C
       /*
        遍历：集合专用的遍历方式：Iterator iterator()   、、Iterator 接口
         */
            Iterator it = C.iterator();//这里返回的是子类对象，这里是多态
            while (it.hasNext()) {
                Integer s1 = (Integer) it.next();
                System.out.println(s1);
            }

        } else {
            //当n>=2时，就体现啦分治算法的思想，可以将A塔上面的盘子看作是一个整体，将最下面的盘子分离出来
            //先将A塔上面的盘子移动到B盘，以C为中转站
            movePlate(sum - 1, A, C, B);
            //将A盘最下面的盘子移动到C塔
            C.add(A.remove(A.size() - 1));

            //在把B塔所有的盘移动到C盘,以A为中转站
            movePlate(sum - 1, B, A, C);
            //输出集合C
         /*
        遍历：集合专用的遍历方式：Iterator iterator()   、、Iterator 接口
         */
            Iterator it1 = C.iterator();//这里返回的是子类对象，这里是多态
            while (it1.hasNext()) {
                Integer s1 = (Integer) it1.next();
                System.out.println(s1);
            }
        }
    }
}
