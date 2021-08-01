package com.company;

import java.util.*;

class Main1 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        topKFrequent(nums, 2);

    }


    public static void topKFrequent(int[] nums, int k) {
        //map中的键是唯一的，值是可重复的
        //键(元素)：       1    2    3         arr[i]
        //值(元素出现次数):  3    2    1     桶   i
        //使用hashMap统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            //如果元素已经添加到map中，直接将其加到值中，否则将元素添加到map中
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //构造一个桶的集合，及就是出现一次的桶，出现几次的桶
        List<Integer>[] buckets = new List[nums.length + 1];
        //遍历map，哪个数出现几次就放在出现几次的桶里
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        for (int key : map.keySet()) {
            // 获取出现的次数作为下标
            int i = map.get(key);
            if (buckets[i] == null) {
                //如果桶为空，就初始化其为一个数组
                buckets[i] = new ArrayList();
            }
            buckets[i].add(key);//将数字放入桶中
        }
        int[] arr = new int[k];
        int index = 0;
        //逆向遍历每个桶，将其中的数字加入到数组中
        for (int i = buckets.length - 1; i > 0; i--) {
            if (buckets[i] != null) {
                for (int j = 0; j < buckets[i].size() && index < k; j++) {
                    //依次将桶里的数字添加到数组中
                    arr[index++] = buckets[i].get(j);
                }

            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

