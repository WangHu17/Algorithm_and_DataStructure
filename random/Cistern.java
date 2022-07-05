package random;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 蓄水池算法
 * 假设有一个源源吐出不同球的机器，
 * 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里。
 * @create 2022-07-04 14:09
 */
public class Cistern {

    public int random(int i) {
        return (int) (Math.random() * i) + 1;
    }

    public int[] bag(int n) {
        int[] bag = new int[10];
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if (index < 10) {
                bag[index++] = i;
            } else {
                if (random(i) <= 10) {
                    int out = (int) (Math.random() * 10);
                    bag[out] = i;
                }
            }
        }
        return bag;
    }

    @Test
    public void test(){
        int[] bag = bag(1000);
        System.out.println(Arrays.toString(bag));
    }

}
