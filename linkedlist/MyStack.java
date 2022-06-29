package linkedlist;

/**
 * @author wanghu
 * @discrption： 链表设计栈
 * @create 2022-05-18 15:36
 */
public class MyStack<V> {

    private Node<V> head;
    private int size;

    public MyStack() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(V val) {
        Node<V> node = new Node<>(val);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public V poll() {
        V val = null;
        if (head != null) {
            val = head.val;
            head = head.next;
            size--;
        }
        return val;
    }

    public V peek() {
        return head == null ? null : head.val;
    }

}
