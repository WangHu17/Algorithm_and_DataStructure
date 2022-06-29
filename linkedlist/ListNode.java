package linkedlist;

/**
 * @author wanghu
 * @discrption： 链表节点
 * @create 2022-05-18 17:22
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
