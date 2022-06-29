package graph;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-15 11:30
 */
public class NodeRecord {

    public Node node;
    public int distance;

    public NodeRecord(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
