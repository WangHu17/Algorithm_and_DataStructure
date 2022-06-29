package graph;

import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 使用加强堆实现
 * @create 2022-06-15 10:44
 */
public class Dijkstra2 {

    public HashMap<Node, Integer> dijkstra2(Node start, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        HashMap<Node, Integer> res = new HashMap<>();
        nodeHeap.addOrUpdateOrIgnore(start, 0);
        while (!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge:cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            res.put(cur, distance);
        }
        return res;
    }

}
