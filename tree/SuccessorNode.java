package tree;

/**
 * @author wanghu
 * @discrption： 后继节点，找到某个节点在二叉树的中序序列中，它后面的那个节点。
 * @create 2022-06-10 10:19
 */
public class SuccessorNode {

    private class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node getSuccessorNode(Node node){
        if (node == null)return null;
        if (node.right != null){
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }else {
            Node parent = node.parent;
            while (parent != null && parent.right == node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

}
