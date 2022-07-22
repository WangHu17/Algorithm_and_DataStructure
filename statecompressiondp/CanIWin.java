package statecompressiondp;

/**
 * @author wanghu
 * @discrption： 有一个数 choose，和一个数 rest，可以从 1-choose 随便拿一个数，拿了之后这个数就不能再拿了。
 * 先手拿出一个数，rest 要减去它，然后后手再去拿。如果谁拿了后，rest 就小于等于 0了，那么谁就赢了。问先手能不能赢？
 * @create 2022-07-22 9:34
 */
public class CanIWin {

    // 暴力递归
    public boolean win1(int choose, int total) {
        if (total == 0) return true;
        if (choose * (choose + 1) / 2 < total) return false;
        int[] status = new int[choose + 1];
        for (int i = 1; i <= choose; i++) {
            status[i] = i;
        }
        return process1(status, total);
    }

    public boolean process1(int[] status, int rest) {
        if (rest <= 0) return false;
        for (int i = 1; i < status.length; i++) {
            if (status[i] != 0) {
                int cur = status[i];
                status[i] = 0;
                boolean next = process1(status, rest - cur);
                status[i] = cur;
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }

    // 状态压缩的暴力递归
    public boolean win2(int choose, int total) {
        if (total <= 0) return true;
        if (choose * (choose + 1) / 2 < total) return false;
        return process2(choose, 0, total);
    }

    public boolean process2(int choose, int status, int rest) {
        if (rest <= 0) return false;
        for (int i = 1; i <= choose; i++) {
            if (((1 << i) & status) == 0) {
                if (!process2(choose, status | (1 << i), rest - i)){
                    return true;
                }
            }
        }
        return false;
    }

    // 记忆化搜索
    public boolean win3(int choose, int total) {
        if (total <= 0) return true;
        if (choose * (choose + 1) / 2 < total) return false;
        int[] dp = new int[1<<(choose + 1)];
        return process3(choose, 0, total, dp);
    }

    public boolean process3(int choose, int status, int rest, int[] dp) {
        if (dp[status] != 0){
            return dp[status] == 1;
        }
        boolean ans = false;
        if(rest > 0){
            for (int i = 1; i <= choose; i++) {
                if (((1 << i) & status) == 0) {
                    if (!process3(choose, status | (1 << i), rest - i, dp)){
                        ans = true;
                        break;
                    }
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }



}
