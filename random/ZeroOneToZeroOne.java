package random;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 一个固定概率返回0，1的函数，设计为等概率返回0，1。
 * @create 2022-05-18 10:14
 */
public class ZeroOneToZeroOne {

    public int f1(){
        return Math.random() < 0.8 ? 0 : 1;
    }

    public int f2(){
        int res;
        do {
            res = f1();
        }while (res == f1());
        return res;
    }

    @Test
    public void test(){
        int[] count = new int[2];
        for(int i=0; i<1000000; i++){
            count[f2()]++;
        }
        for (int n:count){
            System.out.println(n);
        }
    }

}
