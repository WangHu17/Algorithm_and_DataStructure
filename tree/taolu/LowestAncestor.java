package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先。
 * @create 2022-06-11 16:03
 */
public class LowestAncestor {

    public Node lowestAncestor(Node root, Node a, Node b){
        if (root == null)return null;
        return process(root, a, b).ans;
    }

    class Info {
        boolean findA;
        boolean findB;
        Node ans;
        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public Info process(Node node, Node a, Node b){
        if (node == null)
            return new Info(false, false, null);
        Info leftInfo = process(node.left, a, b);
        Info rightInfo = process(node.right, a, b);
        boolean findA = node == a || leftInfo.findA || rightInfo.findA;
        boolean findB = node == b || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        if (leftInfo.ans != null)
            ans = leftInfo.ans;
        else if (rightInfo.ans != null)
            ans = rightInfo.ans;
        else {
            if (findA && findB)ans = node;
        }
        return new Info(findA, findB, ans);
    }

}
