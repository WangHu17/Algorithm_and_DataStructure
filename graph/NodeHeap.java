package graph;

import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 加强堆
 * @create 2022-06-15 11:31
 */
public class NodeHeap {

    private Node[] nodes;
    private HashMap<Node, Integer> indexMap;
    private HashMap<Node, Integer> distanceMap;
    private int size;

    public NodeHeap(int size) {
        nodes = new Node[size];
        indexMap = new HashMap<>();
        distanceMap = new HashMap<>();
        size = 0;
    }

    public void addOrUpdateOrIgnore(Node node, int distance){
        if (inHeap(node)){
            distanceMap.put(node, Math.min(distanceMap.get(node), distance));
            heapInsert(indexMap.get(node));
        }
        if (!isEntered(node)){
            nodes[size] = node;
            indexMap.put(node, size);
            distanceMap.put(node, distance);
            heapInsert(size++);
        }
    }

    public NodeRecord pop(){
        NodeRecord record = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
        swap(0, size-1);
        indexMap.put(nodes[size-1], -1);
        distanceMap.remove(nodes[size-1]);
        nodes[size-1] = null;
        heapify(0, --size);
        return record;
    }

    public void heapInsert(int index){
        while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index -1)/2])){
            swap(index, (index-1)/2);
            index = (index - 1)/2;
        }
    }

    public void heapify(int index, int size){
        int left = 2*index + 1;
        while (left < size){
            int min = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
            min = distanceMap.get(nodes[min]) < distanceMap.get(nodes[index]) ? min : index;
            if (min == index)return;
            swap(min, index);
            index = min;
            index = 2*index + 1;
        }
    }

    public boolean isEntered(Node node){
        return indexMap.containsKey(node);
    }

    public boolean inHeap(Node node){
        return isEntered(node) && indexMap.get(node) != -1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void swap(int index1, int index2){
        indexMap.put(nodes[index2], index1);
        indexMap.put(nodes[index1], index2);
        Node temp = nodes[index1];
        nodes[index1] = nodes[index2];
        nodes[index2] = temp;
    }
}
