package tree.order;

import tree.Node;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 非递归后序
 * @create 2022-06-09 10:59
 */
public class PostOrderNoRecursion {

    public static void postOrder(Node root) {
        if (root == null) return;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            Node node = s1.pop();
            s2.push(node);
            if (node.left != null)
                s1.push(node.left);
            if (node.right != null)
                s1.push(node.right);
        }
        while (!s2.isEmpty()) {
            Node node = s2.pop();
            System.out.println(node.value);
        }
    }

}
