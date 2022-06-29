package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author wanghu
 * @discrption： 最短路径：Dijkstra算法
 * @create 2022-06-14 16:11
 */
public class Dijkstra {

    public HashMap<Node, Integer> dijkstra(Node from){
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();
        distanceMap.put(from, 0);
        selectedNodes.add(from);
        Node minNode = from;
        while (minNode != null){
            Integer distance = distanceMap.get(minNode);
            for (Edge edge:minNode.edges){
                Node toNode = edge.to;
                if (!selectedNodes.contains(toNode)){
                    distanceMap.put(toNode, distance + edge.weight);
                }else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        Integer min = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry:distanceMap.entrySet()){
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < min){
                minNode = node;
                min = distance;
            }
        }
        return minNode;
    }

}
