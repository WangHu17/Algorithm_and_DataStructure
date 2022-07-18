package logarithmfindrule;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 给定一个正整数N，表示有N份青草统一堆放在仓库里
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：
 * 1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 * 根据唯一的参数N，返回谁会赢。
 * @create 2022-07-18 10:00
 */
public class CowAndSheep {

    // 暴力
    public String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        int want = 1;
        while (want <= n) {
            if (whoWin(n - want).equals("后手"))
                return "先手";
            if (want <= n / 4) {
                want *= 4;
            } else {
                break;
            }
        }
        return "后手";
    }

    public String whoWin2(int n) {
        if (n % 5 == 0 || n % 5 == 2)return "后手";
        return "先手";
    }

    @Test
    public void test() {
        // 后 先 后 先 先
        for (int i = 0; i < 70; i++) {
            System.out.println(i + " " + whoWin(i));
        }
    }

}
