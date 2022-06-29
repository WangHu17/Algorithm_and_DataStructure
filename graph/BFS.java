package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-14 10:11
 */
public class BFS {

    public void bfs(Node start) {
        if (start == null)return;
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.val);
            for (Node next:node.nexts){
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

}
