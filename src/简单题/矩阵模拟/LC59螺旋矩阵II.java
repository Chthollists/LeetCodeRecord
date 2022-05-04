package 简单题.矩阵模拟;

/**
 * @author Chthollist email:
 * @create 2022-02-23 22:39
 */
public class LC59螺旋矩阵II {
    public static void main(String[] args) {
        new LC59螺旋矩阵II().generateMatrix(3);
    }

    public int[][] generateMatrix(int n) {
        // int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] matrix = new int[n][n];
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int val = 1;
        while(l <= r && t <= b) {
            for(int col = l; col <= r; col++) {
                matrix[t][col] = val;
                val++;
            }
            for(int row = t + 1; row <= b; row++) {
                matrix[row][r] = val;
                val++;
            }
            for(int col = r - 1; col > l; col--) {
                matrix[b][col] = val;
                val++;
            }
            for(int row = b; row > t; row--) {
                matrix[row][l] = val;
                val++;
            }
            l++;
            r--;
            t++;
            b--;
        }
        return matrix;
    }
}
