package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 最大线段重合数
 * 给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定:
 * 1)线段的开始和结束位置一定都是整数值
 * 2)线段重合区域的长度必须>=1
 * 返回线段最多重合区域中包含了几条线段
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
