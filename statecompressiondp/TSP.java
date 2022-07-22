package statecompressiondp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghu
 * @discrption： 有N个城市，任何两个城市之间的都有距离，任何一座城市到自己的距离都为0。所有点到点的距离都存在一个N*N的二维数组matrix里，
 * 也就是整张图由邻接矩阵表示。现要求一旅行商从k城市出发必须经过每一个城市且只在一个城市逗留一次，最后回到出发的k城，返回总距离最短的路的距离。
 * 参数给定一个matrix，给定k。
 * @create 2022-07-22 10:17
 */
public class TSP {

    // 暴力递归
    public int func1(int[][] matrix) {
        int N = matrix.length;
        ArrayList<Integer> set = new ArrayList<>();
        for (int i=0; i<N; i++){
            set.add(1);
        }
        return process1(matrix, set, 0);
    }
    public int process1(int[][] matrix, List<Integer> set, int start) {
        int num = 0;
        for (int i=0; i<set.size(); i++){
            if (set.get(i) != num)
                num++;
        }
        if (num == 1){
            return matrix[start][0];
        }
        set.set(start, num);
        int min = Integer.MAX_VALUE;
        for (int i=0; i<set.size(); i++){
            if (set.get(i) != num){
                min = Math.min(min, matrix[start][i] + process1(matrix, set, i));
            }
        }
        set.set(start, 1);
        return min;
    }

    // 状态压缩的暴力递归
    public int func2(int[][] matrix) {
        int N = matrix.length;
        int cityStatus = 1 << N - 1;
        return process2(matrix, cityStatus, 0);
    }
    public int process2(int[][] matrix, int cityStatus, int start) {
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        cityStatus &= ~(1 << start);
        int min = Integer.MAX_VALUE;
        for (int i=0; i<matrix.length; i++){
            if ((cityStatus & (1 << i)) != 0) {
                min = Math.min(min, matrix[start][i] + process2(matrix, cityStatus, i));
            }
        }
        cityStatus |= (1 << start);
        return min;
    }

    // 记忆化搜索
    public int func3(int[][] matrix) {
        int N = matrix.length;
        int cityStatus = 1 << N - 1;
        int[][] dp = new int[1 << N][N];
        for (int i=0; i<(1<<N); i++){
            for (int j=0; j<N; j++){
                dp[i][j] = -1;
            }
        }
        return process3(matrix, cityStatus, 0, dp);
    }
    public int process3(int[][] matrix, int cityStatus, int start, int[][] dp) {
        if (dp[cityStatus][start] != -1){
            return dp[cityStatus][start];
        }
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }else {
            cityStatus &= ~(1 << start);
            int min = Integer.MAX_VALUE;
            for (int i=0; i<matrix.length; i++){
                if ((cityStatus & (1 << i)) != 0) {
                    min = Math.min(min, matrix[start][i] + process3(matrix, cityStatus, i, dp));
                }
            }
            cityStatus |= (1 << start);
            dp[cityStatus][start] = min;
        }
        return dp[cityStatus][start];
    }

    @Test
    public void test() {
        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 20000; i++) {
            int[][] matrix = generateGraph(len, value);
            int origin = (int) (Math.random() * matrix.length);
            int ans1 = func1(matrix);
            int ans2 = func2(matrix);
            int ans3 = func3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("fuck");
            }
        }
        System.out.println("功能测试结束");

//        len = 22;
//        System.out.println("性能测试开始，数据规模 : " + len);
//        int[][] matrix = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                matrix[i][j] = (int) (Math.random() * value) + 1;
//            }
//        }
//        for (int i = 0; i < len; i++) {
//            matrix[i][i] = 0;
//        }
//        long start;
//        long end;
//        start = System.currentTimeMillis();
//        t4(matrix);
//        end = System.currentTimeMillis();
//        System.out.println("运行时间 : " + (end - start) + " 毫秒");
//        System.out.println("性能测试结束");
    }

    public static int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

}
