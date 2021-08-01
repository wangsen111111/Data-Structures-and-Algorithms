package com.company;

public class Solution {
    //先定义一个max来表示有几个皇后
    int max=8;
    //创建一个一维数组，arr[i] = val , val 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列
    //理论上应创建一个二维数组来表示棋盘，但其实可用一个一维数组来解决问题
    int[] array=new int[max];
    public static void main(String[] args) {
        //创建对象调用方法
        递归八皇后问题 queue8 =new 递归八皇后问题();
        queue8.check(0);
    }
    //编写一个方法来放第n个皇后
    void check(int n){
        //如果n为8，就表示8个皇后已经放好，就将其打印，并退出
        if(n==8){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i=0;i<max;i++){
            //先将当前这个皇后n放在第一列
            array[n]=i;
            //判断第n个皇后放在第i列时是否冲突
            if(judge(n)){//不冲突时
                //接着放第n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突的话，就继续执行array[n]=i;将第n个皇后放在第i+1列，即本行后移的一个位置
        }
    }
    //查看我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n){
        for(int i=0;i<n;i++){
            //由于每次放第n个皇后其行都在加1，故不需要判断其是否在同一行
            //arr[i]==arr[n]表示第n个皇后和第i个皇后在同一行
            //|arr[n]-arr[i]|=(n-i)表示第n个皇后和第i个皇后在同一斜线上
            if(Math.abs(array[n]-array[i])==Math.abs(n-i)||array[i]==array[n]){
                return false;
            }
        }
        return true;
    }
    //写一个方法可以将皇后的摆放的位置输出
    private void print(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
