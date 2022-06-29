package tree.serialize;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 后序序列化
 * @create 2022-06-09 14:55
 */
public class PostSerial {

    public static Queue<String> postSerial(Node root){
        Queue<String> res = new LinkedList<>();
        posts(root, res);
        return res;
    }

    public static void posts(Node node, Queue<String> res){
        if (node == null){
            res.add(null);
        }else {
            posts(node.left, res);
            posts(node.right, res);
            res.add(String.valueOf(node.value));
        }
    }

}
