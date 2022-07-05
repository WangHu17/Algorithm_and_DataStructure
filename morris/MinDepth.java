package morris;

import tree.Node;

/**
 * @author wanghu
 * @discrption： morris遍历求树的最小深度
 * @create 2022-07-04 15:13
 */
public class MinDepth {

    public static int minDepth(Node head){
        if (head == null)return 0;
        Node cur = head;
        Node mostRight = null;
        int level = 0;
        int minDepth = Integer.MAX_VALUE;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                int rightEdgeSize = 1;
                while (mostRight.right != null && mostRight.right != cur){
                    rightEdgeSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    level++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    if (mostRight.left == null){
                        minDepth = Math.min(minDepth, level);
                    }
                    level -= rightEdgeSize;
                }
            }else {
                level++;
            }
            cur = cur.right;
        }
        int rightEdgeSize = 1;
        cur = head;
        while (cur.right != null){
            rightEdgeSize++;
            cur = cur.right;
        }
        if (cur.left == null && cur.right == null){
            minDepth = Math.min(minDepth, rightEdgeSize);
        }
        return minDepth;
    }

}
