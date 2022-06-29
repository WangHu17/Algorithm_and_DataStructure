package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wanghu
 * @discrption： 二叉树的宽度
 * @create 2022-06-10 9:40
 */
public class MaxWidth {

    public int maxWidth1(Node root){
        if (root == null)return 0;
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = root;
        Node nextEnd = null;
        queue.add(root);
        int max = 0, curWidth = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.left != null){
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null){
                queue.add(node.right);
                nextEnd = node.right;
            }
            curWidth++;
            if (node == curEnd){
                max = Math.max(max, curWidth);
                curWidth = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public int maxWidth2(Node root){
        if (root == null)return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int size = 0, max = 0;
        while (!queue.isEmpty()){
            size = queue.size();
            max = Math.max(max, size);
            while (size-- > 0){
                Node node = queue.poll();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return max;
    }

    @Test
    public void test(){
        /*
                1
              2   3
             4   5  6
            7
        */
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        System.out.println(maxWidth1(root));
        System.out.println(maxWidth2(root));
    }

}
