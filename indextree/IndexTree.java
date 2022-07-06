package indextree;

/**
 * @author wanghu
 * @discrption： 一维 indextree
 * @create 2022-07-06 11:23
 */
public class IndexTree {

    private int[] tree;
    private int N;

    public IndexTree(int n) {
        N = n;
        tree = new int[N+1];
    }

    public int sum(int index){
        int res = 0;
        while (index > 0){
            res += tree[index];
            index -= index & -index;
        }
        return res;
    }

    public void add(int index, int C){
        while (index <= N){
            tree[index] += C;
            index += index & -index;
        }
    }
}
