package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

//dfs（把每一个？和*都填充1-16中没有出现的）+回溯,  然后4*4的格子填充完毕后，再去判断
public class SolutionMain {
    static int res; //最后*位置的值
    static int[] xing;  //保存*号位置的坐标
    static boolean[][] flag;   //flag[i][j]代表是否可以被设置
    static HashSet<Integer> set;    //代表已经拥有的数字,一开始初始化的数字
    static LinkedList<Integer> list;   //代表设置过程中已经设置的数字

    public static void main(String[] args) {
        String[][] chars = new String[][]{
                {"16", "?", "?", "13"},
                {"?", "?", "11", "?"},
                {"9", "?", "?", "*"},
                {"?", "15", "?", "1"}
        };
        res = 0;
        xing = new int[2];
        flag = new boolean[chars.length][chars[0].length];
        set = new HashSet<>();
        list = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j].equals("?")) {
                    flag[i][j] = true;
                } else if (chars[i][j].equals("*")) {
                    xing[0] = i;
                    xing[1] = j;
                    flag[i][j] = true;
                } else {
                    set.add(Integer.valueOf(chars[i][j]));
                }
            }
        }
        dfs(chars, 0, 0);
        System.out.println(res);
    }

    public static void dfs(String[][] arr, int i, int j) {
        if (res != 0) { //加快，省略找到后的速度
            return;
        }
        if (i == arr.length) {
            //现在去判断当前是否满足要求，如果满足，打印出来
            if (isOk(arr)) {
                for (String[] a : arr) {
                    System.out.println(Arrays.toString(a));
                }
                res = Integer.parseInt(arr[xing[0]][xing[1]]);
            }

            //这样可以打印所有排列组合
//            for(String[] a:arr){
//                System.out.println(Arrays.toString(a));
//            }
//            System.out.println();
            //现在代表填充完毕
            return;
        }
        if (!flag[i][j]) { //如果不能设置当前位置，直接去设置下一个地方
            if (j == arr[i].length - 1) {   //表示往右走到尽头，需要往下走
                dfs(arr, i + 1, 0);
            } else {    //表示可以往右走就往右走
                dfs(arr, i, j + 1);
            }
        } else { //如果能设置当前位置
            for (int k = 1; k <= 16; k++) { //如果当前位置能够设置，去设置当前位置为[1-16]其中一个
                if (set.contains(k) || list.contains(k)) {  //如果set和list已经设置了该值，表示就不能设置该值
                    continue;
                }
                arr[i][j] = String.valueOf(k);  //去设置当前位置的值为k
                list.addLast(k);    //去list尾部添加当前值
                if (j == arr[i].length - 1) {   //表示往右走到尽头，需要往下走
                    dfs(arr, i + 1, 0);
                } else {    //表示可以往右走就往右走
                    dfs(arr, i, j + 1);
                }
                list.removeLast();  //删除list尾部刚刚设置的值
            }
        }
    }
    /**
     *怎么去验证一个二阶数组的数据是不是一个有效的幻方构造数据呢？
     * 具体思路就是反证法，默认数据符合幻方数据内容
     */
    /**验证一个二阶数组里的数是不是符合一个幻方的要求*/
    public static boolean isOk(String[][] arr) {
        int[] lie = new int[arr[0].length];   //存储列的值
        int[] dui = new int[2]; //dui[0]代表包含0,0这条对角线,dui代表两条对角线
        //判断行是否相等
        int hang = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = 0;
            for (int j = 0; j < arr[i].length; j++) {
                temp += Integer.parseInt(arr[i][j]);
                lie[j] += Integer.parseInt(arr[i][j]);
                if (i == j) {
                    dui[0] += Integer.parseInt(arr[i][j]);
                }
                if (i + j == arr.length - 1) {
                    dui[1] += Integer.parseInt(arr[i][j]);
                }
            }
            if (hang != 0 && temp != hang) {
                return false;
            }
            hang = temp;
        }

        //判断列是否相等
        for (int value : lie) {
            if (value != hang) {
                return false;
            }
        }
        //判断对角线是否相等
        return dui[0] == hang && dui[1] == hang;
    }
}
