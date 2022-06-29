package tree.serialize;

import tree.Node;

import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 先序反序列化
 * @create 2022-06-09 14:32
 */
public class BuildByPreSerial {

    public static Node buildByPreSerial(Queue<String> queue) {
        if (queue == null || queue.size() == 0)
            return null;
        return preb(queue);
    }

    public static Node preb(Queue<String> queue){
        String val = queue.poll();
        if (val == null)return null;
        Node head = new Node(Integer.parseInt(val));
        head.left = preb(queue);
        head.right = preb(queue);
        return head;
    }

}
