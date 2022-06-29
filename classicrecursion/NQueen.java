package classicrecursion;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-24 9:34
 */
public class NQueen {

    // 递归解法
    public int NQueen(int n) {
        if (n < 1) return 0;
        int[] record = new int[n];
        return process(0, record, n);
    }

    public int process(int i, int[] record, int n) {
        if (i == n) return 1;
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)){
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    public boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k))
                return false;
        }
        return true;
    }

    // 位运算解法
    public int NQueen1(int n) {
        if (n < 1) return 0;
        int limit = (1 << n) - 1;
        return process1(limit, 0, 0, 0);
    }

    public int process1(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit)
            return 1;
        int pos = (~(colLim | leftDiaLim | rightDiaLim)) & limit;
        int res = 0;
        int mostRight = 0;
        while (pos != 0) {
            mostRight = pos & (~pos + 1);
            pos -= mostRight;
            res += process1(limit,
                    colLim | mostRight,
                    (leftDiaLim | mostRight) << 1,
                    (rightDiaLim | mostRight) >>> 1);
        }
        return res;
    }

    @Test
    public void test(){
        int n=8;
        System.out.println(NQueen(n));
        System.out.println(NQueen1(n));
    }

}
