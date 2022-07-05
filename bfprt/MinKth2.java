package bfprt;

/**
 * @author wanghu
 * @discrption： 在无序数组中求第k小的数 - bfprt的方法
 * @create 2022-07-04 10:47
 */
public class MinKth2 {

    public static int minKth3(int[] arr, int k) {
        int[] copy = arr.clone();
        return bfprt(copy, 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int l, int r, int index) {
        if (l == r) return arr[l];
        int pivot = medianOfMedians(arr, l, r);
        int[] partition = MinKth1.partition(arr, l, r, pivot);
        if (index >= partition[0] && index <= partition[1]) {
            return arr[index];
        } else if (index < partition[0]) {
            return bfprt(arr, l, partition[0] - 1, index);
        } else {
            return bfprt(arr, partition[1] + 1, r, index);
        }
    }

    public static int medianOfMedians(int[] arr, int l, int r) {
        int size = r - l + 1;
        int flag = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + flag];
        for (int i = 0; i < mArr.length; i++) {
            int L = l + 5 * i;
            mArr[i] = getMedian(arr, L, Math.min(r, L + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int l, int r) {
        insertSort(arr, l, r);
        return arr[(l + r) / 2];
    }

    public static void insertSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l && arr[j] > arr[j + 1]; j--) {
                MinKth1.swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = MinKth1.minKth1(arr, k);
            int ans2 = MinKth1.minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

}
