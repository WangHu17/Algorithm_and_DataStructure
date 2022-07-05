package morris;

import tree.Node;

/**
 * @author wanghu
 * @discrption： morris 遍历：O(1)的空间复杂度遍历树
 * @create 2022-07-04 14:26
 */
public class Morris {

    public void morris(Node head){
        if (head == null)return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

}
