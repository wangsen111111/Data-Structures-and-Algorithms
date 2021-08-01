package com.company;
/*
radixSort:(桶排序的扩展)它是通过键值各个位的值，将要排序的元素分配到某些桶中，达到排序
          将整数按位数切割成不同的数字，然后按每个位数分别比较
基本思想：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零(007)
        然后从最低位开始，依次进行一次排序，这样从最低位排序-->最高位排序完成，得到有序序列
步骤：
1,将每个数放到桶中(针对每个元素的个位进行排序处理)
2，按照这个桶的顺序(一维数组的下标)依次取出数据，放入原来数组
 */
import java.util.Arrays;

public class 基数排序11 {
    public static void main(String[] args) {
        int[] arr={53,3,542,748,14,214};
        radixSort(arr);
    }
    //基数排序方法
    public static void radixSort(int[] arr) {
        ////1，求出最大位数,最大位数决定有几轮
        int max=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        int maxLength=(max+"").length();


        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];//经典以空间换时间算法
        //第一轮(针对每个元素的个位数进行排序并将其放入对应桶中)
        int[] bucketElementCounts = new int[10];//bucketElementCounts[0]表示bucket[0]桶中元素数量
        for(int k=0,n=1;k<maxLength;k++,n*=10) {
            //1,将每个数放到桶中(针对每个元素的个位进行排序处理)
            for (int i = 0; i < arr.length; i++) {
                //取出每个元素个位的值
                int digitOfElement = arr[i] /n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            //2，按照这个桶的顺序(一维数组的下标)依次取出数据，放入原来数组
            //遍历每一个桶，并将桶中数据放入到原来的数组
            int index = -1;
            for (int i = 0; i < bucket.length; i++) { //第几个桶
                //如果桶中有数据，就将其放入到原数组
                if (bucketElementCounts[i] != 0) {
                    for (int j = 0; j < bucketElementCounts[i]; j++) {  //桶中第几个元素
                        index++;
                        arr[index] = bucket[i][j];
                    }
                }
                //第一轮处理后，需要将每个bucketElementCounts置为0，将桶中数据清空
                bucketElementCounts[i] = 0;//方便第二轮处理
            }
            System.out.println("对个位的排序处理为arr=" + Arrays.toString(arr));
        }
    }
}
