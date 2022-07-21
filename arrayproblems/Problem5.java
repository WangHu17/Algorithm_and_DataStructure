package arrayproblems;

/**
 * @author wanghu
 * @discrption： 给定一个长方形矩阵matrix，实现螺旋打印。
 * @create 2022-07-21 11:16
 */
public class Problem5 {

    public static void spiralOrderPrint(int[][] arr) {
        int row1 = 0;
        int col1 = 0;
        int row2 = arr.length - 1;
        int col2 = arr[0].length - 1;
        while (row1 <= row2 && col1 <= col2){
            printEdge(arr, row1++, col1++, row2--, col2--);
        }
    }

    public static void printEdge(int[][] arr, int r1, int c1, int r2, int c2){
        if (r1 == r2){
            for (int i=c1; i<=c2; i++){
                System.out.print(arr[r1][i] + " ");
            }
        }else if (c1 == c2){
            for (int i=r1; i<=r2; i++){
                System.out.print(arr[i][c1] + " ");
            }
        }else {
            int r = r1;
            int c = c1;
            while (c != c2) {
                System.out.print(arr[r][c++] + " ");
            }
            while (r != r2) {
                System.out.print(arr[r++][c] + " ");
            }
            while (c != c1) {
                System.out.print(arr[r][c--] + " ");
            }
            while (r != r1) {
                System.out.print(arr[r--][c] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        spiralOrderPrint(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
