package stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-06 10:43
 */
public class TwoQueueToStack {

    public static class MyStack{
        private Queue<Integer> queue;
        private Queue<Integer> help;
        public MyStack(){
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }
        public void push(int n){
            queue.add(n);
        }
        public int pop(){
            while (queue.size() > 1){
                help.add(queue.poll());
            }
            int res = queue.poll();
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }
        public int peek(){
            while (queue.size() > 1){
                help.add(queue.poll());
            }
            int res = queue.poll();
            help.add(res);
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

}
