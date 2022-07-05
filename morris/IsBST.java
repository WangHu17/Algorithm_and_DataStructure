package morris;

import tree.Node;

/**
 * @author wanghu
 * @discrption： morris遍历判断搜索二叉树
 * @create 2022-07-04 15:06
 */
public class IsBST {

    public static boolean isBST(Node head){
        if (head == null)return true;
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean res = true;
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
            if (pre != null && pre >= cur.value){
                res =false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return res;
    }

    public static void main(String[] args) {
        /*
                4
             2     6
           1   3  5  7
         */
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        System.out.println(isBST(head));
    }

}
