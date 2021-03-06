package com.company;
//DP动态规划(背包问题)
public class Main {

    public static void main(String[] args) {
        int[] w={1,4,3};//物品的重量
        int[] val={1500,3000,2000};//物品的价值
        int m=4;//背包的容量
        int n=val.length;//物品的个数
        //创建二维数组
        //v[i][j]表示再第i个物品中能够装入容量为j的背包中的最大价值
        int[][] v=new int[n+1][m+1];
        //为了记录放入商品的情况，定一个二维数组
        int[][] path=new int[n+1][m+1];
        //初始化第一行,和第一列
        for(int i=0;i<v.length;i++){
            v[i][0]=0;
        }
        for(int i=0;i<v[0].length;i++){
            v[0][i]=0;
        }
        //根据前面得到的宫式来动态规划处理
        for (int i=1;i<v.length;i++){//不处理第一行
            for(int j=1;j<v[0].length;j++){//不处理第一列
                //因为我们的程序是从1开始的，所以w[i]要改为w[i+1],val[i]要改为val[i+1]
                if(w[i-1]>j){
                    v[i][j]=v[i-1][j];
                }else {
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们不能直接使用上面的公式，需要if-else来体现公式
                    if(v[i-1][j]>val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=v[i-1][j];
                    }else{
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到path(最优解)
                        path[i][j]=1;
                    }
                }
            }

        }
        //输出v
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                System.out.print(v[i][j]+" \t");
            }
            System.out.println();
        }
        /*
        //输出最后放入的有哪些商品
        for(int i=0;i<path.length;i++){
            for(int j=0;j<path[i].length;j++){
                if(path[i][j]==1){
                    System.out.printf("第%d个商品放入到背包\n",i);
                }
            }
            System.out.println();
        }
         */
        int i=path.length-1;//行的最大下标
        int j=path[0].length-1;//列的最大下标
	    while(j>0&&i>0){//从path的最后开始找
	        if(path[i][j]==1){
                System.out.printf("第%d个商品放入到背包\n",i);
	            j-=w[i-1];//背包李已经放了w[i-1]的重量
            }
	        //放入一个物品就将物品数-1
            i--;
        }
    }
}
