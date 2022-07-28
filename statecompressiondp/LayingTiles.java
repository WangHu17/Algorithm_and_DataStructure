package statecompressiondp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 你有无限的 1*2的砖块，要铺满 M*N的区域，不同的铺法有多少种?
 * @create 2022-07-25 9:37
 */
public class LayingTiles {

    // 暴力递归
    public int way1(int N, int M) {
        if (M < 1 || N < 1 || ((M * N) & 1) != 0) {
            return 0;
        }
        if (M == 1 && N == 1) return 1;
        int[] pre = new int[M];
        Arrays.fill(pre, 1);
        return process1(pre, 0, N);
    }

    public int process1(int[] pre, int level, int N) {
        if (level == N) {
            for (int i : pre) {
                if (i == 0) return 0;
            }
            return 1;
        }
        int[] op = getOp(pre);
        return dfs1(op, 0, level, N);
    }

    public int dfs1(int[] op, int col, int level, int N) {
        if (col == op.length) {
            return process1(op, level + 1, N);
        }
        int ans = 0;
        ans += dfs1(op, col + 1, level, N);
        if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
            op[col] = 1;
            op[col + 1] = 1;
            ans += dfs1(op, col + 2, level, N);
            op[col] = 0;
            op[col + 1] = 0;
        }
        return ans;
    }

    public int[] getOp(int[] pre) {
        int[] cur = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            cur[i] = pre[i] ^ 1;
        }
        return cur;
    }

    // 状态压缩的暴力递归
    public int way2(int N, int M) {
        if (M < 1 || N < 1 || ((M * N) & 1) != 0) {
            return 0;
        }
        if (M == 1 && N == 1) return 1;
        int max = Math.max(N, M);
        int min = Math.min(N, M);
        int pre = (1 << min) - 1;
        return process2(pre, 0, max, min);
    }

    public int process2(int pre, int level, int N, int M) {
        if (level == N) {
            return pre == (1 << M) - 1 ? 1 : 0;
        }
        int op = (~pre) & ((1 << M) - 1);
        return dfs2(op, level, M - 1, N, M);
    }

    public int dfs2(int op, int level, int col, int N, int M) {
        if (col == -1) {
            return process2(op, level + 1, N, M);
        }
        int ans = 0;
        ans += dfs2(op, level, col - 1, N, M);
        if (col > 0 && (op & (3 << col - 1)) == 0) {
            ans += dfs2(op | (3 << col - 1), level, col - 2, N, M);
        }
        return ans;
    }

    // 记忆化搜索
    public int way3(int N, int M) {
        if (M < 1 || N < 1 || ((M * N) & 1) != 0) {
            return 0;
        }
        if (M == 1 && N == 1) return 1;
        int max = Math.max(N, M);
        int min = Math.min(N, M);
        int pre = (1 << min) - 1;
        int[][] dp = new int[1 << min][max + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process3(pre, 0, max, min, dp);
    }

    public int process3(int pre, int level, int N, int M, int[][] dp) {
        if (dp[pre][level] != -1)
            return dp[pre][level];
        int ans = 0;
        if (level == N) {
            ans = pre == (1 << M) - 1 ? 1 : 0;
        }else {
            int op = (~pre) & ((1 << M) - 1);
            ans = dfs3(op, level, M - 1, N, M, dp);
        }
        dp[pre][level] = ans;
        return ans;
    }

    public int dfs3(int op, int level, int col, int N, int M, int[][] dp) {
        if (col == -1) {
            return process3(op, level + 1, N, M, dp);
        }
        int ans = 0;
        ans += dfs3(op, level, col - 1, N, M, dp);
        if (col > 0 && (op & (3 << col - 1)) == 0) {
            ans += dfs3(op | (3 << col - 1), level, col - 2, N, M, dp);
        }
        return ans;
    }

    @Test
    public void test() {
        int N = 8;
        int M = 6;
        System.out.println(way1(N, M));
        System.out.println(way2(N, M));
        System.out.println(way3(N, M));
    }

}
