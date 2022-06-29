package mergesortproblems;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 数组逆序对
 * @create 2022-06-06 14:13
 */
public class ReversePairNum {

    public int reversePair(int[] arr){
        if (arr == null || arr.length < 2)return 0;
        return process(arr, 0, arr.length-1);
    }

    public int process(int[] arr, int l, int r){
        if (l == r)return 0;
        int m = l + (r - l)/2;
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public int merge(int[] arr, int l, int m, int r){
        int[] temp = new int[r-l+1];
        int i = temp.length-1;
        int p1 = m, p2 = r;
        int res = 0;
        while (p1 >= l && p2 >= m+1){
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            temp[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l)temp[i--] = arr[p1--];
        while (p2 >= m + 1)temp[i--] = arr[p2--];
        for (i = 0; i<temp.length; i++){
            arr[l + i] = temp[i];
        }
        return res;
    }

    public int verify(int[] arr){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i] > arr[j])
                    sum ++;
            }
        }
        return sum;
    }

    @Test
    public void test(){
        int[] arr = {1,3,8,4,2,6,9,7,5};
        System.out.println(verify(arr));
        System.out.println(reversePair(arr));
    }

}
