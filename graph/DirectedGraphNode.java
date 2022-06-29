package graph;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-14 11:18
 */
public class DirectedGraphNode {

    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }

}
