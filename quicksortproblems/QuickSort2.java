package quicksortproblems;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-06 15:45
 */
public class QuickSort2 {

    public void quickSort2(int[] arr){
        if (arr == null || arr.length < 2)return;
        process(arr, 0, arr.length-1);
    }

    public void process(int[] arr, int l, int r){
        if (l >= r)return;
        int[] partition = Partition2.partition(arr, l, r);
        process(arr, l, partition[0]-1);
        process(arr, partition[1]+1, r);
    }

    @Test
    public void test(){
        int[] arr = {4,2,6,3,9,7,5,1,0,4};
        quickSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

}
