package slidewindow;

import java.util.LinkedList;

/**
 * @author wanghu
 * @discrption： 加油站的良好出发点问题
 * 给定两个数组，一个gas数组，代表当前加油站的油量，一个cost数组，代表当前加油站到下一个加油站需要消耗的油量。
 * 假设一辆车，初始油量为0，油箱无限大。返回它从各个加油站出发是否能跑完所有加油站并回到起点加油站。
 * @create 2022-06-28 10:14
 */
public class GoodStartingPoint {

    public int goodStartingPoint(int[] gas, int[] cost) {
        boolean[] res = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (res[i])
                return i;
        }
        return -1;
    }

    public boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        boolean[] res = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[i])
                queue.pollLast();
            queue.addLast(i);
        }
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[queue.peekFirst()] - offset >= 0)
                res[i] = true;
            if (queue.peekFirst() == i)
                queue.pollFirst();
            while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[j])
                queue.pollLast();
            queue.addLast(j);
        }
        return res;
    }

}
