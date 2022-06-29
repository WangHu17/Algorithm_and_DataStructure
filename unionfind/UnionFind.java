package unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 并查集
 * @create 2022-06-12 17:23
 */
public class UnionFind<T> {

    private HashMap<T, Node<T>> nodes;
    private HashMap<Node<T>, Node<T>> parent;
    private HashMap<Node<T>, Integer> sizeMap;

    public UnionFind (List<T> values) {
        nodes = new HashMap<>();
        parent = new HashMap<>();
        sizeMap = new HashMap<>();
        for (T v:values){
            Node<T> node = new Node<>(v);
            nodes.put(v, node);
            parent.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<T> findHead(T v) {
        Stack<Node<T>> path = new Stack<>();
        Node<T> node = nodes.get(v);
        while (node != parent.get(node)){
            path.push(node);
            node = parent.get(node);
        }
        while (!path.isEmpty()){
            parent.put(path.pop(), node);
        }
        return node;
    }

    public boolean isSameSet(T v1, T v2){
        return findHead(v1) == findHead(v2);
    }

    public void union(T v1, T v2) {
        Node<T> head1 = findHead(v1);
        Node<T> head2 = findHead(v2);
        if (head1 != head2){
            Integer size1 = sizeMap.get(head1);
            Integer size2 = sizeMap.get(head2);
            Node<T> big = size1 > size2 ? head1 : head2;
            Node<T> small = big == head1 ? head2 : head1;
            parent.put(small, big);
            sizeMap.put(big, size1 + size2);
            sizeMap.remove(small);
        }
    }

    public int sets(){
        return sizeMap.size();
    }

    class Node<T> {
        T val;
        public Node(T val) {
            this.val = val;
        }
    }

}
