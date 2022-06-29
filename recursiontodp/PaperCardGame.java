package recursiontodp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿。
 * 但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明，请返回最后获胜者的分数。
 * @create 2022-06-16 10:24
 */
public class PaperCardGame {

    // 暴力递归
    public int win1(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        int f = f1(arr, 0, arr.length - 1);
        int g = g1(arr, 0, arr.length - 1);
        return Math.max(f, g);
    }

    public int f1(int[] arr, int l, int r) {
        if (l == r)
            return arr[l];
        int left = arr[l] + g1(arr, l + 1, r);
        int right = arr[r] + g1(arr, l, r - 1);
        return Math.max(left, right);
    }

    public int g1(int[] arr, int l, int r) {
        if (l == r)
            return 0;
        int left = f1(arr, l + 1, r);
        int right = f1(arr, l, r - 1);
        return Math.min(left, right);
    }

    // 加缓存表
    public int win2(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(fmap[i], -1);
            Arrays.fill(gmap[i], -1);
        }
        int f = f2(arr, 0, N - 1, fmap);
        int g = g2(arr, 0, N - 1, gmap);
        return Math.max(f, g);
    }

    public int f2(int[] arr, int l, int r, int[][] map) {
        if (map[l][r] != -1)
            return map[l][r];
        int ans = 0;
        if (l == r)
            ans = arr[l];
        else {
            int p1 = arr[l] + g2(arr, l + 1, r, map);
            int p2 = arr[r] + g2(arr, l, r - 1, map);
            ans = Math.max(p1, p2);
        }
        map[l][r] = ans;
        return ans;
    }

    public int g2(int[] arr, int l, int r, int[][] map) {
        if (map[l][r] != -1)
            return map[l][r];
        int ans = 0;
        if (l != r) {
            int p1 = f2(arr, l + 1, r, map);
            int p2 = f2(arr, l, r - 1, map);
            ans = Math.min(p1, p2);
        }
        map[l][r] = ans;
        return ans;
    }

    // 动态规划
    public int win3(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = i;
            while (r < N){
                fmap[l][r] = Math.max(arr[l] + gmap[l+1][r], arr[r] + gmap[l][r-1]);
                gmap[l][r] = Math.min(fmap[l+1][r], fmap[l][r-1]);
                l++;
                r++;
            }
        }
        return Math.max(fmap[0][N-1], gmap[0][N-1]);
    }

    @Test
    public void test() {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }

}
