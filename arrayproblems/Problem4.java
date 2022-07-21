package arrayproblems;

/**
 * @author wanghu
 * @discrption： 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子。
 * @create 2022-07-21 10:34
 */
public class Problem4 {

    public static void fun(int[][] arr){
        int a = 0;
        int b = arr.length - 1;
        while (a < b){
            rotate(arr, a++, b--);
        }
    }

    public static void rotate(int[][] arr, int a, int b){
        int temp = 0;
        for (int i=0; i<b-a; i++){
            temp = arr[a][a+i];
            arr[a][a+i] = arr[b-i][a];
            arr[b-i][a] = arr[b][b-i];
            arr[b][b-i] = arr[a+i][b];
            arr[a+i][b] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        fun(matrix);
        System.out.println("=========");
        printMatrix(matrix);
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
