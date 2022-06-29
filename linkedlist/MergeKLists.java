package linkedlist;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author wanghu
 * @discrption： 合并k个已排序的链表
 * @create 2022-05-18 15:38
 */
public class MergeKLists {

    public ListNode mergeKLists(ArrayList<ListNode> nodes){
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((v1,v2)->v1.val - v2.val);
        for (ListNode node:nodes){
            if (node != null)
                priorityQueue.offer(node);
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (!priorityQueue.isEmpty()){
            ListNode node = priorityQueue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null)
                priorityQueue.offer(node.next);
        }
        return head.next;
    }

}
