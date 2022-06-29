package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 拓扑排序，适用于 DirectedGraphNode图结构，DFS方法实现(比较每个点的点次，点次即该点通过所有路径走到底经过的点的数量（经过的点可以重复），点次大的排前面。)
 * @create 2022-06-14 11:36
 */
public class TopSort4 {

    class Record {
        DirectedGraphNode node;
        long nodes;
        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
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
        int nodes = 0;
        for (DirectedGraphNode next:node.neighbors){
            nodes += f(next, order).nodes;
        }
        Record record = new Record(node, nodes + 1);
        order.put(node, record);
        return record;
    }

}
