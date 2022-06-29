package trietree;

/**
 * @author wanghu
 * @discrption： 前缀树-数组实现（只能存小写字母）
 * @create 2022-06-08 9:20
 */
public class TrieTree1 {

    private Node root;

    public TrieTree1 () {
        root = new Node();
    }

    public void insert(String word){
        if (word == null)return;
        char[] str = word.toCharArray();
        Node node = root;
        node.pass++;
        int path= 0;
        for (int i=0; i<str.length; i++){
            path = str[i] - 'a';
            if (node.nexts[path] == null){
                node.nexts[path] = new Node();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    public void delete(String word){
        if (search(word) == 0)return;
        char[] str = word.toCharArray();
        Node node = root;
        node.pass--;
        int path = 0;
        for (int i=0; i<str.length; i++){
            path = str[i] - 'a';
            if (--node.nexts[path].pass == 0){
                node.nexts[path] = null;
                return;
            }
            node = node.nexts[path];
        }
        node.end--;
    }

    public int search(String word){
        if (word == null)return 0;
        char[] str = word.toCharArray();
        Node node = root;
        int path = 0;
        for (int i=0; i<str.length; i++){
            path = str[i] - 'a';
            if (node.nexts[path] == null)
                return 0;
            node = node.nexts[path];
        }
        return node.end;
    }

    public int prefixNum(String s){
        if (s == null)return 0;
        char[] str = s.toCharArray();
        Node node = root;
        int path = 0;
        for (int i=0; i<str.length; i++){
            path = str[i] - 'a';
            if (node.nexts[path] == null)
                return 0;
            node = node.nexts[path];
        }
        return node.pass;
    }

    private class Node {
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            this.nexts = new Node[26];
        }
    }

}
