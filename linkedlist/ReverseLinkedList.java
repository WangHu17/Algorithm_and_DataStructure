package linkedlist;

/**
 * @author wanghu
 * @discrption： 单双链表逆序
 * @create 2022-05-18 11:28
 */
public class ReverseLinkedList {

    private class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
    private class Node1 {
        int val;
        Node1 next;
        Node1 last;
        public Node1(int val) {
            this.val = val;
        }
    }

    public Node reverseLinkedList(Node head){
        Node pre = null, next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public Node1 reverseDoubleLinkedList(Node1 head){
        Node1 pre = null, next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
