package linkedlistproblems;

import linkedlist.Node;

/**
 * @author wanghu
 * @discrption： 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
 * [要求]：如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 * @create 2022-06-09 9:34
 */
public class IntersectNode {

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null)
            return getNoLoopNode(head1, head2);
        if (loop1 != null && loop2 != null)
            return getBothLoopNode(head1, head2, loop1, loop2);
        return null;
    }

    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null)
            return null;
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static Node getNoLoopNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node p1 = head1;
        Node p2 = head2;
        int n = 0;
        while (p1.next != null) {
            n++;
            p1 = p1.next;
        }
        while (p2.next != null) {
            n--;
            p2 = p2.next;
        }
        if (p1 != p2) return null;
        p1 = n > 0 ? head1 : head2;
        p2 = p1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n-- > 0) {
            p1 = p1.next;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static Node getBothLoopNode(Node head1, Node head2, Node loop1, Node loop2) {
        Node p1 = null;
        Node p2 = null;
        if (loop1 == loop2) { // 同一个入环节点，以该节点为尾节点找相交点
            p1 = head1;
            p2 = head2;
            int n = 0;
            while (p1.next != loop1) {
                n++;
                p1 = p1.next;
            }
            while (p2.next != loop1) {
                n--;
                p2 = p2.next;
            }
            if (p1 != p2) return null;
            p1 = n > 0 ? head1 : head2;
            p2 = p1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n-- > 0) {
                p1 = p1.next;
            }
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        } else {
            p1 = loop1.next;
            while (p1 != loop1) {
                if (p1 == loop2)
                    return loop1;
                p1 = p1.next;
            }
            return null;
        }
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

    }

}
