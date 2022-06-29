package sort;

/**
 * @author wanghu
 * @discrption： 计数排序
 * @create 2022-06-08 10:44
 */
public class CountSort {

    public void countSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] count = new int[max + 1];
        for (int a:arr){
            count[a]++;
        }
        int index = 0;
        for (int i=0; i<count.length; i++){
            while (count[i]-- > 0){
                arr[index++] = i;
            }
        }
    }

}
