package tree.order;

import tree.Node;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 非递归中序
 * @create 2022-06-09 10:59
 */
public class InOrderNoRecursion {

    public static void inOrder(Node cur) {
        if (cur == null) return;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node node = stack.pop();
                System.out.println(node.value);
                cur = node.right;
            }
        }
    }

}
