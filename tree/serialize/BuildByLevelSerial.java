package tree.serialize;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 层次反序列化
 * @create 2022-06-09 15:13
 */
public class BuildByLevelSerial {

    public static Node buildByLevelSerial(Queue<String> queue){
        if (queue == null || queue.size() == 0)
            return null;
        Node root = generateNode(queue.poll());
        Queue<Node> queue1 = new LinkedList<>();
        if (root != null){
            queue1.add(root);
        }
        while (!queue1.isEmpty()){
            Node node = queue1.poll();
            node.left = generateNode(queue.poll());
            node.right = generateNode(queue.poll());
            if (node.left != null)
                queue1.add(node.left);
            if (node.right != null)
                queue1.add(node.right);
        }
        return root;
    }

    public static Node generateNode(String val){
        if (val == null)
            return null;
        return new Node(Integer.parseInt(val));
    }

}
