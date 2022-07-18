package logarithmfindrule;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 * @create 2022-07-15 11:05
 */
public class BagAndApple {

    // 暴力
    public int minBags(int N){
        if (N == 0)return 0;
        if(N < 6)return -1;
        int eightBag = N >> 3;
        int rest = N - (eightBag << 3);
        while (eightBag >= 0){
            if (rest % 6 == 0){
                return eightBag + rest/6;
            }else {
                eightBag--;
                rest += 8;
            }
        }
        return -1;
    }

    // 找规律
    public int minBags2(int N){
        if (N == 0)return 0;
        if ((N & 1) != 0)return -1;
        if (N < 18){
            return (N == 6 || N == 8) ? 1 : (N == 12 || N == 14 || N == 16) ? 2 : -1;
        }
        return (N-18)/8+3;
    }

    @Test
    public void test(){
        for (int i=0; i<200; i++){
            System.out.println( i + " " +minBags2(i));
        }
    }

}
