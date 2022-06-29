package greey;

/**
 * @author wanghu
 * @discrption：
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
