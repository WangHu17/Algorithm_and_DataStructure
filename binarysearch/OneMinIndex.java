package binarysearch;

/**
 * @author wanghu
 * @discrption： 一个无序数组，相邻元素不等，找出一个局部最小值，用二分。
 * @create 2022-05-18 10:42
 */
public class OneMinIndex {

    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        int N = arr.length;
        if (N == 1) return 0;
        if (arr[0] < arr[1])
            return 0;
        if (arr[N - 1] < arr[N - 2])
            return N - 1;
        int l = 0, r = N - 1;
        // 注意边界条件；l < r - 1，要保证mid左边和右边都有数，否则返回l和r位置较小的
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1])
                return mid;
            else if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            } else
                l = mid + 1;
        }
        return arr[l] < arr[r] ? l : r;
    }

    // 生成随机数组，且相邻数不相等
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    // 也用于测试
    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int ans = oneMinIndex(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
