package tree.taolu;

import tree.Node;

/**
 * @author wanghu
 * @discrption： 最大的搜索二叉树子树的大小
 * @create 2022-06-11 14:13
 */
public class MaxBSTSubtreeSize {

    public int maxBSTSubtreeSize(Node root){
        if (root == null)return 0;
        return process(root).maxBSTSubtreeSize;
    }

    class Info {
        int maxBSTSubtreeSize;
        int allSize;
        int max;
        int min;
        public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public Info process(Node node){
        if (node == null)return null;
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value, min = node.value, allSize = 0;
        int p1 = -1, p2 = -1;
        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allSize += leftInfo.allSize;
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            allSize += rightInfo.allSize;
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST){
            boolean leftMaxLessThanCur = leftInfo == null ? true : (leftInfo.max < node.value);
            boolean rightMinMoreThanCur = rightInfo == null ? true : (rightInfo.min > node.value);
            if (leftMaxLessThanCur && rightMinMoreThanCur){
                p3 += (leftInfo == null ? 0 : leftInfo.allSize) + (rightInfo == null ? 0 : rightInfo.allSize) + 1;
            }
        }
        return new Info(Math.max(p1,Math.max(p2,p3)), allSize, max, min);
    }

}
