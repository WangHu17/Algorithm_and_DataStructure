package heap;

import quicksortproblems.Swap;

/**
 * @author wanghu
 * @discrption： 大根堆
 * @create 2022-06-07 9:21
 */
public class MyHeap {

    private int[] heap;
    private final int limit;
    private int heapSize;

    public MyHeap(int limit) {
        this.limit = limit;
        heap = new int[limit];
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1)/2]) {
            Swap.swap(arr, index, (index - 1)/2);
            index = (index - 1)/2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize){
        int left = 2 * index + 1;
        while (left < heapSize){
            int maxIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            maxIndex = arr[maxIndex] > arr[index] ? maxIndex : index;
            if (maxIndex == index)break;
            Swap.swap(arr, index, maxIndex);
            index = maxIndex;
            left = 2 * index + 1;
        }
    }

    public void push(int val){
        if (heapSize == limit)
            throw new RuntimeException("堆已满");
        heap[heapSize] = val;
        heapInsert(heap, heapSize++);
    }

    public int pop(){
        if (heapSize == 0)
            throw new RuntimeException("堆已空");
        int res = heap[0];
        Swap.swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return res;
    }

}
