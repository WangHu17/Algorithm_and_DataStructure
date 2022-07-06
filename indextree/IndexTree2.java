package indextree;

/**
 * @author wanghu
 * @discrption： 二维 indextree
 * @create 2022-07-06 11:31
 */
public class IndexTree2 {

    private int[][] tree;
    private int[][] num;
    private int N;
    private int M;

    public IndexTree2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        N = matrix.length;
        M = matrix[0].length;
        tree = new int[N + 1][M + 1];
        num = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                update(i,j,matrix[i][j]);
            }
        }
    }

    public int sum(int row, int col) {
        int res = 0;
        for (int i = row + 1; i > 0; i -= i & -i) {
            for (int j = col + 1; j > 0; j -= j & -j) {
                res += tree[i][j];
            }
        }
        return res;
    }

    public void update(int row, int col, int val) {
        int add = val - num[row][col];
        num[row][col] = val;
        for (int i = row + 1; i <= N; i += i & -i) {
            for (int j = col + 1; j <= M; j += j & -j) {
                tree[i][j] += add;
            }
        }
    }

    public int sumRange(int row1, int col1, int row2, int col2) {
        if (N == 0 || M == 0) return 0;
        return sum(row2, col2) - sum(row2, col1 - 1) - sum(row1 - 1, col2) + sum(row1 - 1, col1 - 1);
    }
}
