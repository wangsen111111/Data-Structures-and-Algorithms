package com.company;
/*
分治法，分治算法（“分而治之”）【汉诺塔】，就是把一个复杂的问题分成两个或更多的相同或相似的子问题，--》
再把子问题分成更小的子问题……--》直到最后子问题可以简单的直接求解，--》原问题的解即子问题的解的合并。
分治算法基本步骤：
 分治法在每一层递归上都有三个步骤：
     分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
     解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
     合并：将各个子问题的解合并为原问题的解。
分治算法【汉诺塔问题】：
      （1）如果只有一个盘子  直接将 A 塔的盘子移动到 C 塔：A —> C
      （2）当n >= 2 时，就体现出了分治算法的思想：我们将 A 塔上面的盘子看作一个整体，最下面的单个盘子单独分离出来
          1，先将 A 塔上面的盘子看作一个整体，移动到 B 塔（把 C 塔当做中转站）    hanoitower(num-1,A,C,B);
          （先把最上面的盘A——》B）
          2，这样 A 塔就只剩下一个最大的盘子，将 A 塔剩下的盘子移动到 C 塔       System.out.println("第"+num+"个盘:" + A + "->" + C);
          （把最下面的盘A——》C）
          3，最后将 B 塔上面的盘子移动到 C 塔（把 A 塔当做中转站）              hanoitower(num-1,B,A,C);
           （把B塔所有的盘从B——》C）
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入盘子数目为：");
        int num = sc.nextInt();
        String A="left";
        String B="mid";
        String C="right";
        hanoitower(num, A, B, C);
    }
    //栈帧
    private static void hanoitower(int num, String A, String B, String C) {
        if (num == 1) {
            //如果有一个盘子
            System.out.println("第1个盘:" + A + "->" + C);
        } else {
            //如果是n>=2，我们总是可以看作是两个盘，1：最下边的一个盘----2：上面的所有盘
            //先把最上面的所有盘从a->b
           hanoitower(num-1,A,C,B);
           //把最下边的一个盘从A->C
           System.out.println("第"+num+"个盘:" + A + "->" + C);
           //把B塔的所有盘从B->C
            hanoitower(num-1,B,A,C);
        }
    }
}
