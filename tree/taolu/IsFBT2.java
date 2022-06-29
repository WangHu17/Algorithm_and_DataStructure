package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-10 15:04
 */
public class IsFBT2 {

    public boolean isFBT(Node root){
        return process(root).isfbt;
    }

    class Info {
        boolean isfbt;
        int height;
        public Info(boolean isfbt, int height) {
            this.isfbt = isfbt;
            this.height = height;
        }
    }

    public Info process(Node node){
        if (node == null)
            return new Info(true, 0);
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean isfbt = leftInfo.isfbt && rightInfo.isfbt && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isfbt, height);
    }

}
