package datascale;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author wanghu
 * @discrption： 数组所有子序列中累加和%m之后的最大值
 * @create 2022-07-20 15:21
 */
public class SumModM3 {

    // 数组每个元素巨大，m也巨大，数组长度很小（30以内）。

    public static int max4(int[] arr, int m) {
        TreeSet<Integer> set1 = new TreeSet<>();
        TreeSet<Integer> set2 = new TreeSet<>();
        int mid = arr.length / 2;
        process(arr, 0, mid + 1, 0, m, set1);
        process(arr, mid + 1, arr.length, 0, m, set2);
        int max = 0;
        for (Integer n:set1){
            max = Math.max(max, n + set2.floor(m-1-n));
        }
        return max;
    }

    public static void process(int[] arr, int index, int end, int sum, int m, Set<Integer> set) {
        if (index == end) {
            set.add(sum % m);
        } else {
            process(arr, index + 1, end, sum, m, set);
            process(arr, index + 1, end, sum + arr[index], m, set);
        }
    }

}
