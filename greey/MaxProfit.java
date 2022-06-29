package greey;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 做项目问题
 * 输入：正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱（利润）
 * K表示你只能串行的做最多k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 * @create 2022-06-12 15:15
 */
public class MaxProfit {

    public int findMaximizedCapital(int[] costs, int[] profits, int K, int W) {
        if (costs == null || profits == null || K == 0 || W == 0)return 0;
        PriorityQueue<Program> minCosts = new PriorityQueue<>(new CostComparator());
        PriorityQueue<Program> maxProfits = new PriorityQueue<>(new ProfitComparator());
        for (int i=0; i<costs.length; i++){
            minCosts.add(new Program(costs[i], profits[i]));
        }
        for (int i=0; i<K; i++){
            while (!minCosts.isEmpty() && minCosts.peek().cost <= W){
                maxProfits.add(minCosts.poll());
            }
            if (maxProfits.isEmpty())return W;
            W += maxProfits.poll().profit;
        }
        return W;
    }

    class Program {
        int cost;
        int profit;
        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    class CostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    class ProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

}
