package quicksortproblems;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-06 15:54
 */
public class Swap {

    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
