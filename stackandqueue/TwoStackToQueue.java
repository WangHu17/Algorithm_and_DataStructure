package stackandqueue;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 两个栈实现队列
 * @create 2022-06-06 10:03
 */
public class TwoStackToQueue {

    public static class MyQueue{
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;
        public MyQueue(){
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }
        public void pushToPop(){
            if (stackPop.isEmpty()){
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public void add(int n){
            stackPush.push(n);
            pushToPop();
        }
        public int poll(){
            if (stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("队列已空");
            }
            pushToPop();
            return stackPop.pop();
        }
        public int peek(){
            if (stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("队列已空");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

}
