package unionfind;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-13 10:08
 */
public class FriendCircle {

    public int friendCircle(int[][] M) {
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j=i+1; j<N; j++){
                if (M[i][j] == 1)
                    unionFind.union(i, j);
            }
        }
        return unionFind.sets();
    }

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i){
            int hi = 0;
            while (i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            for (; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j){
            int h1 = find(i);
            int h2 = find(j);
            if (h1 != h2){
                if (size[h1] >= size[h2]){
                    parent[h2] = h1;
                    size[h1] += size[h2];
                }else {
                    parent[h1] = h2;
                    size[h2] += size[h1];
                }
                sets--;
            }
        }

        public int sets(){
            return sets;
        }
    }

}
