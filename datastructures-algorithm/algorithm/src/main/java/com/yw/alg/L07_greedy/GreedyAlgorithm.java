package com.alg.L07_greedy;

import java.util.*;

/**
 * 贪心算法最佳实践-集合覆盖问题
 *
 * @author yangwei
 * @date 2020-06-14 14:52
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台，放入Map
        Map<String, Set<String>> broadcastMap = new LinkedHashMap<>(8);
        // 将各个电台放入broadcastMap
        broadcastMap.put("K1", newHashSet("北京", "上海", "天津"));
        broadcastMap.put("K2", newHashSet("广州", "北京", "深圳"));
        broadcastMap.put("K3", newHashSet("成都", "上海", "杭州"));
        broadcastMap.put("K4", newHashSet("上海", "天津"));
        broadcastMap.put("K5", newHashSet("杭州", "大连"));

        // 所有地区的集合
        Set<String> allAreas = getAllAreas(broadcastMap);
        System.out.println("All Areas = " + allAreas);
        // 创建一个List，存放选择的电台集合
        List<String> selects = new ArrayList<>();
        // 定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        Set<String> tempSet = new HashSet<>();
        // 定义maxKey：保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
        // 如果 maxKey 不为 null , 则会加入到 selects
        String maxKey;
        while (allAreas.size() > 0) {
            // 每进行一次循环，都需要将maxKey置空
            maxKey = null;
            for (String key : broadcastMap.keySet()) {
                tempSet.clear();
                tempSet.addAll(broadcastMap.get(key));
                // 求出 tempSet 和 allAreas 集合的交集, 交集会赋给 tempSet
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量，比 maxKey 指向的集合地区还多，就需要重置 maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcastMap.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                // 将 maxKey 指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcastMap.get(maxKey));
            }
        }
        System.out.println("得到的结果是:" + selects);
    }

    @SafeVarargs
    private static <E> Set<E> newHashSet(E... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    private static Set<String> getAllAreas(Map<String, Set<String>> broadcastMap) {
        Set<String> res = new HashSet<>();
        for (Set<String> value : broadcastMap.values()) {
            res.addAll(value);
        }
        return res;
    }
}
