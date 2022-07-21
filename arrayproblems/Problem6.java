package arrayproblems;

/**
 * @author wanghu
 * @discrption： 给定一个正方形或者长方形矩阵matrix，实现zigzag打印
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * 打印: 0 1 3 6 4 2 5 7 8
 * @create 2022-07-21 14:34
 */
public class Problem6 {

    public static void print(int[][] arr) {
        int r1 = 0;
        int c1 = 0;
        int r2 = 0;
        int c2 = 0;
        int endR = arr.length - 1;
        int endC = arr[0].length - 1;
        boolean down = false;
        while (r1 != endR + 1) {
            printLevel(arr, r1, c1, r2, c2, down);
            r1 = c1 == endC ? r1 + 1 : r1;
            c1 = c1 == endC ? c1 : c1 + 1;
            c2 = r2 == endR ? c2 + 1 : c2;
            r2 = r2 == endR ? r2 : r2 + 1;
            down = !down;
        }
    }

    public static void printLevel(int[][] arr, int r1, int c1, int r2, int c2, boolean down) {
        if (down) {
            while (r1 != r2 + 1) {
                System.out.print(arr[r1++][c1--] + " ");
            }
        }else {
            while (r2 != r1 - 1) {
                System.out.print(arr[r2--][c2++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrix(matrix);
        print(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
