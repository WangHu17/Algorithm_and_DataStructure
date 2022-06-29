package classicrecursion;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-15 15:35
 */
public class Permutation2 {

    public ArrayList<String> permutation(String s){
        if (s == null || s.length() == 0)return null;
        ArrayList<String> res = new ArrayList<>();
        char[] str = s.toCharArray();
        process(str, 0, res);
        return res;
    }

    public void process(char[] str, int index, ArrayList<String> res){
        if (index == str.length){
            res.add(String.valueOf(str));
            return;
        }
        boolean[] isVisited = new boolean[256];
        for (int i=index; i<str.length; i++){
            if (!isVisited[str[i]]){
                isVisited[str[i]] = true;
                swap(str, index, i);
                process(str, index + 1, res);
                swap(str, index, i);
            }
        }
    }

    public void swap(char[] str, int i, int j){
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

}
