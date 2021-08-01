package com.company;

import javafx.scene.control.OverrunStyle;

import javax.swing.*;
import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不连通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建mGraph的对象
        MGraph mGraph=new MGraph(data.length);
        //创建一个MinTree对象
        MinTree minTree=new MinTree();
        minTree.createGraph(mGraph,data.length,data,weight);
        //输出
        minTree.showGraph(mGraph);
        //测试prim算法
        minTree.prim(mGraph,0);

    }
}
//创建最小生成树,村庄的图
class MinTree{
    //创建图的邻接矩阵
    //graph-》图对象 ，verxs-》图对应的节点个数 ，data-》图的各个节点的值，weight-》图的邻接矩阵
    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        for(int i=0;i<verxs;i++){
            graph.data[i]=data[i];
            for(int j=0;j<verxs;j++){
                graph.weight[i][j]=weight[i][j];
            }
        }
    }
    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for(int[] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }
    //编写prim算法，得到最小生成树
    // graph->图   v->表示图的第几个顶点开始生成‘A’=0，‘B’=1  ————
    public void prim(MGraph graph,int v){
        //visited[]标纪节点是否被访问过
        int[] visited=new int[graph.vers];
        //把当前这个节点标记为已访问
        visited[v]=1;
        //h1和h2记录最短权值的两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;//将minWeight初始化成一个大叔，后面在遍历的过程中，会被替代
        for(int k=1;k<graph.vers;k++){
            //因为有graph.verxs个节点，普利姆算法结束后，有graph.verxs-1条边

            //这个是确定每一次生成的子图，和哪个节点的距离最近
            for(int i=0;i<graph.vers;i++){//i节点表示被访问过的节点
                for(int j=0;j<graph.vers;j++){//j节点表示还没有访问过的节点
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        //替换minWeight(寻找已经访问过的节点和未访问过的节点间的权值是最小的边)
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值:"+minWeight);
            //将当前这个为访问过的节点标记为已访问
            visited[h2]=1;
            //minWeight重新设置为最大值10000
            minWeight=10000;
        }

    }
}
//图
class MGraph{
    int vers;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是我们的邻接矩阵
    public MGraph(int vers){
        this.vers=vers;
        data=new char[vers];
        weight=new int[vers][vers];
    }
}
