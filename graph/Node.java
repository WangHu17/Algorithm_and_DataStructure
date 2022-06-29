package graph;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-14 9:47
 */
public class Node {

    public int val;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
