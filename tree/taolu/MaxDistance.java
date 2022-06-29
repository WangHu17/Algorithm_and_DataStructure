package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 树的最大距离
 * @create 2022-06-10 14:32
 */
public class MaxDistance {

    public int maxDistance(Node root){
        return process(root).maxDistance;
    }

    class Info{
        int maxDistance;
        int height;
        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public Info process(Node node){
        if (node == null)
            return new Info(0 , 0);
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height);
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(p1, Math.max(p2, p3));
        return new Info(maxDistance, height);
    }

}
