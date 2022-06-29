package linkedlistproblems;

import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 拷贝复杂链表
 * @create 2022-06-08 16:34
 */
public class CopyRandomList {

    private class RandomListNode{
        int val;
        RandomListNode next;
        RandomListNode random;

        public RandomListNode(int val) {
            this.val = val;
        }
    }

    // 方法一：使用哈希表
    public RandomListNode copyRandomList1(RandomListNode head){
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode p = head;
        while (p != null){
            map.put(p, new RandomListNode(p.val));
            p = p.next;
        }
        p = head;
        while (p != null){
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    // 方法二：在原链表上进行操作
    public RandomListNode copyRandomList2(RandomListNode head){
        if (head == null)return null;
        RandomListNode p = head, next = null;
        while (p != null){
            next = p.next;
            p.next = new RandomListNode(p.val);
            p.next.next = next;
            p = next;
        }
        p = head;
        RandomListNode copy = null;
        while (p != null){
            next = p.next.next;
            copy = p.next;
            copy.random = p.random != null ? p.random.next : null;
            p = next;
        }
        RandomListNode res = head.next;
        p = head;
        while (p != null){
            next = p.next.next;
            copy = p.next;
            p.next = next;
            copy.next = next != null ? next.next : null;
            p = next;
        }
        return res;
    }

}
