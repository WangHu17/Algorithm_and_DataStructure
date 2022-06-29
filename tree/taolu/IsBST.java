package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 是否是搜索二叉树
 * @create 2022-06-10 11:25
 */
public class IsBST {

    public boolean isBST(Node root) {
        return process(root).isbst;
    }

    class Info {
        boolean isbst;
        int max;
        int min;

        public Info(boolean isbst, int max, int min) {
            this.isbst = isbst;
            this.max = max;
            this.min = min;
        }
    }

    public Info process(Node node) {
        if (node == null)
            return null;
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value;
        int min = node.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        boolean isbst = true;
        if (leftInfo != null && !leftInfo.isbst) isbst = false;
        if (rightInfo != null && !rightInfo.isbst) isbst = false;
        if (leftInfo != null && leftInfo.max > node.value) isbst = false;
        if (rightInfo != null && rightInfo.min < node.value) isbst = false;
        return new Info(isbst, max, min);
    }

}
