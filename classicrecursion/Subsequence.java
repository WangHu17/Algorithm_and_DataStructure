package classicrecursion;

import java.util.ArrayList;

/**
 * @author wanghu
 * @discrption： 打印字符串的所有子序列
 * @create 2022-06-15 15:01
 */
public class Subsequence {

    public ArrayList<String> subsequence(String s){
        char[] str = s.toCharArray();
        ArrayList<String> res = new ArrayList<>();
        String path = "";
        process(str, 0, res, path);
        return res;
    }

    public void process(char[] str, int index, ArrayList<String> res, String path){
        if (index == str.length){
            res.add(path);
            return;
        }
        process(str, index + 1, res, path);
        process(str, index + 1, res, path + String.valueOf(str[index]));
    }

}
