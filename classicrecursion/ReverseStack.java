package classicrecursion;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 * @create 2022-06-15 15:45
 */
public class ReverseStack {

    public void reverse(Stack<Integer> stack){
        if (stack.isEmpty())return;
        int last = f(stack);
        reverse(stack);
        stack.push(last);
    }

    public Integer f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}
