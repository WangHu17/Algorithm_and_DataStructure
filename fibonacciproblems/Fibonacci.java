package fibonacciproblems;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 使用矩阵幂求斐波拉契数列的第n项，O(logn)
 * @create 2022-06-29 11:28
 */
public class Fibonacci {

    // 基本解法 O(n)
    public int f1(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        int a = 1;
        int b = 1;
        int t = 0;
        for (int i = 3; i <= n; i++) {
            t = a + b;
            a = b;
            b = t;
        }
        return t;
    }

    // 矩阵幂解法 O(logn)
    public int f2(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        int[][] a = {{1, 1},
                {1, 0}};
        int[][] res = matrixPower(a, n - 2);
        return res[0][0] + res[1][0];
    }

    // 求矩阵的 P 次方
    private int[][] matrixPower(int[][] m, int p) {
        int N = m.length;
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++)
            res[i][i] = 1;
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) == 1) {
                res = matrixMulti(res, t);
            }
            t = matrixMulti(t, t);
        }
        return res;
    }

    // 两个矩阵相乘
    private int[][] matrixMulti(int[][] t, int[][] t1) {
        int r = t.length;
        int c = t[0].length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < c; k++) {
                    res[i][j] += t[i][k] * t1[k][j];
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(f1(39));
        System.out.println(f2(39));
    }

}
