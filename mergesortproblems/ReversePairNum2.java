package mergesortproblems;

/**
 * @author wanghu
 * @discrption： 求数组的反向对，前面的数比后面的数的两倍大是一个反向对
 * @create 2022-06-06 15:01
 */
public class ReversePairNum2 {

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
        int res = 0;
        int windowR = m+1;
        for(int i=l; i<=m; i++){
            while (windowR <= r && (long)arr[i] > (long)arr[windowR]*2){
                windowR++;
            }
            res += windowR - m - 1;
        }
        int[] temp = new int[r-l+1];
        int i = temp.length-1;
        int p1 = m, p2 = r;
        while (p1 >= l && p2 >= m+1){
            temp[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l)temp[i--] = arr[p1--];
        while (p2 >= m + 1)temp[i--] = arr[p2--];
        for (i = 0; i<temp.length; i++){
            arr[l + i] = temp[i];
        }
        return res;
    }

}
