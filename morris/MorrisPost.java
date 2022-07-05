package morris;

import tree.Node;

/**
 * @author wanghu
 * @discrption： morris后序遍历
 * @create 2022-07-04 14:44
 */
public class MorrisPost {

    public static void morrisPost(Node head){
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
                    printRightEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printRightEdge(head);
    }

    public static void printRightEdge(Node node){
        Node tail = reverse(node);
        Node cur = tail;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverse(tail);
    }

    public static Node reverse(Node start){
        Node pre = null;
        Node next = null;
        while (start != null){
            next = start.right;
            start.right = pre;
            pre = start;
            start = next;
        }
        return pre;
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
        morrisPost(head);
    }

}
