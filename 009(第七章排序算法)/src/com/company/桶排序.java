package com.company;
/*
桶排序bucketSort：桶排序的思想是划分区间（桶），后一个区间中的数总是比前一个区间的大
                例如按薪水排序，处于月薪数W元区间的任何一个人的薪水肯定比处于月薪数K元区间的所有人多。
       分好桶后，再选择某一种算法对这个桶内的元素排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。最后根据桶的优先级依次取出元素，
1，初始化桶
2，读取数据，加入到相应的桶中
3，遍历每个桶，依次读取数据即可
 */
public class 桶排序 {
    public static void main(String[] args) {
        //对每个桶进行初始化
        int[] arr=new int[11];
        int[] studentArr={1,3,5,3,8};//学生成绩
        //将5个学生的分数加入到桶中
        for(int i=0;i<5;i++){
            arr[studentArr[i]]++;//将每个学生分数对应桶的序号加1
        }
        //遍历桶，依次读取即可
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i];j++){
                System.out.print(i);
            }
        }
    }
}
