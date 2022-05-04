package 简单题.矩阵模拟;

/**
 * @author Chthollist email:
 * @create 2022-02-24 21:09
 */
public class LC1706球会落何处 {

    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            int row = 0, col = i;
            while (row < m) {
                int nextCol = col + grid[row][col];
                // 超出两侧边界
                if (nextCol < 0 || nextCol >= n) {
                    pos[i] = -1;
                    break;
                }
                // 挡板形成夹角
                if (grid[row][nextCol] != grid[row][col]) {
                    pos[i] = -1;
                    break;
                }
                row++;
                col = nextCol;
            }
            // 到达矩阵底部
            if (pos[i] != -1) pos[i] = col;
        }
        return pos;
    }
}
