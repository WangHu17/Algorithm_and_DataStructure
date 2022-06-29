package trietree;

import java.util.HashMap;

/**
 * @author wanghu
 * @discrption： 前缀树-哈希表实现（可以存任何字符）
 * @create 2022-06-08 10:07
 */
public class TrieTree2 {

    private Node root;

    public TrieTree2() {
        root = new Node();
    }

    public void insert(String word){
        if (word == null)return;
        char[] str = word.toCharArray();
        Node node = root;
        node.pass++;
        for (int i=0; i<str.length; i++){
            Character c = str[i];
            if (!node.nexts.containsKey(c)){
                node.nexts.put(c, new Node());
            }
            node = node.nexts.get(c);
            node.pass++;
        }
        node.end++;
    }

    public void delete(String word){
        if (search(word) == 0)return;
        char[] str = word.toCharArray();
        Node node = root;
        node.pass--;
        for (int i=0; i<str.length; i++){
            Character c = str[i];
            if (--node.nexts.get(c).pass == 0){
                node.nexts.remove(c);
                return;
            }
            node = node.nexts.get(c);
        }
        node.end--;
    }

    public int search(String word){
        if (word == null)return 0;
        char[] str = word.toCharArray();
        Node node = root;
        for (int i=0; i<str.length; i++) {
            Character c = str[i];
            if (!node.nexts.containsKey(c))
                return 0;
            node = node.nexts.get(c);
        }
        return node.end;
    }

    public int prefixNum(String word){
        if (word == null)return 0;
        char[] str = word.toCharArray();
        Node node = root;
        for (int i=0; i<str.length; i++) {
            Character c = str[i];
            if (!node.nexts.containsKey(c))
                return 0;
            node = node.nexts.get(c);
        }
        return node.pass;
    }

    private class Node {
        public int pass;
        public int end;
        public HashMap<Character, Node> nexts;

        public Node() {
            nexts = new HashMap<>();
        }
    }

}