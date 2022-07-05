package morris;

import tree.Node;

/**
 * @author wanghu
 * @discrption： morris先序遍历
 * @create 2022-07-04 14:44
 */
public class MorrisPre {

    public static void morrisPre(Node head){
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
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
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
        morrisPre(head);
    }

}
