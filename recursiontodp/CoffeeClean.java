package recursiontodp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-20 9:38
 */
public class CoffeeClean {

    class CoffeeMachine {
        int startTime;
        int cleanTime;

        public CoffeeMachine(int startTime, int cleanTime) {
            this.startTime = startTime;
            this.cleanTime = cleanTime;
        }
    }

    class MyComparator implements Comparator<CoffeeMachine> {
        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return (o1.startTime + o1.cleanTime) - (o2.startTime + o2.cleanTime);
        }
    }

    public int minTime(int[] arr, int n, int a, int b) {
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new MyComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new CoffeeMachine(0, arr[i]));
        }
        int[] drink = new int[n];
        for (int i = 0; i < n; i++) {
            CoffeeMachine cur = heap.poll();
            cur.startTime += cur.cleanTime;
            drink[i] = cur.startTime;
            heap.add(cur);
        }
//        return process1(drink, a, b, 0, 0);
        return processDP(drink, a, b);
    }

    // 暴力递归
    public int process1(int[] drink, int wash, int air, int index, int free) {
        if (index == drink.length)
            return 0;
        // 洗
        int selfClean1 = Math.max(drink[index], free) + wash;
        int restClean1 = process1(drink, wash, air, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);
        // 不洗
        int selfClean2 = drink[index] + air;
        int restClean2 = process1(drink, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);
    }

    // 动态规划
    public int processDP(int[] drink, int wash, int air) {
        int N = drink.length;
        int maxFree = 0;
        for (int i = 0; i < N; i++) {
            maxFree = Math.max(drink[i], maxFree) + wash;
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                // 洗
                int selfClean1 = Math.max(drink[index], free) + wash;
                if (selfClean1 > maxFree) break;
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);
                // 不洗
                int selfClean2 = drink[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


    @Test
    public void test() {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = minTime(arr, n, a, b);
            int ans2 = right(arr, n, a, b);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");
    }

    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

}
