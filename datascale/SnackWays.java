package datascale;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author wanghu
 * @discrption： 牛牛家里一共有n袋零食，第 i 袋零食体积为v[i]，背包容量为w。
 * 牛牛想知道在总体积不超过背包容量的情况下，共有多少种零食放法，体积为0也算一种放法
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9，0 <= v[i] <= 10^9
 * @create 2022-07-20 16:09
 */
public class SnackWays {

    public long ways(int[] arr, int bag) {
        int N = arr.length;
        if (N == 1)
            return arr[0] <= bag ? 2 : 1;
        int mid = N / 2;
        TreeMap<Long, Long> map1 = new TreeMap<>();
        TreeMap<Long, Long> map2 = new TreeMap<>();
        long ways = process(arr, 0, mid + 1, 0, bag, map1);
        ways += process(arr, mid + 1, N, 0, bag, map2);
        TreeMap<Long, Long> preMap = new TreeMap<>();
        long pre = 0;
        for (Long key:map2.keySet()){
            pre += map2.get(key);
            preMap.put(key, pre);
        }
        for (Map.Entry<Long, Long> entry:map1.entrySet()){
            Long key1 = entry.getKey();
            Long value1 = entry.getValue();
            Long key2 = preMap.floorKey(bag - key1);
            if (key2 != null){
                Long value2 = preMap.get(key2);
                ways += value1 * value2;
            }
        }
        return ways + 1;
    }

    public long process(int[] arr, int index, int end, long sum, int bag, TreeMap<Long, Long> map) {
        if (sum > bag) return 0;
        if (index == end) {
            if (sum != 0) {
                map.put(sum, map.getOrDefault(sum, 0L) + 1);
                return 1;
            } else {
                return 0;
            }
        }
        long ways = process(arr, index + 1, end, sum, bag, map);
        ways += process(arr, index + 1, end, sum + arr[index], bag, map);
        return ways;
    }

}
