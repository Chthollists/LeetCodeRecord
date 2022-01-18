package 排序.桶排序;

import java.util.Collections;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-01-18 13:15
 */
public class LC539最小时间差 {

    // 1. 对时间字符串排序
    public int findMinDifference1(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) return 0;
        int n = timePoints.size();
        Collections.sort(timePoints);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, getMinute(timePoints.get(i)) - getMinute(timePoints.get(i - 1)));
        }
        // 首尾元素
        int firstTime = getMinute(timePoints.get(0));
        int lastTime = getMinute(timePoints.get(n - 1));
        minDiff = Math.min(minDiff, firstTime - lastTime + 1440);
        return minDiff;
    }

    // 2. 桶排序 & 哈希计数
    public int findMinDifference2(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) return 0;
        int n = timePoints.size();
        if (n > 1440) return 0;
        int[] bucket = new int[1440];
        for (String timePoint : timePoints) {
            bucket[getMinute(timePoint)]++;
        }
        int minDiff = Integer.MAX_VALUE;
        int first = 0, last = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 1) return 0;
            if (bucket[i] == 0) continue;
            if (last != -1) {
                minDiff = Math.min(minDiff, i - last);
            } else {
                first = i; // 首个bucket中只有一个元素
            }
            last = i; // 当前bucket中只有一个元素，遍历结束时就是最后一个只有一个元素的bucket
        }
        // 首尾元素
        minDiff = Math.min(minDiff, first - last + 1440);
        return minDiff;
    }


    // 方法二优化：在哈希计数时剪枝
    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) return 0;
        int n = timePoints.size();
        if (n > 1440) return 0;
        int[] bucket = new int[1440];
        for (String timePoint : timePoints) {
            if (bucket[getMinute(timePoint)] >= 1) return 0;
            bucket[getMinute(timePoint)]++;
        }
        int minDiff = Integer.MAX_VALUE;
        int first = 0, last = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 1) return 0;
            if (bucket[i] == 0) continue;
            if (last != -1) {
                minDiff = Math.min(minDiff, i - last);
            } else {
                first = i; // 首个bucket中只有一个元素
            }
            last = i; // 当前bucket中只有一个元素，遍历结束时就是最后一个只有一个元素的bucket
        }
        // 首尾元素
        minDiff = Math.min(minDiff, first - last + 1440);
        return minDiff;
    }

    private int getMinute(String timeString) {
        String[] time = timeString.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
