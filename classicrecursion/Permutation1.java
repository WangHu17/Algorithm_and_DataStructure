package classicrecursion;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption： 字符串全排列
 * @create 2022-06-15 15:23
 */
public class Permutation1 {

    public ArrayList<String> permutation(String s){
        if (s == null || s.length() ==0)return null;
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Character> rest = new ArrayList<>();
        for (char c:s.toCharArray())
            rest.add(c);
        String path = "";
        process(rest, path, res);
        return res;
    }

    public void process(ArrayList<Character> rest, String path, ArrayList<String> res){
        if (rest.size() == 0){
            res.add(path);
            return;
        }
        int N = rest.size();
        for (int i=0; i < N; i++){
            Character c = rest.get(i);
            rest.remove(i);
            process(rest, path + c , res);
            rest.add(i, c);
        }
    }

}
