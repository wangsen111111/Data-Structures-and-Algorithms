package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到Map进行管理
        HashMap<String, HashSet<String>> broadcasts=new HashMap<>();
        //将各个电台放入到broadcasts
        //广播台k1
        HashSet<String> hashSet1=new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        //广播台k2
        HashSet<String> hashSet2=new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        //广播台k3
        HashSet<String> hashSet3=new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        //广播台k4
        HashSet<String> hashSet4=new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        //广播台k5
        HashSet<String> hashSet5=new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到Map
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);
        //所有的地区,所有地区会变化
        HashSet<String> allAreas=new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //ArrayList,存放选择的电台集合
        ArrayList<String> selects=new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet=new HashSet<>();
        //定义一个maxKey，保存在一次遍历过程中，能够覆盖最大为覆盖地区对应的电台的key
        //如果maxKey不为null，则加入到selects
        String maxKey=null;
        /**
         * 1,遍历所有的广播电台，找到一个覆盖了最多未覆盖地区的电台(此电台可能包含一些已经覆盖的地区)
         * 2，将这个电台加入到一个集合中(比如ArrayList)，想办法吧该电台覆盖的地区在下次比较时去掉
         * 3，重复第一步直到覆盖了全部的地区
         */
        while (allAreas.size()!=0){
            //每加入一个广播台，就将maxKey置null
            maxKey=null;
            //遍历键值对
            for(String key:broadcasts.keySet()){
                //存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集,进行完一次就要将其清
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas=broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区要多,就需要重置keySet
                //tempSet.size()>broadcasts.get(maxKey).size())/体现出贪心算法的核心，每次都选择最优的
                if(tempSet.size()>0&&(maxKey==null||tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey=key;
                }
            }
            //maxkey!=null,就应该将maxKey加入到selects中
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是"+selects);
    }
}
