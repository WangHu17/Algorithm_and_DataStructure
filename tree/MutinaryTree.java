package tree;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption： 多叉树转二叉树，再转回去
 * @create 2022-06-09 15:55
 */
public class MutinaryTree {

    class MNode {
        int val;
        ArrayList<MNode> children;
        public MNode(int val) {
            this.val = val;
        }
        public MNode(int val, ArrayList<MNode> children) {
            this.val = val;
            this.children = children;
        }
    }

    public Node encode(MNode mRoot){
        if (mRoot == null)return null;
        Node root = new Node(mRoot.val);
        root.left = en(mRoot.children);
        return root;
    }

    public Node en(ArrayList<MNode> children){
        Node root = null;
        Node cur = null;
        for (MNode child:children){
            Node node = new Node(child.val);
            if (root == null){
                root = node;
            }else {
                cur.right = node;
            }
            cur = node;
            cur.left = en(child.children);
        }
        return root;
    }

    public MNode decode(Node root){
        if (root == null)return null;
        return new MNode(root.value, de(root.left));
    }

    public ArrayList<MNode> de(Node root){
        ArrayList<MNode> children = new ArrayList<>();
        while (root != null){
            MNode node = new MNode(root.value, de(root.left));
            children.add(node);
            root = root.right;
        }
        return children;
    }

}
