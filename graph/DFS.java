package graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-14 10:17
 */
public class DFS {

    public void dfs(Node start){
        if (start == null)return;
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.val);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            for (Node next:node.nexts){
                if (!set.contains(next)){
                    stack.add(node);
                    stack.add(next);
                    set.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }

}
