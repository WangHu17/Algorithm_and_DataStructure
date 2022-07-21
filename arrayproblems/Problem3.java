package arrayproblems;

/**
 * @author wanghu
 * @discrption： 给定一个整数组成的无序数组arr，值可能正、可能负、可能0。给定一个整数值K，找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的，返回其长度。
 * @create 2022-07-21 9:58
 */
public class Problem3 {

    public static int maxLengthAwesome(int[] arr, int k) {
        int N = arr.length;
        int[] minSum = new int[N];
        int[] minSumEnd = new int[N];
        minSum[N - 1] = arr[N - 1];
        minSumEnd[N - 1] = N - 1;
        for (int i=N-2; i>=0; i--){
            if (minSum[i+1] < 0){
                minSum[i] = arr[i] + minSum[i+1];
                minSumEnd[i] = minSumEnd[i+1];
            }else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int res = 0;
        int sum = 0;
        int r = 0;
        for (int i=0; i<N; i++){
            while (r<N && sum + minSum[r] <= k){
                sum += minSum[r];
                r = minSumEnd[r] + 1;
            }
            res = Math.max(res, r - i);
            if (r > i){
                sum -= arr[i];
            }else {
                r = i+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

    // for test
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

}
