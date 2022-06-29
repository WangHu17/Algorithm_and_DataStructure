package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-20 11:30
 */
public class MinPathSum {

    public int minPathSum1(int[][] arr){
        if (arr == null || arr.length == 0)return 0;
        int row = arr.length;
        int col = arr[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = arr[0][0];
        for (int i=1; i<col; i++){
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }
        for (int i=1; i<row; i++){
            dp[i][0] = dp[i-1][0] + arr[i][0];
        }
        for (int i=1; i<row; i++){
            for (int j=1; j<col; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + arr[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    public int minPathSum2(int[][] arr){
        if (arr == null || arr.length == 0)return 0;
        int row = arr.length;
        int N = arr[0].length;
        int[] dp = new int[N];
        dp[0] = arr[0][0];
        for (int i=1; i<N; i++){
            dp[i] = dp[i-1] + arr[0][i];
        }
        for (int i=1; i<row; i++){
            dp[0] += arr[i][0];
            for (int j=1; j<N; j++){
                dp[j] = Math.min(dp[j], dp[j-1]) + arr[i][j];
            }
        }
        return dp[N-1];
    }

    @Test
    public void test() {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
