package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-14 11:36
 */
public class TopSort3 {

    class Record {
        DirectedGraphNode node;
        int deep;
        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode node:graph){
            f(node, order);
        }
        ArrayList<Record> records = new ArrayList<>();
        for (Record record:order.values()){
            records.add(record);
        }
        records.sort(new MyComparator());
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record record:records){
            res.add(record.node);
        }
        return res;
    }

    public Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order){
        if (order.containsKey(node))
            return order.get(node);
        int follow = 0;
        for (DirectedGraphNode next:node.neighbors){
            follow = Math.max(follow, f(next, order).deep);
        }
        Record record = new Record(node, follow + 1);
        order.put(node, record);
        return record;
    }

}
