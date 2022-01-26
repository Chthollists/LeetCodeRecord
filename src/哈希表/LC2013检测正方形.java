package 哈希表;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Chthollist email:
 * @create 2022-01-26 18:43
 */
public class LC2013检测正方形 {
    Map<Integer, Map<Integer, Integer>> pointMap;

    public LC2013检测正方形() {
        pointMap = new HashMap<Integer, Map<Integer, Integer>>();
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        Map<Integer, Integer> map = pointMap.getOrDefault(y, new HashMap<>());
        map.put(x, map.getOrDefault(x, 0) + 1);
        pointMap.put(y, map);
    }

    public int count(int[] point) {
        int count = 0;
        int edge = 0;
        int x = point[0], y = point[1];
        if (!pointMap.containsKey(y)) return 0;
        Map<Integer, Integer> yMap = pointMap.get(y);
        Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = pointMap.entrySet();
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : entries) {
            int col = entry.getKey();
            Map<Integer, Integer> map = entry.getValue();
            if (col != y) {
                edge = col - y;
                count += map.getOrDefault(x, 0) * yMap.getOrDefault(x + edge, 0) * map.getOrDefault(x + edge, 0);
                count += map.getOrDefault(x, 0) * yMap.getOrDefault(x - edge, 0) * map.getOrDefault(x - edge, 0);
            }
        }
        return count;
    }
}


class DetectSquares {

}
