package linkedlist;

/**
 * @author wanghu
 * @discrption： 带泛型的链表节点
 * @create 2022-06-08 10:42
 */
public class Node<V> {
    V val;
    Node next;
    public Node(V val) {
        this.val = val;
    }
}
