package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 最大线段重合数
 * @create 2022-06-07 14:49
 */
public class MaxCover {

    public int maxCover(int[][] m){
        Arrays.sort(m, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int[] line:m){
            while (!heap.isEmpty() && heap.peek() <= line[0]){
                heap.poll();
            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

}
