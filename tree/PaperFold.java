package tree;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 纸条折痕问题
 * @create 2022-06-10 10:30
 */
public class PaperFold {

    public void paperFold(int n){
        process(1, n, true);
    }

    public void process(int i, int n, boolean down){
        if (i > n)return;
        process(i+1, n, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(i+1, n, false);
    }

    @Test
    public void test(){
        paperFold(4);
    }

}
