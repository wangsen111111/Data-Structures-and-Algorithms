package com.company;
/*
迷宫问题，小球找路（找最短路问题）：
  1，用数组表示不同策略
  2，用for循环将这几个策略走一遍
  3，将每一个2（这个结点）保存到一个集合中，看哪一个集合的大小是最小的
  4，最小的集合即就是最短路径
 */
/*
递归--迷宫问题（小球找路问题）
 */
public class 递归迷宫找路问题小球找路问题 {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫，使用1为墙，上下置为1，左右置为1，设置挡板为1
        //创建一个8行7列的二维数组
        int[][] map = new int[8][7];
        //将上下置为1
        for (int i = 0; i < 7; i++) {
            //将第一行置为1，最后一行置为1，做墙
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //将左右置为1
        for (int i = 0; i < 8; i++) {
            //将第一列和最后一列置为1
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("迷宫如下：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //小球找路,小球的起始位置在（1，1）位置
        setWay(map, 1, 1);
        //输出小球找路的路径
        System.out.println("小球找路的路径如下图所示：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param map 地图
     * @param i   小球起始位置所在的行
     * @param j   小球起始位置所在的列
     * @return 如果找到通路，就返回true，否则返回false
     * 通路即为小球能到map[6][5]位置，就说明通路找到
     * 约定：当map[i][j]==0表示该点还没有走过
     * ==1表示墙
     * ==2表示通路可以走
     * ==3表示该点已经走过，但是走不通
     * 走迷宫时，需要确定一个策略（方法）下-->右-->上-->左
     */
    //小球找路,将地图，与起始位置传过来
    public static boolean setWay(int[][] map, int i, int j) {
        //判断如果直接map[6][5]为2时，表示该路可走，通路已经找到，直接返回true
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//如果该点换没走过
                //如果该点还没走过，就按照上面规定的策略即方法走
                //先假定该点可以走通,将能走通的点标记为2，下次回溯就可以不用再走啦，要不然回溯太多容易出现栈溢出StackOverflow
                map[i][j]=2;
                if (setWay(map, i + 1, j)) {//先向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//再向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//再向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//再向左走
                    return true;
                }else{//说明该点走不通，是死路
                    map[i][j]=3;
                    return false;
                }

            } else {//该点已经走过,即就是当map[i][j]=1，2，3这三种情况表示都已经走过，不需要再走，直接返回false
                return false;
            }
        }
    }
}
