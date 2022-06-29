package graph;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption： 图结构：只有点，关联信息都在点内部
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
