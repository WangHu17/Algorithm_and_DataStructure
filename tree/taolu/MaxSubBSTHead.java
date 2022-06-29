package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-11 15:42
 */
public class MaxSubBSTHead {

    public Node maxBSTSubtreeHead(Node root){
        if (root == null)return null;
        return process(root).maxSubBSTHead;
    }

    class Info {
        Node maxSubBSTHead;
        int maxBSTSubtreeSize;
        int max;
        int min;
        public Info(Node maxSubBSTHead, int maxBSTSubtreeSize, int max, int min) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.max = max;
            this.min = min;
        }
    }

    public Info process(Node node){
        if (node == null)return null;
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value, min = node.value, maxBSTSubtreeSize = 0;
        Node maxSubBSTHead = null;
        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxBSTSubtreeSize = leftInfo.maxBSTSubtreeSize;
        }
        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (rightInfo.maxBSTSubtreeSize > leftInfo.maxBSTSubtreeSize){
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxBSTSubtreeSize = rightInfo.maxBSTSubtreeSize;
            }
        }
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxSubBSTHead == node.left);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxSubBSTHead == node.right);
        if (leftBST && rightBST){
            boolean leftMaxLessThanCur = leftInfo == null ? true : (leftInfo.max < node.value);
            boolean rightMinMoreThanCur = rightInfo == null ? true : (rightInfo.min > node.value);
            if (leftMaxLessThanCur && rightMinMoreThanCur){
                maxBSTSubtreeSize = (leftInfo == null ? 0 : leftInfo.maxBSTSubtreeSize) + (rightInfo == null ? 0 : rightInfo.maxBSTSubtreeSize) + 1;
                maxSubBSTHead = node;
            }
        }
        return new Info(maxSubBSTHead, maxBSTSubtreeSize, max, min);
    }

}
