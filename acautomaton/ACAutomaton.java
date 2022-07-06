package acautomaton;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wanghu
 * @discrption： AC 自动机
 * @create 2022-07-06 14:41
 */
public class ACAutomaton {

    class Node {
        private String end;
        private boolean endUse;
        private Node fail;
        private Node[] nexts;

        public Node() {
            end = null;
            endUse = false;
            fail = null;
            nexts = new Node[26];
        }
    }

    private Node root;

    public ACAutomaton() {
        root = new Node();
    }

    public void insert(String s) {
        char[] str = s.toCharArray();
        Node cur = root;
        for (int i = 0; i < str.length; i++) {
            int index = str[i] - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new Node();
            }
            cur = cur.nexts[index];
        }
        cur.end = s;
    }

    public void build() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        Node cur = null;
        Node cfail = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int i = 0; i < 26; i++) {
                if (cur.nexts[i] != null) {
                    cur.nexts[i].fail = root;
                    cfail = cur.fail;
                    while (cfail != null) {
                        if (cfail.nexts[i] != null) {
                            cur.nexts[i].fail = cfail.nexts[i];
                            break;
                        }
                        cfail = cfail.fail;
                    }
                    queue.add(cur.nexts[i]);
                }
            }
        }
    }

    public ArrayList<String> containWords(String content) {
        ArrayList<String> res = new ArrayList<>();
        Node cur = root;
        Node follow = null;
        int index = 0;
        char[] str = content.toCharArray();
        for (int i = 0; i < str.length; i++) {
            index = str[i] - 'a';
            while (cur.nexts[index] == null && cur != root) {
                cur = cur.fail;
            }
            cur = cur.nexts[index] != null ? cur.nexts[index] : root;
            follow = cur;
            while (follow != root) {
                if (follow.endUse) break;
                if (follow.end != null) {
                    res.add(follow.end);
                    follow.endUse = true;
                }
                follow = follow.fail;
            }
        }
        return res;
    }

    @Test
    public void test() {
        ACAutomaton ac = new ACAutomaton();
        // 建立前缀树
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
