package mergesortproblems;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 一个数组，求每个数左边比自己小的数之和的和。
 * @create 2022-06-06 11:16
 */
public class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2)return 0;
        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r)return 0;
        int mid = l + (r - l)/2;
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        int[] temp = new int[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r){
            res += arr[p1] < arr[p2] ? (r - p2 + 1)*arr[p1] : 0;
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m)temp[i++] = arr[p1++];
        while (p2 <= r)temp[i++] = arr[p2++];
        for (i=0; i<temp.length; i++)arr[l + i] = temp[i];
        return res;
    }

    public int verify(int[] arr){
        int sum = 0;
        for(int i=1; i<arr.length; i++){
            for (int j=0; j<i; j++){
                if (arr[j] < arr[i])
                    sum += arr[j];
            }
        }
        return sum;
    }

    @Test
    public void test(){
        int[] arr = {1,3,8,4,2,6,9,7,5};
        System.out.println(verify(arr));
        System.out.println(smallSum(arr));
    }

}
