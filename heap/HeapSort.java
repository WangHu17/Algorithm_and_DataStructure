package heap;

import org.junit.Test;
import quicksortproblems.Swap;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 堆排序
 * @create 2022-06-07 10:15
 */
public class HeapSort {

    public void heapSort(int[] arr){
        if(arr == null || arr.length < 2)return;
        int heapSize = arr.length;
        for (int i=arr.length-1; i>=0; i--){
            MyHeap.heapify(arr, i, heapSize);
        }
        Swap.swap(arr, 0, --heapSize);
        while (heapSize > 0){
            MyHeap.heapify(arr, 0, heapSize);
            Swap.swap(arr, 0, --heapSize);
        }
    }

    @Test
    public void test(){
        int[] arr = {4,2,6,3,9,7,5,1,0,4};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
