package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author wanghu
 * @discrption： 最小生成树：Prim算法，使用并查集实现
 * @create 2022-06-14 15:35
 */
public class Prim {

    class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public Set<Edge> prim(Graph graph){
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        HashSet<Node> nodeSet = new HashSet<>();
        HashSet<Edge> res = new HashSet<>();
        for (Node node:graph.nodes.values()){
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                for (Edge edge:node.edges){
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)){
                        nodeSet.add(toNode);
                        res.add(edge);
                        for (Edge edge1:toNode.edges){
                            priorityQueue.add(edge1);
                        }
                    }
                }
            }
        }
        return res;
    }

}
