package arrayproblems;

/**
 * @author wanghu
 * @discrption： 给定一个正整数组成的无序数组arr，给定一个正整数值K，找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的，返回其长度。
 * @create 2022-07-21 9:35
 */
public class Problem1 {

    public static int getMaxLength(int[] arr, int k){
        int l = 0;
        int r = 0;
        int sum = arr[0];
        int res = 0;
        while (r < arr.length){
            if (sum == k){
                res = Math.max(res, r-l+1);
                sum -= arr[l++];
            }else if (sum < k){
                r++;
                if (r == arr.length)break;
                sum += arr[r];
            }else {
                sum -= arr[l++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 50000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int ans1 = getMaxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }

    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
