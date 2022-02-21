package 搜索.DFS;

/**
 * @author Chthollist email:
 * @create 2022-02-20 16:23
 */
public class LC1219黄金矿工 {
    public static void main(String[] args) {
        new LC1219黄金矿工().getMaximumGold(new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}});
    }

    int[][] grid;
    boolean[][] vis;
    int m, n;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        vis = new boolean[m][n];
        int maxGold = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    vis[i][j] = true;
                    maxGold = Math.max(maxGold, dfs(i, j));
                    vis[i][j] = false;
                }
            }
        }
        return maxGold;

    }

    private int dfs(int x, int y) {
        int gold = grid[x][y];
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0 && !vis[nx][ny]) {
                vis[nx][ny] = true;
                gold = Math.max(gold, grid[x][y] + dfs(nx, ny));
                vis[nx][ny] = false;
            }
        }
        return gold;
    }
}
