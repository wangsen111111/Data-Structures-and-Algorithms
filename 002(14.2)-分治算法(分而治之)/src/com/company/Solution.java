package com.company;

import jdk.nashorn.internal.codegen.Compiler;

import java.util.ArrayList;
import java.util.Iterator;
class Solution{
    public static class Hanoi {
        public static  void main(String[] args) {
            getSolution(3);

        }
        public static void getSolution(int n) {
            String A="left";
            String B="mid";
            String C="right";
            //创建集合数组
            ArrayList<String> array=new ArrayList<String>();
            hanoitower(n,A,B,C,array);
            Iterator<String> it=array.iterator();
            while(it.hasNext()){
                String s=(String)it.next();
                System.out.println(s);
            }
        }
        //返回一个Arraylist集合
        private static void  hanoitower(int n,String A,String B,String C,ArrayList<String> array){
            if(n==1){
                //如果只有一个盘子，就将其添加到集合
                array.add("move from "+A+" to "+C);
            }else{
                //如果是n>=2，我们总是可以看作是两个盘，一个是最下面的盘，另一个是上面所有的盘        }
                //先把上卖弄的所有的盘从A移动到B，以C为中转站
                hanoitower(n-1,A,C,B,array);
                //将A盘最下面的那一个盘移动到C,并将其添加到集合
                array.add("move from "+A+" to "+C);
                //再将B盘所有的盘从B移动到C,以A为中转站
                hanoitower(n-1,B,A,C,array);
            }
        }
    }
}