package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        char[] chars={'a','a','b','b','c','c','c'};
        compress(chars);
    }
    public static void compress(char[] chars) {
        Map<Character,Integer> map=new HashMap();
        int count = 1;
        for(int i=0;i<chars.length-1;i++){
            if(chars[i]==chars[i+1]) {
                map.put(chars[i],count++);
            }else {
                map.put(chars[i],count);
            }
        }
        //比较优雅的遍历
        for(Map.Entry<Character, Integer > met:map.entrySet()){
            System.out.println("key:"+met.getKey()+",value:"+met.getValue());
        }
    }
}
