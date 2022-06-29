package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 是否是完全二叉树
 * @create 2022-06-11 15:22
 */
public class IsCBT {

    public boolean isCBT(Node root) {
        if (root == null)return true;
        return process(root).isCBT;
    }

    class Info {
        boolean isFull;
        boolean isCBT;
        int height;
        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public Info process(Node node){
        if (node == null)
            return new Info(true, true, 0);
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (isFull){
            isCBT = true;
        }else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            isCBT = true;
        }else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            isCBT = true;
        }else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
            isCBT = true;
        }
        return new Info(isFull, isCBT, height);
    }

}
