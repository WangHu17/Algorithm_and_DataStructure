package linkedlistproblems;

import linkedlist.ListNode;

/**
 * @author wanghu
 * @discrption： 给定链表头结点和一个划分值，将链表中小于划分值的节点放在左边，等于划分值的节点放在中间，大于划分值的节点放在右边。
 * @create 2022-06-08 16:09
 */
public class ListPartition {

    public ListNode listPartition(ListNode head, int pivot){
        ListNode sH = null, sT = null, eH = null, eT = null, mH = null, mT = null;
        while (head != null){
            ListNode next = head.next;
            head.next = null;
            if (head.val < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.val == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        if (sT != null){
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null){
            eT = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

}
