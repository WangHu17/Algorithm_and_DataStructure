package linkedlistproblems;

import linkedlist.ListNode;

/**
 * @author wanghu
 * @discrption： 回文链表
 * @create 2022-06-08 15:19
 */
public class PalindromeList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode p1 = head;
        ListNode p2 = head;
        // 找中点
        while (p2.next != null && p2.next.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        // 逆序右半段链表
        ListNode cur = p1.next;
        p1.next = null;
        ListNode pre = p1, next = null;
        while (cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 遍历左右两条链表判断是否相同
        ListNode head1 = head;
        ListNode head2 = pre;
        boolean res = true;
        while (head1 != null && head2 != null){
            if (head1.val != head2.val){
                res = false;
                break;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        // 还原链表
        cur = pre;
        pre = null;
        while (cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return res;
    }


}
