package graph;

/**
 * @author wanghu
 * @discrption： 自定义图的边结构
 * @create 2022-06-14 9:47
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
