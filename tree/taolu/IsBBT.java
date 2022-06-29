package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 是否是平衡二叉树
 * @create 2022-06-10 11:04
 */
public class IsBBT {

    public boolean isBBT(Node root){
        return process(root).isBalanced;
    }

    class Info{
        boolean isBalanced;
        int height;
        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public Info process(Node node){
        if (node == null)
            return new Info(true, 0);
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced)isBalanced = false;
        if (Math.abs(leftInfo.height - rightInfo.height) > 1)isBalanced = false;
        return new Info(isBalanced, height);
    }

}
