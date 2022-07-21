package arrayproblems;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-07-21 15:04
 */
public class Problem7 {

    public void print(int n) {
        char[][] res = new char[n][n];
        for (int i = 0; i < res.length; i++) {
            Arrays.fill(res[i], ' ');
        }
        int a = 0;
        int b = n-1;
        while (a <= b) {
            set(res, a, b);
            a += 2;
            b -= 2;
        }
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void set(char[][] arr, int a, int b) {
        for (int i=a; i<=b; i++) { // n
            arr[a][i] = '*';
        }
        for (int i=a+1; i<=b; i++) { // n-1
            arr[i][b] = '*';
        }
        for (int i=b-1; i>=a+1; i--) { // n-2
            arr[b][i] = '*';
        }
        for (int i=b-1; i>=a+2; i--) { // n-3
            arr[i][a+1] = '*';
        }
    }

    @Test
    public void test() {
        print(8);
    }

}
