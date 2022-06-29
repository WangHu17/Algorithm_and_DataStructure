package tree.serialize;

import tree.Node;

import java.util.Queue;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 后序反序列化
 * @create 2022-06-09 14:59
 */
public class BuildByPostSerial {

    public static Node buildByPostSerial(Queue<String> queue){
        if (queue == null || queue.size() == 0)
            return null;
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()){
            stack.push(queue.poll());
        }
        return postb(stack);
    }

    public static Node postb(Stack<String> stack){
        String val = stack.pop();
        if (val == null)return null;
        Node head = new Node(Integer.parseInt(val));
        head.right = postb(stack);
        head.left = postb(stack);
        return head;
    }

}
