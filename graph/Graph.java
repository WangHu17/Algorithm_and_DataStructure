package graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author wanghu
 * @discrption： 自定义的图结构，及将数组形式的图转化为自己的图结构
 * @create 2022-06-14 9:46
 */
public class Graph {

    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public static Graph createGraph(int[][] arr){
        Graph graph = new Graph();
        for (int i=0; i<arr.length; i++){
            int weight = arr[i][0];
            int from = arr[i][1];
            int to = arr[i][2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            graph.edges.add(newEdge);
            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);
        }
        return graph;
    }
}
