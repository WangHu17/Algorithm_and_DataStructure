package classicrecursion;

/**
 * @author wanghu
 * @discrption： 汉诺塔
 * @create 2022-06-15 14:39
 */
public class Hanoitower {

    public void hanoi(int N){
        if (N > 0)
            f(N, "left", "right", "mid");
    }

    public void f(int N, String from, String to, String other){
        if (N == 1){
            System.out.println("move 1 from " + from + " to " + to);
        }else {
            f(N-1, from, other, to);
            System.out.println("move " + N + " from " + from + " to " + to);
            f(N-1, other, to, from);
        }
    }

}
