package com.hct.common_algorithms.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author HCT
 * @Date 2021/4/9 17:01
 * @Version 1.0
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到hashMap
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        // 将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        // 加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        for (Map.Entry<String, HashSet<String>> broadcast : broadcasts.entrySet()) {
            allAreas.addAll(broadcast.getValue());
        }

        // 创建ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        // 定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        String maxKey = null;
        while (allAreas.size() != 0){ // 如果allAreas 不为0, 则表示还没有覆盖到所有的地区
            //每进行一次while 需要将 maxKey置空
            maxKey = null;
            // 遍历 broadcasts, 取出对应key
            for (String key : broadcasts.keySet()){
                //每一次for循环，都需要将tempSet置空
                tempSet.clear();
                //当前这个key能够覆盖的区域
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // 求出tempSet和allAreas集合的交集，交集会重新赋给tempSet
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() >  broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if (maxKey != null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas中去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是：" + selects);
    }
}
