package graph;

import java.util.*;

/**
 * @author wanghu
 * @discrption： 拓扑排序，适用于 DirectedGraphNode图结构，BFS方法实现
 * @create 2022-06-14 11:18
 */
public class TopSort2 {

    public List<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (DirectedGraphNode node:graph){
            inMap.put(node, 0);
        }
        for (DirectedGraphNode node:graph){
            for (DirectedGraphNode next:node.neighbors){
                inMap.put(next, inMap.get(next) + 1);
            }
        }
        for (DirectedGraphNode node:inMap.keySet()){
            if (inMap.get(node) == 0)
                queue.add(node);
        }
        while (!queue.isEmpty()){
            DirectedGraphNode node = queue.poll();
            res.add(node);
            for (DirectedGraphNode next:node.neighbors){
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0)
                    queue.add(next);
            }
        }
        return res;
    }

}
