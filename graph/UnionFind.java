package graph;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 并查集的实现
 * @create 2022-06-14 15:14
 */
public class UnionFind {

    private HashMap<Node, Node> parent;
    private HashMap<Node, Integer> sizeMap;

    public UnionFind(Graph graph) {
        for (Node node:graph.nodes.values()){
            parent.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node find(Node node){
        Stack<Node> path = new Stack<>();
        while (node != parent.get(node)){
            path.push(node);
            node = parent.get(node);
        }
        while (!path.isEmpty()){
            parent.put(path.pop(), node);
        }
        return node;
    }

    public boolean isSameSet(Node node1, Node node2){
        return parent.get(node1) == parent.get(node2);
    }

    public void union(Node node1, Node node2){
        Node h1 = find(node1);
        Node h2 = find(node2);
        if (h1 != h2){
            Integer size1 = sizeMap.get(h1);
            Integer size2 = sizeMap.get(h2);
            if (size1 >= size2){
                parent.put(h2, h1);
                sizeMap.put(h1, size1 + size2);
                sizeMap.remove(h2);
            }else {
                parent.put(h1, h2);
                sizeMap.put(h2, size1 + size2);
                sizeMap.remove(h1);
            }
        }
    }
}
