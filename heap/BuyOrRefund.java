package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-07 16:49
 */
public class BuyOrRefund {

    class Costumer {
        private int id;
        private int buy;
        private int enterTime;
        public Costumer(int id, int buy, int time){
            this.id = id;
            this.buy = buy;
            this.enterTime = time;
        }
    }

    public class candidateComp implements Comparator<Costumer> {
        @Override
        public int compare(Costumer o1, Costumer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime;
        }
    }
    public class awardComp implements Comparator<Costumer> {
        @Override
        public int compare(Costumer o1, Costumer o2) {
            return o1.buy != o2.buy ? o1.buy - o2.buy : o1.enterTime - o2.enterTime;
        }
    }

    class WhoGetAward {

        private HashMap<Integer, Costumer> costumers;
        private HeapGreater<Costumer> candidateHeap;
        private HeapGreater<Costumer> awardHeap;
        private int limit;
        public WhoGetAward(int limit){
            costumers = new HashMap<>();
            candidateHeap = new HeapGreater<>(new candidateComp());
            awardHeap = new HeapGreater<>(new awardComp());
            this.limit = limit;
        }

        public void operate(int time, int id, boolean buyOrRefund){
            if (!buyOrRefund && !costumers.containsKey(id))return;
            if (!costumers.containsKey(id)){
                costumers.put(id, new Costumer(id, 0 , 0));
            }
            Costumer c = costumers.get(id);
            if (buyOrRefund){
                c.buy++;
            }else
                c.buy--;
            if (c.buy == 0)costumers.remove(id);
            if (!candidateHeap.contain(c) && !awardHeap.contain(c)){
                c.enterTime = time;
                if (awardHeap.size() < limit)
                    awardHeap.add(c);
                else
                    candidateHeap.add(c);
            }else if (candidateHeap.contain(c)){
                if (c.buy == 0)
                    candidateHeap.remove(c);
                else
                    candidateHeap.resign(c);
            }else {
                if (c.buy == 0)
                    awardHeap.remove(c);
                else
                    awardHeap.resign(c);
            }
            moveToAward(time);
        }

        public void moveToAward(int time){
            if (candidateHeap.isEmpty())return;
            if (awardHeap.size() < limit){
                Costumer c = candidateHeap.pop();
                c.enterTime = time;
                awardHeap.add(c);
            }else {
                if (candidateHeap.peek().buy > awardHeap.peek().buy){
                    Costumer newCos = candidateHeap.pop();
                    Costumer oldCos = awardHeap.pop();
                    newCos.enterTime = time;
                    oldCos.enterTime = time;
                    candidateHeap.add(oldCos);
                    awardHeap.add(newCos);
                }
            }
        }

        public List<Integer> getAwards(){
            List<Costumer> costumers = awardHeap.getAllElement();
            List<Integer> res = new ArrayList<>();
            for (Costumer c:costumers){
                res.add(c.id);
            }
            return res;
        }

        public List<List<Integer>> topK(int[] arr, boolean[] op, int k){
            List<List<Integer>> res = new ArrayList<>();
            WhoGetAward whoGetAward = new WhoGetAward(k);
            for (int i =0; i<arr.length; i++){
                whoGetAward.operate(i, arr[i], op[i]);
                res.add(whoGetAward.getAwards());
            }
            return res;
        }
    }

}
