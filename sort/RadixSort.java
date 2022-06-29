package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 基数排序
 * @create 2022-06-08 14:28
 */
public class RadixSort {

    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        radix(arr, 0, arr.length - 1, getMaxDigit(arr));
    }

    public void radix(int[] arr, int l, int r, int digit) {
        int[] help = new int[r - l + 1];
        int i;
        for (int d = 0; d < digit; d++) {
            int[] count = new int[10];
            for (i = l; i <= r; i++) {
                int num = getDigit(arr[i], d);
                count[num]++;
            }
            for (i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (i = r; i >= l; i--){
                int num = getDigit(arr[i], d);
                help[count[num] - 1] = arr[i];
                count[num]--;
            }
            for (i = l; i <= r; i++){
                arr[i] = help[i];
            }
        }
    }

    public int getDigit(int x, int d) {
        return (x / (int) Math.pow(10, d)) % 10;
    }

    public int getMaxDigit(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return (max + "").length();
    }

    @Test
    public void test() {
        int[] arr = {4, 21, 636, 34, 9, 765, 5, 1234, 42, 47};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
