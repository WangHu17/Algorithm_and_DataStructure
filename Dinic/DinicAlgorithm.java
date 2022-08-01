package Dinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author wanghu
 * @discrption： 最大网络流算法--dinic算法
 * @create 2022-07-29 10:47
 */
public class DinicAlgorithm {

    public static class Edge {
        public int from;
        public int to;
        public int available;

        public Edge(int from, int to, int available) {
            this.from = from;
            this.to = to;
            this.available = available;
        }
    }

    public static class Dinic {
        private int N;
        private ArrayList<ArrayList<Integer>> nexts;
        private ArrayList<Edge> edges;
        private int[] depth;
        private int[] cur;

        public Dinic(int num) {
            N = num + 1;
            nexts = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                nexts.add(new ArrayList<>());
            }
            edges = new ArrayList<>();
            depth = new int[N];
            cur = new int[N];
        }

        public void addEdge(int u, int v, int r) {
            int m = edges.size();
            edges.add(new Edge(u, v, r));
            nexts.get(u).add(m);
            edges.add(new Edge(v, u, 0));
            nexts.get(v).add(m + 1);
        }

        public int maxFlow(int s, int t) {
            int flow = 0;
            while (bfs(s, t)) {
                Arrays.fill(cur, 0);
                flow += dfs(s, t, Integer.MAX_VALUE);
                Arrays.fill(depth, 0);
            }
            return flow;
        }

        private boolean bfs(int s, int t) {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addFirst(s);
            boolean[] visited = new boolean[N];
            visited[s] = true;
            while (!queue.isEmpty()) {
                Integer u = queue.pollLast();
                for (int i = 0; i < nexts.get(u).size(); i++) {
                    Edge e = edges.get(nexts.get(u).get(i));
                    int v = e.to;
                    if (!visited[v] && e.available > 0) {
                        visited[v] = true;
                        depth[v] = depth[u] + 1;
                        if (v == t) break;
                        queue.addFirst(v);
                    }
                }
            }
            return visited[t];
        }

        private int dfs(int s, int t, int r) {
            if (s == t || r == 0) {
                return r;
            }
            int f = 0;
            int flow = 0;
            for (; cur[s] < nexts.get(s).size(); cur[s]++) {
                Integer ei = nexts.get(s).get(cur[s]);
                Edge e = edges.get(ei);
                Edge o = edges.get(ei ^ 1);
                if ((depth[e.to] == depth[s] + 1) && (f = dfs(e.to, t, Math.min(e.available, r))) != 0) {
                    e.available -= f;
                    o.available += f;
                    flow += f;
                    r -= f;
                    if (r <= 0){
                        break;
                    }
                }
            }
            return flow;
        }
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int cases = cin.nextInt();
        for (int i = 1; i <= cases; i++) {
            int n = cin.nextInt();
            int s = cin.nextInt();
            int t = cin.nextInt();
            int m = cin.nextInt();
            Dinic dinic = new Dinic(n);
            for (int j = 0; j < m; j++) {
                int from = cin.nextInt();
                int to = cin.nextInt();
                int weight = cin.nextInt();
                dinic.addEdge(from, to, weight);
                dinic.addEdge(to, from, weight);
            }
            int ans = dinic.maxFlow(s, t);
            System.out.println("Case " + i + ": " + ans);
        }
        cin.close();
    }

}
