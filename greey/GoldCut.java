package greey;

import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 切金条问题
 * @create 2022-06-12 15:02
 */
public class GoldCut {

    public int goldCut(int[] arr){
        if (arr == null || arr.length == 0)return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int a:arr)heap.add(a);
        int res = 0, cur = 0;
        while (heap.size() > 1){
            cur = heap.poll() + heap.poll();
            res += cur;
            heap.add(cur);
        }
        return res;
    }

}
