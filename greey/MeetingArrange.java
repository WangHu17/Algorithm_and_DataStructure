package greey;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wanghu
 * @discrption： 会议安排
 * @create 2022-06-12 14:36
 */
public class MeetingArrange {

    class Meet {
        int start;
        int end;
        public Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class MyComparator implements Comparator<Meet> {
        @Override
        public int compare(Meet o1, Meet o2) {
            return o1.end - o2.end;
        }
    }

    public int meetingArrange(Meet[] meets){
        if (meets == null || meets.length == 0)return 0;
        Arrays.sort(meets, new MyComparator());
        int timeLine = 0, res = 0;
        for (int i=0; i<meets.length; i++){
            if (timeLine <= meets[i].start){
                res++;
                timeLine = meets[i].end;
            }
        }
        return res;
    }

}
