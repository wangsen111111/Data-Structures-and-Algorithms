package com.company;
/*
黄金分割:是指将整体一分为二，较大部分与整体部分的比值等于较小部分与较大部分的比值，其比值约为0.618
FibonacciSearch:借助一个斐波那契数列来配合我们这个数组来找黄金分割点
 */

import java.util.Arrays;

public class 斐波那契查找算法 {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 20, 89, 1000, 1234};
        System.out.println(fibSearch(arr,89));
    }

    //因为后面mid=low+F(k-1)-1,需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法,使用非递归方式
     * 1，temp = {1,8, 10, 89, 1000, 1234, 0, 0} => {1,8, 10, 89, 1000, 1234, 1234, 1234}
     * 2，使用 while 来循环处理，找到我们的数 key
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//mid=low+F(k-1)-1
        int mid = 0;
        int[] f = fib();//获取斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //由于f[k]的值可能大于arr的长度，所以要将数组扩容
        int[] temp = Arrays.copyOf(arr, f[k]);
        //将数组后面扩容的数置为high
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while来循环处理，找到我们的数key
        while (low <= high) {
            mid = low + f[k] - 1;
            /**
             * 切分以后继续找黄金分割点： F[k]=F[k-1]+F[k-2];
             *     黄金分割点前面有k-1个元素，后面有k-2个元素
             *     黄金分割点为k
             *         如果该值在k前面，就套用黄金分割F[k-1]=F[k-1-1]+F[k-2-1] k--;
             *         如果该值在k后面，就F[k-2]=F[k-1-2]+F[k-2-2] k-=2;
             */
            if (key < temp[mid]) {//说明应该继续向数组左边查找
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                //找到
                if (mid < high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
