package random;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 将一个等概率返回 1-5 的随机数的函数设计为返回 1-7 的随机数的函数。
 * @create 2022-05-18 9:31
 */
public class OneFiveToOneSeven {

    // 随机1-5
    public int f1(){
        return (int)(Math.random() * 5) + 1;
    }

    // 随机0，1
    public int f2(){
        int res;
        do{
            res = f1();
        }while (res == 3);
        return res < 3 ? 0 : 1;
    }

    // 随机0-7(000-111)
    public int f3(){
        return (f2() << 2) + (f2() << 1) + f2();
    }

    // 随机0-6
    public int f4(){
        int res;
        do{
            res = f3();
        }while (res == 7);
        return res;
    }

    // 随机1-7
    public int f5(){
        return f4()+1;
    }

    @Test
    public void test(){
        int[] count = new int[8];
        for(int i=0; i<1000000; i++){
            count[f5()]++;
        }
        for (int n:count){
            System.out.println(n);
        }
    }

}
