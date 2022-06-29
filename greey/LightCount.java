package greey;

/**
 * @author wanghu
 * @discrption： 放灯问题
 * 给定一个字符串str，只由 'X' 和 '.' 两种字符构成。
 * 'X' 表示墙，不能放灯，也不需要点亮。
 * '.' 表示居民点，可以放灯，需要点亮。如果灯放在i位置,可以让i-1，i 和 i+1三个位置被点亮。
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯。
 * @create 2022-06-12 16:23
 */
public class LightCount {

    // X.X..X...X.....X
    public int lightCount(String road){
        int res = 0, i = 0;
        char[] roads = road.toCharArray();
        while (i < roads.length){
            if (roads[i] == 'X'){
                i++;
            }else {
                res++;
                if (i + 1 == roads.length)
                    break;
                else {
                    if (roads[i+1] == 'X'){
                        i += 2;
                    }else
                        i += 3;
                }
            }
        }
        return res;
    }

}
