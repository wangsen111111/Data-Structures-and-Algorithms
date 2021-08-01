package com.company;

public class Solution {
    public static void main(String[] args) {
        String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String s = "ball";
        //（1），将这个一维字符串数组转为稀疏数组
        //1，遍历原始的一维数组，得到有效数据的个数sum
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                //如果该字符数组不为空，就将sum++
                sum++;
            }
        }
        //2，根据sum就可以创建稀疏数组sparseArr[sum+1][3]
        String[][] sparseArr = new String[sum + 1][3];
        //3，将一维数组的有效数据存入到稀疏数组，行都为0
        sparseArr[0][0] = "1";
        sparseArr[0][1] = String.valueOf(words.length);
        sparseArr[0][2] = String.valueOf(sum);
        //定义一个统计变量count
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                count++;
                sparseArr[count][0] = "1";
                sparseArr[count][1] = String.valueOf(i);
                //就将稀疏数组中的字符串用其替换     (sparseArr[count][i], words[i]
                sparseArr[count][2] = words[i];
            }
        }
        //输出稀疏数组
        for (String[] row : sparseArr) {
            for (String val : row) {
                System.out.printf("%s\t", val);
            }
            System.out.println();
        }
        for (int i = 0; i < sparseArr.length; i++) {
            if (sparseArr[i][2].equals(s)) {
                System.out.println(Integer.parseInt(sparseArr[i][1]));

            }
        }
    }
}
