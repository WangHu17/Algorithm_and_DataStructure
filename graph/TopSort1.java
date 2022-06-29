package graph;

import java.util.*;

/**
 * @author wanghu
 * @discrption： 拓扑排序，适用于自己定义的图结构，有出度和入度，BFS方法实现
 * @create 2022-06-14 10:56
 */
public class TopSort1 {

    public List<Node> topSort(Graph graph){
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> res = new ArrayList<>();
        for (Node node:graph.nodes.values()){
            inMap.put(node, node.in);
            if (node.in == 0)
                queue.add(node);
        }
        while (!queue.isEmpty()){
            Node node = queue.poll();
            res.add(node);
            for (Node next:node.nexts){
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0)
                    queue.add(next);
            }
        }
        return res;
    }

}
