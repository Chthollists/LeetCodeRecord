package 搜索.BFS;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-01-29 17:13
 */
public class LC1765地图中的最高点 {

    public int[][] highestPeak1(int[][] isWater) {
        if (isWater == null || isWater.length == 0) return null;
        int m = isWater.length;
        int n = isWater[0].length;
        Deque<int[]> deque = new LinkedList<>();
        int[][] height = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(height[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    deque.add(new int[]{i, j});
                }
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && height[nx][ny] == -1) {
                    height[nx][ny] = height[x][y] + 1;
                    deque.add(new int[]{nx, ny});
                }
            }
        }
        return height;
    }


    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] visited;

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] ans = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(ans[i], Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    dfs(ans, i, j, m, n);
                }
            }
        }
        return ans;
    }

    public void dfs(int[][] ans, int x, int y, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
            return;
        }
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[x][y]) {
                ans[nx][ny] = Math.min(ans[nx][ny], ans[x][y] + 1);
                visited[nx][ny] = true;
                dfs(ans, nx, ny, m, n);
            }
        }
    }


}
