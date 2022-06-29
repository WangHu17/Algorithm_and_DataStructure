package stackandqueue;

/**
 * @author wanghu
 * @discrption： 数组实现队列
 * @create 2022-06-06 9:45
 */
public class ArrayToQueue {

    public static class MyQueue {

        private int[] arr;
        private int size;
        private int pushi;
        private int polli;
        private int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            this.limit = limit;
        }

        public void push(int n) {
            if (size == limit) {
                throw new RuntimeException("队列已满");
            }
            size++;
            arr[pushi] = n;
            pushi = nextIndex(pushi);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列已空");
            }
            size--;
            int res = arr[polli];
            polli = nextIndex(polli);
            return res;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

}
