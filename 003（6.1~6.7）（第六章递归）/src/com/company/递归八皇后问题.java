package com.company;

/*
八皇后问题：任意两个皇后都不能处于同一行、 同一列或同一斜线上，问有多少种摆法(92)
八皇后问题算法思路分析：
 1) 第一个皇后先放第一行第一列
 2) 第二个皇后放在第二行第一列(编写一个方法，放第n个皇后)、然后判断是否 OK(查看我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突)， 如果
    不 OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 3) 继续第三个皇后，还是第一列、第二列……直到第 8 个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4 的步骤
 6) 示意图:递归八皇后问题图解
 说明： 理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] = {0, 4, 7, 5, 2, 6, 1, 3}
  //对应 arr 下标 表示第几行，即第几个皇后，{arr[i] = val , val 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列}

 */
public class 递归八皇后问题 {
    //先定义一个max来表示有几个皇后
    int max=8;
    //定义一个计数变量来统计打印了多少次，即就是有多少种解法
    static int count=0;
    //定义一个计数变量judgeCount来统计一共判断了多少次，
    static int judgeCount=0;
    //创建一个一维数组，arr[i] = val , val 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列
    //理论上应创建一个二维数组来表示棋盘，但其实可用一个一维数组来解决问题
    int[] array=new int[max];
    public static void main(String[] args) {
    //创建对象调用方法
        递归八皇后问题 queue8 =new 递归八皇后问题();
        queue8.check(0);
        System.out.println("8皇后的解法一共有："+count+"种解法");
        System.out.println("一共判断冲突的次数为%d："+judgeCount+"次");
    }
    //编写一个方法来放第n个皇后
    void check(int n){
        //如果n为8，就表示8个皇后已经放好，就将其打印，并退出
        if(n==8){
            print();
            count++;
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
        judgeCount++;
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
