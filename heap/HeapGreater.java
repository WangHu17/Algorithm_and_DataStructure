package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author wanghu
 * @discrption： 加强堆的的实现
 * @create 2022-06-07 14:58
 */
public class HeapGreater<T> {

    private ArrayList<T> heap;
    private int heapSize;
    private HashMap<T, Integer> indexMap;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        comp = c;
    }

    public void add(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T res = heap.get(0);
        indexMap.remove(res);
        swap(0, --heapSize);
        heap.remove(heapSize);
        heapify(0);
        return res;
    }

    public T peek() {
        return heap.get(0);
    }

    public void remove(T obj) {
        int index = indexMap.get(obj);
        T replace = heap.get(heapSize - 1);
        heap.remove(--heapSize);
        if (obj != replace){
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public void resign(T obj) {
        int index = indexMap.get(obj);
        heapInsert(index);
        heapify(index);
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = 2 * index + 1;
        while (left < heapSize){
            int max = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            max = comp.compare(heap.get(index), heap.get(max)) < 0 ? index : max;
            if (max == index)break;
            swap(max, index);
            index = max;
            left = 2 * index + 1;
        }
    }

    public boolean contain(T obj) {
        return indexMap.containsKey(obj);
    }

    public List<T> getAllElement() {
        List<T> res = new ArrayList<>();
        for (T obj : heap) res.add(obj);
        return res;
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void swap(int i, int j) {
        T a = heap.get(i);
        T b = heap.get(j);
        indexMap.put(a, j);
        indexMap.put(b, i);
        heap.set(i, b);
        heap.set(j, a);
    }

}
