package com.company;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的临接矩阵
    private int numOfEdges;//表示边的个数

    private boolean[] isVisited=new boolean[5];//定义数组boolean[],记录某个结点是否被访问过
    //得到第一个邻接结点的下标w
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            //当为1时，表明两个元素相邻,即返回相邻元素对应的下标
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接结点
    //连成一条线的线路
    public int getNextNeighbor(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    /**
     *深度优先遍历算法
     */
    //i,第一次为0
     public void dfs(boolean[] isVisited,int i){
         //(1)首先我们访问初始结点v，并标记v已访问
         System.out.print(getValueByIndex(i)+"->");
         isVisited[i]=true;
         //(2)查找结点v的第一个邻接结点w
         int w=getFirstNeighbor(i);
         /**
          * (3)如果w有
          * 1,若w存在，并且w未被访问，对w进行进行深度优先遍历--递归
          * 2,若w存在，但是w已经被访问过啦，查找结点v的w(邻接结点)的下一个邻接结点,【再进行1，2步】
          */
         while(w!=-1){
             if(!isVisited[w]){
                 dfs(isVisited,w);
             }
             //如果w结点已经被访问过啦
             w=getNextNeighbor(i,w);
         }
         //如果w不存在，就回到第一步，从v的第一个结点继续，遍历我们所有的结点，并进行dfs
         //可对dfs进行一个回溯
     }
     //对dfs进行一个重载，遍历我们所有的结点，并进行dfs
     private void dfs(){
         //遍历所有的结点，进行dfs
         for(int i=0;i<getNumOfVertex();i++){
             if(!isVisited[i]){
                 dfs(isVisited,i);
             }
         }
     }

    /**
     *对一个结点进行广度优先遍历的方法
     */
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头结点对应下标
        int w;//邻接结点w
        //队列记录访问的顺序
        LinkedList queue=new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i)+"=>");
        //标记为已访问
        isVisited[i]=true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的的头节点的下标u
            u=(Integer)queue.removeFirst();
            //查找结点u的第一个邻接结点w
            w=getFirstNeighbor(u);
            while(w!=-1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"=>");
                    isVisited[w]=true;
                    queue.addLast(w);
                }
                //查找结点u的继w邻接结点后的下一个邻接结点w
                w=getNextNeighbor(u,w);
            }
        }
    }
    //方法重载，遍历所有的结点，都进行广度优先搜素
    public void bfs(){
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //构造器
    public Main(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<String>(n);
        numOfEdges=0;//边默认为0
    }

    public static void main(String[] args) {
        int n=5;//结点个数
        String[] Vertex={"A","B","C","D","E"};//结点值
        Main graph=new Main(n);
        //循环的添加顶点
        for(String vertex:Vertex){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示邻接矩阵
        graph.showGraph();
        System.out.println("深度遍历~");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先~");
        //graph.bfs();
    }
    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边,无向图
    //v1,表示点的下表，即第几个顶点
    //v2,第二个顶点对应的下标
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;//两个结点连起来，边数++
    }
    /**
     * 常用方法
     */
    //返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵(二维数组)
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }
}
