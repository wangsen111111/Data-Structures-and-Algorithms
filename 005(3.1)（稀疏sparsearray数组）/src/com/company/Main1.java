package com.company;

public class Main1 {
    public static void main(String[] args) {
        //创建原始棋盘
        int[][] chessArr=new int[11][11];
        //用1代表黑棋，用二代表蓝棋，将黑棋与蓝棋加入到棋盘中，0表示没有棋子
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        System.out.println("原始棋盘为：");
        //遍历二维数组，输出棋盘
        for(int[] row:chessArr){
            for(int col:row){
                System.out.printf("%d\t",col);
            }
            System.out.printf("\n");
        }
        /**
         * 将二维数组转为稀疏数组：
         * 1，遍历原始二维数组，得到有效数据的个数sum
         * 2，根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
         * 3，将二维数组的有效数据存入到稀疏数组
         */
        int sum=0;
        //1，遍历原始二维数组，得到有效数据的个数sum
        for(int[] row:chessArr){
            for(int col:row){
                if(col!=0){
                    sum++;
                }
            }
        }
        //2，根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
        int[][] sparseArr=new int[sum+1][3];
        //稀疏数组的第一行为原数组的行，列，即有效值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //计数变量count,用于记录是第几个非零数据
        int count=0;
        //3，将二维数组的有效数据存入到稀疏数组
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
        for(int[] row:sparseArr){
            for(int col:row){
                System.out.printf("%d\t",col);
            }
            System.out.println();
        }
        /**
         * 将稀疏数组转为原始的二维数组
         * 1，先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如 chess2=int[11][11]
         * 2,再读取稀疏数组的后几行的数据，并赋给原始的二维数组即可
         */
         //1，先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如 chess2=int[11][11]
        int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        //2,再读取稀疏数组的后几行的数据，并赋给原始的二维数组即可
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("将稀疏数组转为原始的二维数组为：");
        //遍历二维数组
        for(int i=0;i<sparseArr[0][0];i++){
            for(int j=0;j<sparseArr[0][1];j++){
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }
    }
}
