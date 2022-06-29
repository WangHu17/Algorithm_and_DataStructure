package tree.serialize;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 先序序列化
 * @create 2022-06-09 14:19
 */
public class PreSerial {

    public static Queue<String> preSerial(Node root){
        Queue<String> res = new LinkedList<>();
        pres(root, res);
        return res;
    }

    public static void pres(Node node, Queue<String> res){
        if (node == null){
            res.add(null);
        }else {
            res.add(String.valueOf(node.value));
            pres(node.left, res);
            pres(node.right, res);
        }
    }

}
