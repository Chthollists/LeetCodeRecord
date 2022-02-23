package 搜索.DFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Chthollist email:
 * @create 2022-02-23 15:45
 */
public class LC1020飞地的数量 {

/*    // 0. DFS 超时
    private boolean[][] enclaves;

    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        enclaves = new boolean[m][n];
        int sum = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    sum++;
                    if(!enclaves[i][j]) backTracing(i, j, i, j, grid);
                }
            }
        }
        int count = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (enclaves[i][j]) {
                    count++;
                }
            }
        }
        return sum - count;
    }

    private void backTracing(int row, int col, int nr, int nc, int[][] grid) {
        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
            enclaves[row][col] = true;
            return;
        }
        if (grid[nr][nc] == 0 || visited[nr][nc]) return;
        visited[nr][nc] = true;
        for (int[] dir : dirs) {
            backTracing(nr, nc, nr + dir[0], nc + dir[1], grid);
            if(enclaves[nr][nc]) {
                enclaves[row][col] = true;
                break;
            }
        }
        visited[nr][nc] = false;
    }*/

    // 1. DFS
    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    private boolean[][] visited;

    public int numEnclaves1(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    public void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0 || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        for (int[] dir : dirs) {
            dfs(grid, row + dir[0], col + dir[1]);
        }
    }


    // 2. BFS
    public int numEnclaves2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> deque = new LinkedList<>();
        // 先把边界的陆地放入队列
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                visited[i][0] = true;
                deque.add(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                visited[i][n - 1] = true;
                deque.add(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (grid[0][j] == 1) {
                visited[0][j] = true;
                deque.add(new int[]{0, j});
            }
            if (grid[m - 1][j] == 1) {
                visited[m - 1][j] = true;
                deque.add(new int[]{m - 1, j});
            }
        }
        // 搜索队列中已有的边界上的陆地所连通的陆地，将这些陆地加入队列，并继续BFS搜索
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int row = cur[0], col = cur[1];
            for (int[] dir : dirs) {
                int nextRow = row + dir[0], nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    deque.add(new int[]{nextRow, nextCol});
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    // 3. 并查集

    public int numEnclaves3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        uf.union(index, index + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        uf.union(index, index + n);
                    }
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !uf.isOnEdge(i * n + j)) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }
}

class UnionFind {
    private int[] parent;
    private boolean[] onEdge;
    private int[] rank;

    public UnionFind(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        onEdge = new boolean[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    parent[index] = index;
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        onEdge[index] = true;
                    }
                }
            }
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
                onEdge[rootx] |= onEdge[rooty];
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
                onEdge[rooty] |= onEdge[rootx];
            } else {
                parent[rooty] = rootx;
                onEdge[rootx] |= onEdge[rooty];
                rank[rootx]++;
            }
        }
    }

    public boolean isOnEdge(int i) {
        return onEdge[find(i)];
    }
}

// -------------------------------------------
// 并查集模板
class UnionTemplate {

    int m, n;
    int[][] grid;
    int N = 510;
    int[] p = new int[N*N]; // M*N
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                p[getIndex(i, j)] = getIndex(i, j); // 给每一个单元格编号
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    // 不是陆地、与编号为 0 的边界连通的单元格无需判断
                    if (grid[i][j] != 1 || query(getIndex(i, j), 0)) continue;
                    dfs(i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !query(getIndex(i, j), 0))
                    res++;
            }
        }
        return res;
    }

    private void dfs(int i, int j) {
        union(getIndex(i, j), 0);
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (grid[x][y] != 1 || query(getIndex(x, y), 0)) continue;
            dfs(x, y);
        }

    }

    private void union(int a, int b) {
        p[find(a)] = find(b);
    }

    private boolean query(int index, int target) {
        return find(index) == find(target);
    }

    private int find(int index) {
        if (p[index] != index) p[index] = find(p[index]);
        return p[index];
    }

    private int getIndex(int i, int j) {
        return i * n + j + 1;
    }


}
