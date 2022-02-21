package 双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-02-21 17:01
 */
public class LC56合并区间 {

    // 1.排序 + 双指针
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return new int[0][2];
        int n = intervals.length;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n;) {
            int begin = intervals[i][0], end = intervals[i][1];
            int j = i + 1;
            while(j < n && intervals[j][0] <= end) {
                end = Math.max(end, intervals[j][1]);
                j++;
            }
            list.add(new int[]{begin, end});
            i = j;
        }
        return list.toArray(new int[list.size()][]);
    }


}
