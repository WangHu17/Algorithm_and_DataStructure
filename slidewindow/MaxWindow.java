package slidewindow;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author wanghu
 * @discrption： 滑动窗口的最大值
 * 假设一个固定大小为W的窗口，依次划过arr
 * 返回每一次滑出状况的最大值
 * 例如，arr=[4,3,5,4,3,3,6,7]，W=3
 * 返回：[5,5,5,4,6,7]
 * @create 2022-06-24 10:19
 */
public class MaxWindow {

    public int[] maxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w)
            return null;
        int N = arr.length;
        int[] res = new int[N - w + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i])
                queue.pollLast();
            queue.addLast(i);
            if (queue.peekFirst() == i - w)
                queue.pollFirst();
            if (i >= w - 1)
                res[i - w + 1] = arr[queue.peekFirst()];
        }
        return res;
    }

    @Test
    public void test(){
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        int[] res = maxWindow(arr, 3);
        System.out.println(Arrays.toString(res));
    }

}
