package 简单题.简单模拟;

/**
 * @author Chthollists email:
 * @create 2022-01-01 16:57
 */
public class LC2022一维数组转换为二维 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original == null || original.length == 0) return new int[0][0];
        int len = original.length;
        if (len != m * n) return new int[0][0];
        int[][] res = new int[m][n];
        for (int i = 0; i < len; i++) {
            int row = i / n;
            int col = i % n;
            res[row][col] = original[i];
        }
        return res;
    }
}
