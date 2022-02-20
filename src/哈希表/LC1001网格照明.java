package 哈希表;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-08 16:59
 */
public class LC1001网格照明 {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[][] dirs = new int[][]{{0, 0}, {0, 1}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {1, 0}, {1, 1}};
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int[] lamp : lamps) {
            // 注意重复的灯无需操作，只点亮一次
            if(set.contains(lamp[0] + " " + lamp[1])) continue;
            set.add(lamp[0] + " " + lamp[1]);
            rowMap.put(lamp[0], rowMap.getOrDefault(lamp[0], 0) + 1);
            colMap.put(lamp[1], colMap.getOrDefault(lamp[1], 0) + 1);
            leftMap.put(lamp[0] + lamp[1], leftMap.getOrDefault(lamp[0] + lamp[1], 0) + 1);
            rightMap.put(lamp[0] - lamp[1], rightMap.getOrDefault(lamp[0] - lamp[1], 0) + 1);
        }
        int[] res = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1];
            if (rowMap.containsKey(x) && rowMap.get(x) > 0 || colMap.containsKey(y) && colMap.get(y) > 0 || leftMap.containsKey(x + y) && leftMap.get(x + y) > 0 || rightMap.containsKey(x - y) && rightMap.get(x - y) > 0)
                res[index] = 1;
            index++;
            for (int[] dir : dirs) {
                int nx = query[0] + dir[0], ny = query[1] + dir[1];
                boolean isRemove = set.remove(nx + " " + ny);
                if (isRemove) {
                    rowMap.put(nx, rowMap.get(nx) - 1);
                    colMap.put(ny, colMap.get(ny) - 1);
                    leftMap.put(nx + ny, leftMap.get(nx + ny) - 1);
                    rightMap.put(nx - ny, rightMap.get(nx - ny) - 1);
                }
            }
        }
        return res;
    }
}
