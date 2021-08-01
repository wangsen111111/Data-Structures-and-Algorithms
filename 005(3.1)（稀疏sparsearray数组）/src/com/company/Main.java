package com.company;
/*
稀疏数组实际需求：编写五子棋程序中，有存盘退出和续上盘的功能
   11*11的棋盘--（使用二维数组记录棋盘）》
   该二维数组的很多值是默认值0，因此记录了很多没有意义的数据--》稀疏数组（对二维数组进行一个压缩）
稀疏数组基本介绍：当一个数组中大部分元素为0，或者为同一个值的数组时，就可以使用稀疏数组来保存该数组
稀疏数组处理方式： 1，记录数组一共有几行几列，有多少个不同的值
               2，把具有不同值的元素的行列及值记录在一个小规模的数组中（稀疏数组），从而缩小程序的规模
稀疏数组应用实例：
           1，使用稀疏数组，来保留类似前面的二维数组（棋盘，地图等）
           2，把稀疏数组存盘，并且可以重新恢复为原来的二维数组
 二维数组--》稀疏数组的思路：
      1，遍历原始的二维数组，得到有效数据的个数sum
      2，根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
      3，将二维数组的有效数字存入到稀疏数组
 稀疏数组--》原始的二维数组思路：
      1，先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，
      2，再读取稀疏数组后几行的数据，并赋给原始的二维数组即可
 */
public class Main {
    public static void main(String[] args) {
        //创建原始二维数组（11*11）棋盘
        int[][] chessArr=new int[11][11];
        //用1代表黑棋，用二代表蓝棋，将黑棋与蓝棋加入到棋盘中，0表示没有棋子
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        System.out.println("原始的棋盘为：");
        //遍历棋盘，输出二维数组
        for(int[] row:chessArr){
            for(int value:row){
                System.out.printf("%d\t",value);
            }
            System.out.printf("\n");
        }
        /*
	    将二维数组--》稀疏数组（思路）
	      1，遍历原始的二维数组，得到有效数据的个数sum
	      2，根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
	      3，将二维数组的有效数据存入到稀疏数组
	 */
        int sum=0;
        //遍历原始数组，得到有效值sum
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("棋盘中非零的数有："+sum);
        //根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
        int[][] sparseArr=new int[sum+1][3];
        //稀疏数组的第一行为原数组的行，列，即有效值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //计数变量count,用于记录是第几个非零数据
        int count=0;

        //将二维数组的有效数据存入到稀疏数组
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr[i][j];
                }
            }
        }
        System.out.println("稀疏数组为：");
        //遍历稀疏数组，并将其输出
        for(int[] row:sparseArr){
            for(int value:row){
                System.out.printf("\t"+value);
            }
            System.out.println();
        }
        System.out.println("稀疏数组为（2）：");
        for(int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        /*
        稀疏数组--》原始的二维数组思路：
          1，先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如chessArr2=int[11][11];
          2,再读取稀疏数组后几行的数据，并赋给原始的二维数组即可
         */
        //先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        //再读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for(int i=1;i<sparseArr.length;i++) {
                chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("将稀疏数组转换为原始二维数组为：");
        //遍历原始二维数组并将其输出
        for(int[] row:chessArr2){
            for(int value:row){
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }

    }
}
