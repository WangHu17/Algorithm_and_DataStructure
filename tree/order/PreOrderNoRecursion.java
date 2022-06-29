package tree.order;

import tree.Node;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 非递归先序
 * @create 2022-06-09 10:59
 */
public class PreOrderNoRecursion {

    public static void preOrder(Node root){
        if (root == null)return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.value);
            if (node.right!=null)
                stack.push(node.right);
            if (node.left!=null)
                stack.push(node.left);
        }
    }

}
