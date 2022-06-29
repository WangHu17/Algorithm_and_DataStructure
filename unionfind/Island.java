package unionfind;

/**
 * @author wanghu
 * @discrption： 岛屿数量
 * 一个二维数组，由字符 '0' 和 '1' 组成，'1'代表陆地，上下左右为相连，相连的为一个岛屿，求共有多少个岛屿。
 * @create 2022-06-13 11:11
 */
public class Island {

    // 方法二：并查集
    public int numIsland2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind unionFind = new UnionFind(board);
        for (int i = 1; i < col; i++) {
            if (board[0][i - 1] == '1' && board[0][i] == '1')
                unionFind.union(0, i - 1, 0, i);
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1')
                unionFind.union(i - 1, 0, i, 0);
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i - 1][j] == '1')
                        unionFind.union(i - 1, j, i, j);
                    if (board[i][j - 1] == '1')
                        unionFind.union(i, j - 1, i, j);
                }
            }
        }
        return unionFind.sets;
    }

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public UnionFind(char[][] board) {
            int row = board.length;
            col = board[0].length;
            int n = row * col;
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == '1') {
                        int index = index(i, j);
                        parent[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
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
            int index1 = index(i, j);
            int index2 = index(m, n);
            int i1 = find(index1);
            int i2 = find(index2);
            if (i1 != i2) {
                if (size[i1] >= size[i2]) {
                    parent[i2] = i1;
                    size[i1] += size[i2];
                } else {
                    parent[i1] = i2;
                    size[i2] += size[i1];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

    // 方法一：dfs
    public int numIsland1(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    res++;
                    dfs(board, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') return;
        board[i][j] = '0';
        dfs(board, i + 1, j);
        dfs(board, i, j + 1);
        dfs(board, i - 1, j);
        dfs(board, i, j - 1);
    }

}
