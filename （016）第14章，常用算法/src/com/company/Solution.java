package com.company;
//进制转换---》将字符串中的数转换成十进制，然后再转其他进制（2<=k<=36）
public class Solution {
    public static void main(String[] args) {
        System.out.println(transK("100",10,2));
        System.out.println(Integer.valueOf("100",2));
    }
    public static String transK(String str, int x, int y){
        //先将x进制对应的10进制得到，然后以10进制转其它进制
        int number=Integer.valueOf(str,x);
        char[] arr=new char[36];
        for(int i=0;i<10;i++){
            arr[i]=(char)('0'+i);
        }

        for(int i=10;i<36;i++){
            arr[i]=(char)('A'+i-10);
        }

        StringBuffer sb=new StringBuffer();
        while(number!=0){
            sb.append(arr[number%y]);
            number/=y;
        }
        //余数反转
        return sb.reverse().toString();
    }
}
