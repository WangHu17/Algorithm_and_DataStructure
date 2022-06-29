package recursiontodp;


import java.util.HashMap;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-17 10:02
 */
public class Stickers {

    // 暴力递归
    public int sticker1(String target, String[] stickers){
        int res = process1(target, stickers);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int process1(String target, String[] stickers){
        if (target.length() == 0)
            return 0;
         int min = Integer.MAX_VALUE;
         for (String sticker:stickers){
             String rest = minus(target, sticker);
             if (rest.length() != target.length()){
                 min = Math.min(min, process1(rest, stickers));
             }
         }
         return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public String minus(String target, String sticker){
        char[] targets = target.toCharArray();
        char[] stickers = sticker.toCharArray();
        int[] count = new int[26];
        for (char c:targets){
            count[c-'a']++;
        }
        for (char c:stickers){
            count[c-'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<26; i++){
            if (count[i] > 0){
                for (int j=0; j<count[i]; j++){
                    builder.append((char)(i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    // 词频表优化，加缓存表
    public int sticker2(String target, String[] stickers){
        int N = stickers.length;
        int[][] stickerCount = new int[N][26];
        for (int i=0; i<N; i++){
            char[] sticker = stickers[i].toCharArray();
            for (char c:sticker){
                stickerCount[i][c-'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int res = process2(target, stickerCount, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int process2(String target, int[][] stickers, HashMap<String, Integer> dp){
        if (dp.containsKey(target))
            return dp.get(target);
        int[] targetCount = new int[26];
        char[] targets = target.toCharArray();
        for (char c:targets)
            targetCount[c-'a']++;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<stickers.length; i++){
            if (stickers[i][targets[0] - 'a'] > 0){
                StringBuilder builder = new StringBuilder();
                for (int j=0; j<26; j++){
                    if (targetCount[j] > 0){
                        int num = targetCount[j] - stickers[i][j];
                        for (int k=0; k<num; k++)
                            builder.append((char)(j + 'a'));
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(rest, stickers, dp));
            }
        }
        int res = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, res);
        return res;
    }

}
