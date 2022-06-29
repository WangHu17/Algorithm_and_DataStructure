package unionfind;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption： 岛屿数量2
 * 给定3个参数，m，n是代表陆地和海洋的二维数组的行和列，positions是装着陆地位置的二维数组。
 * 依次根据positions中的位置添加陆地，每添加一块陆地，求出岛屿的数量，最后返回装有岛屿数量的list集合。
 * @create 2022-06-13 15:06
 */
public class Island2 {

    public ArrayList<Integer> numIsland(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m, n);
        ArrayList<Integer> res = new ArrayList<>();
        for (int[] position:positions){
            res.add(unionFind.connect(position[0], position[1]));
        }
        return res;
    }

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int row;
        private int col;
        private int sets;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        public int index(int i, int j) {
            return i * col + j;
        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j, int m, int n) {
            if (i < 0 || i == row || j < 0 || j == col || m < 0 || m == row || n < 0 || n == col) return;
            int i1 = index(i, j);
            int i2 = index(m,n);
            if (size[i1] == 0 || size[i2] == 0)return;
            int h1 = find(i1);
            int h2 = find(i2);
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

        public int connect(int i, int j) {
            int index = index(i, j);
            if (size[index] == 0){
                size[index] = 1;
                parent[index] = index;
                sets++;
                union(i-1,j,i,j);
                union(i,j-1,i,j);
                union(i+1,j,i,j);
                union(i,j+1,i,j);
            }
            return sets;
        }
    }

}
