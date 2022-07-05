package bfprt;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 在无序数组中求第k小的数 - 改快排的方法
 * @create 2022-07-04 9:49
 */
public class MinKth1 {

    public static int minKth2(int[] arr, int k) {
        int N = arr.length;
        int[] copy = arr.clone();
        return process(copy, 0, N - 1, k - 1);
    }

    public static int process(int[] arr, int l, int r, int index) {
        while (l <= r) {
            int pivot = arr[l + (int) Math.random() * (r - l + 1)];
            int[] partition = partition(arr, l, r, pivot);
            if (index >= partition[0] && index <= partition[1]) {
                return arr[index];
            } else if (index < partition[0]) {
                r = partition[0] - 1;
            } else {
                l = partition[1] + 1;
            }
        }
        return -1;
    }

    public static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l-1;
        int more = r+1;
        int cur = l;
        while (cur < more){
            if (arr[cur] < pivot){
                swap(arr, cur++, ++less);
            }else if (arr[cur] > pivot){
                swap(arr, cur, --more);
            }else {
                cur++;
            }
        }
        return new int[]{less+1, more-1};
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }


}
