package com.company;

import com.sun.scenario.animation.shared.ClipEnvelope;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //升序排列，大顶堆
        int[] arr={4,6,8,5,9};
        heapSort(arr);

    }
    //堆排序
    public static void heapSort(int[] arr){
        System.out.println("堆排序~");
        //1，将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i, arr.length);
        }
        int temp=0;
        //2，将堆顶元素与末尾元素互换，将最大元素沉到数组末端
        //3，重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整交换步骤，直到整个序列有序
        for(int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            //从顶上找
            adjustHeap(arr,0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 完成将以将以i对应的非叶子结点的数调整成大顶堆
     * @param arr
     * @param i 非叶子结点在数组中的索引
     * @param length 表示对多少个元素调整，length是在逐步的减少
     */
    //将一个数组(二叉树)调整成一个大顶堆
    public static void adjustHeap(int[] arr, int i, int length){
        int temp=arr[i];//先取出当前元素的值
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length&&arr[k]<arr[k+1]){
                k++;
            }
            if(arr[k]>temp){
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的书的最大值，放在了最顶(局部)
        arr[i]=temp;
    }
}
