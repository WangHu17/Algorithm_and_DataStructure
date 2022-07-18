package logarithmfindrule;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如:
 * 5 = 2+3，5就是这样的数
 * 12 = 3+4+5，12就是这样的数
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 * @create 2022-07-18 10:47
 */
public class ContinuousSum {

    public boolean isSum(int n) {
        for (int i = 1; i <= n; i++) {
            int sum = i;
            for (int j = i + 1; j <= n; j++) {
                if (sum + j > n)break;
                if (sum + j == n)return true;
                sum += j;
            }
        }
        return false;
    }

    public boolean isSum2(int n){
        return n != (n & (-n));
    }

    @Test
    public void test(){
        // 0 1 2 4 8 16 ...
        for (int i=0; i<100; i++){
            System.out.println(i + " " + isSum(i) + " " + isSum2(i));
        }
    }

}
