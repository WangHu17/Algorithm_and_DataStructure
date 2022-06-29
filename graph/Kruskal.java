package graph;

import com.sun.source.tree.BreakTree;

import java.util.*;

/**
 * @author wanghu
 * @discrption： 使用并查集实现
 * @create 2022-06-14 14:54
 */
public class Kruskal {

    class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public Set<Edge> kruskal(Graph graph){
        HashSet<Edge> res = new HashSet<>();
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        UnionFind unionFind = new UnionFind(graph);
        for (Edge edge:graph.edges)
            edges.add(edge);
        while (!edges.isEmpty()){
            Edge edge = edges.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)){
                res.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return res;
    }

}
