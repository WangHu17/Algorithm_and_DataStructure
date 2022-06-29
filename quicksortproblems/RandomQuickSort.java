package quicksortproblems;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 随机快排
 * 从当前划分区域中随机选择一个值 x 与最右的数交换，将 x 作划分值，小于 x 的数放左边，等于 x 的数放中间，大于 x 的数放右边，返回等于区域的下标，然后递归执行左边和右边。
 * @create 2022-06-06 15:45
 */
public class RandomQuickSort {

    public void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int l, int r) {
        if (l >= r) return;
        Swap.swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] partition = Partition2.partition(arr, l, r);
        process(arr, l, partition[0] - 1);
        process(arr, partition[1] + 1, r);
    }

    @Test
    public void test(){
        int[] arr = {4,2,6,3,9,7,5,1,0,4};
        quickSort3(arr);
        System.out.println(Arrays.toString(arr));
    }

}
