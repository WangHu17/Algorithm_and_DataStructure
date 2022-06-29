package linkedlist;

/**
 * @author wanghu
 * @discrption： 链表每 k个节点逆序
 * @create 2022-05-18 15:36
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k){
        ListNode start = head;
        ListNode end = getKEnd(start, k);
        if (end == null)return head;
        head = end;
        reverse(start, end);
        ListNode lastEnd = start;
        while (start.next != null){
            start = start.next;
            end = getKEnd(start, k);
            if (end == null)return head;
            reverse(start,end);
            // 将上一条尾节点指向这一条的开始节点
            lastEnd.next = end;
            // 将上一条尾节点变为这一条的尾节点
            lastEnd = start;
        }
        return head;
    }
    public ListNode getKEnd(ListNode start, int k){
        while ( --k != 0 && start != null){
            start = start.next;
        }
        return start;
    }
    public void reverse(ListNode start, ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while(cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

}
