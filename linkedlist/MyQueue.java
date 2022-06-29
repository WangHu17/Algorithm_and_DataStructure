package linkedlist;

/**
 * @author wanghu
 * @discrption： 链表设计队列
 * @create 2022-05-18 15:36
 */
public class MyQueue<V> {

    private Node<V> head;
    private Node<V> tail;
    private int size;

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void offer(V val){
        Node<V> node = new Node<>(val);
        if (tail == null){
            head = node;
            tail = node;
        }else {
            tail.next = node;
            tail = node;
        }
        size++;
    }
    public V poll(){
        V res = null;
        if (head != null){
            res = head.val;
            head = head.next;
            size--;
        }
        if (head == null){
            tail = null;
        }
        return res;
    }
    public V peek(){
        V res = null;
        if (head != null){
            res = head.val;
        }
        return res;
    }

}
