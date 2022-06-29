package tree;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 是否是完全二叉树
 * @create 2022-06-10 10:44
 */
public class IsCBT {

    public boolean isCBT(Node root) {
        if (root == null) return true;
        Node l = null;
        Node r = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            l = node.left;
            r = node.right;
            if ((flag && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) queue.add(l);
            if (r != null) queue.add(r);
            if (l == null || r == null) flag = true;
        }
        return true;
    }

}
