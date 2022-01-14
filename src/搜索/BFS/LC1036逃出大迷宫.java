package 搜索.BFS;

import java.util.*;

/**
 * @author Chthollists email:
 * @create 2022-01-11 20:36
 * {
 * [[691938,300406],[710196,624190],[858790,609485],[268029,225806],[200010,188664],
 * [132599,612099],[329444,633495],[196657,757958],[628509,883388]]
 * }
 * [655988,180910]
 * [267728,840949]
 */
public class LC1036逃出大迷宫 {

    final int GRID_EDGE = (int) 1e6;
    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (source == null || source.length == 0) return false;
        if (target == null || target.length == 0) return false;
        if (blocked == null || blocked.length == 0) return true;
        int n = blocked.length;
        final int AREA = n * (n - 1) / 2;
        Set<Long> blockSet = new HashSet<>();
        for (int[] grid : blocked) {
            long hash = (long) grid[0] * GRID_EDGE + grid[1];
            blockSet.add(hash);
        }
        return bfs(source, target, blockSet, AREA) && bfs(target, source, blockSet, AREA);
    }

    private boolean bfs(int[] source, int[] target, Set<Long> blockSet, int AREA) {
        Set<Long> visited = new HashSet<>();
        Deque<int[]> deque = new LinkedList<>();
        long hash = (long) source[0] * GRID_EDGE + source[1];
        deque.addLast(source);
        visited.add(hash);
        while (!deque.isEmpty()) {
            // 可达网格个数大于包围圈面积，连通
            if (visited.size() > AREA) return true;
            int[] cur = deque.poll();
            int x = cur[0];
            int y = cur[1];
            //  经过了起点、终点，连通
            if (x == target[0] && y == target[1]) return true;
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= GRID_EDGE || ny < 0 || ny >= GRID_EDGE) continue;
                hash = (long) nx * GRID_EDGE + ny;
                if (blockSet.contains(hash) || visited.contains(hash)) continue;
                deque.addLast(new int[]{nx, ny});
                visited.add(hash);
            }
        }
        return visited.size() > AREA;
    }


    public boolean isEscapePossible1(int[][] blocked, int[] source, int[] target) {
        if (source == null || source.length == 0) return false;
        if (target == null || target.length == 0) return false;
        if (blocked == null || blocked.length == 0) return true;
        int n = blocked.length;
        final int AREA = n * (n - 1) / 2;
        Set<List<Integer>> blockSet = new HashSet<>();
        for (int[] grid : blocked) {
            List<Integer> list = new ArrayList<>(2);
            list.add(grid[0]);
            list.add(grid[1]);
            blockSet.add(list);
        }
        return bfs1(source, target, blockSet, AREA) && bfs1(target, source, blockSet, AREA);
    }

    private boolean bfs1(int[] source, int[] target, Set<List<Integer>> blockSet, int AREA) {
        Set<List<Integer>> visited = new HashSet<>();
        Deque<int[]> deque = new LinkedList<>();
        List<Integer> list = new ArrayList<>(2);
        list.add(source[0]);
        list.add(source[1]);
        deque.addLast(source);
        visited.add(list);
        while (!deque.isEmpty()) {
            // 可达网格个数大于包围圈面积，连通
            if (visited.size() > AREA) return true;
            int[] cur = deque.poll();
            int x = cur[0];
            int y = cur[1];
            //  经过了起点、终点，连通
            if (x == target[0] && y == target[1]) return true;
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= GRID_EDGE || ny < 0 || ny >= GRID_EDGE) continue;
                int[] next = new int[]{nx, ny};
                list = new ArrayList<>(2);
                list.add(nx);
                list.add(ny);
                if (blockSet.contains(list) || visited.contains(list)) continue;
                deque.addLast(next);
                visited.add(list);
            }
        }
        return visited.size() > AREA;
    }
}
