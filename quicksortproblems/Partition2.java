package quicksortproblems;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 荷兰国旗2，给定一个数组和一个数a，使数组中小于a的数放左边，等于a的数放中间，大于a的数放右边。
 * @create 2022-06-06 15:44
 */
public class Partition2 {

    public static int[] partition(int[] arr, int l, int r){
        if (l > r)return new int[]{-1, -1};
        if (l == r)return new int[]{l, r};
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more){
            if (arr[index] == arr[r]){
                index++;
            }else if(arr[index] < arr[r]){
                Swap.swap(arr, index++, ++less);
            }else {
                Swap.swap(arr, index, --more);
            }
        }
        Swap.swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    @Test
    public void test(){
        int[] arr = {4,2,6,3,9,7,5,1,0,4};
        partition(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
