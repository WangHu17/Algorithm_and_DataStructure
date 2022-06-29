package greey;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wanghu
 * @discrption： 最小字典序
 * @create 2022-06-12 13:43
 */
public class LowestString {

    public String lowestString(String[] strs){
        if (strs == null || strs.length ==0)return null;
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (String str:strs)
            res += str;
        return res;
    }

    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

}
