package quicksortproblems;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 荷兰国旗1，给定一个数组和一个数a，使数组中小于等于a的数放左边，大于a的数放右边。
 * @create 2022-06-06 15:42
 */
public class Partition1 {

    public static int partition(int[] arr, int l, int r){
        if (l > r)return -1;
        if (l == r)return l;
        int lessAndEqual = l - 1;
        int index = l;
        while (index < r){
            if (arr[index] <= arr[r]){
                Swap.swap(arr, index, ++lessAndEqual);
            }
            index++;
        }
        Swap.swap(arr, ++lessAndEqual, r);
        return lessAndEqual;
    }

    @Test
    public void test(){
        int[] arr = {4,2,6,3,9,7,5,1,0,4};
        partition(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
