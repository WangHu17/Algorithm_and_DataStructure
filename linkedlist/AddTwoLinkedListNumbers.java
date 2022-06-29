package linkedlist;

/**
 * @author wanghu
 * @discrption： 两条链表相加
 * @create 2022-05-18 15:36
 */
public class AddTwoLinkedListNumbers {

    public ListNode addTwoLinkedListNumbers(ListNode head1, ListNode head2) {
        int len1 = len(head1);
        int len2 = len(head2);
        ListNode l = len1 >= len2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;
        ListNode curL = l, last = l;
        int flag = 0, num = 0;
        while (s != null) {
            num = curL.val + s.val + flag;
            curL.val = num % 10;
            flag = num / 10;
            last = curL;
            curL = curL.next;
            s = s.next;
        }
        while (curL != null) {
            num = curL.val + flag;
            curL.val = num % 10;
            flag = num / 10;
            last = curL;
            curL = curL.next;
        }
        if (flag != 0){
            last.next = new ListNode(1);
        }
        return l;
    }

    public int len(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

}
