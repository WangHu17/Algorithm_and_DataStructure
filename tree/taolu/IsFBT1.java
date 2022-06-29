package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 是否是满二叉树
 * @create 2022-06-10 14:58
 */
public class IsFBT1 {

    public boolean isFBT(Node root){
        if (root == null)return true;
        Info info = process(root);
        return (2 * info.height - 1) == info.nodes;
    }

    class Info{
        int height;
        int nodes;
        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public Info process(Node node){
        if (node == null)
            return new Info(0, 0);
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }

}
