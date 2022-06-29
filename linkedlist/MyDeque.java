package linkedlist;

/**
 * @author wanghu
 * @discrption： 双链表设计双端队列
 * @create 2022-05-18 15:36
 */
public class MyDeque<V> {

    private DNode<V> head;
    private DNode<V> tail;
    private int size;

    public MyDeque() {
        head=null;
        tail=null;
        size=0;
    }
    public void pushHead(V val){
        DNode<V> node = new DNode<>(val);
        if (head == null){
            head=node;
            tail=node;
        }else {
            node.next = head;
            head.last = node;
            head = node;
        }
        size++;
    }
    public void pushTail(V val){
        DNode<V> node = new DNode<>(val);
        if (tail == null){
            head=node;
            tail=node;
        }else {
            tail.next = node;
            node.last = tail;
            tail = node;
        }
        size++;
    }
    public V pollHead(){
        V res = null;
        if (head != null){
            res = head.val;
            head = head.next;
            head.last = null;
            size--;
        }
        if (head == null){
            tail = null;
        }
        return res;
    }
    public V pollTail(){
        V res = null;
        if (tail != null){
            res = tail.val;
            tail = tail.last;
            tail.next = null;
            size--;
        }
        if (tail == null){
            head = null;
        }
        return res;
    }
    public V peekHead(){
        return head==null?null:head.val;
    }
    public V peekTail(){
        return tail==null?null:tail.val;
    }

    class DNode<V> {
        V val;
        DNode next;
        DNode last;
        public DNode(V val) {
            this.val = val;
        }
    }
}
