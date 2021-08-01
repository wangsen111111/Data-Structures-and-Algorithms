package com.company;
/*
插入排序{2，3，4，5，6，1}当插入的数是较小的数时，后移的次数明显增多，对效率有影响
--》
希尔排序(shellSort)[缩小增量排序，尽量把小的数后前面排，避免一个小的数在后面要移动很多次]:
1，按下标的一定增量分组，对每组使用直接插入排序算法排序
2，随增量的减少，每组元素数量增多，当增量减到1，整个数组就分成一组，此时接近有序数组
3，最后对这个有序数组进行插入排序
 */
import java.util.Arrays;

public class 希尔排序8 {
    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        //shellSort(arr);
        shellSortMove(arr);
    }
    //交换法
    public static void shellSort(int[] arr){
        int temp=0;
        for(int gap=arr.length/2;gap>0;gap/=2) {
            //希尔排序第一轮排序
            //第一轮排序是将数组分成了5组，每组有两个元素，步长为5
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有元素，步长为5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上增量后的那个元素，就交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序增值为"+gap+"的数组排序后的结果为：" + Arrays.toString(arr));
        }
    }
    //由于直接插入是将要插入的数插入到有序表的适当位置，而上面的交换法，是将每组中的数据进行比较进行交换，对效率有影响
    //为提高效率，对交换式的希尔排序进行优化-》移动法
    public static void shellSortMove(int[] arr){
        //增量为gap，并逐个的缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for(int i=gap;i<arr.length;i++){
                //先保存要插入数的位置和值
                int j=i;
                int temp=arr[j];
                //找到要插入位置
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0&&temp<arr[j-gap]){
                        //就将这个数向前移动
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    //当退出while循环时，就找到temp要插入的位置
                    arr[j]=temp;
                }
            }
            System.out.println("希尔排序增值为"+gap+"的数组排序后的结果为：" + Arrays.toString(arr));
        }
    }
}
