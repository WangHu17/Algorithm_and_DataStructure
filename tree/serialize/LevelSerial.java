package tree.serialize;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 层次序列化
 * @create 2022-06-09 15:04
 */
public class LevelSerial {

    public static Queue<String> levelSerial(Node root){
        Queue<String> res = new LinkedList<>();
        if (root == null){
            res.add(null);
        }else {
            Queue<Node> queue = new LinkedList<>();
            res.add(String.valueOf(root.value));
            queue.add(root);
            while (!queue.isEmpty()){
                Node node = queue.poll();
                if (node.left != null){
                    res.add(String.valueOf(node.left.value));
                    queue.add(node.left);
                }else {
                    res.add(null);
                }
                if (node.right != null){
                    res.add(String.valueOf(node.right.value));
                    queue.add(node.right);
                }else {
                    res.add(null);
                }
            }
        }
        return res;
    }

}
