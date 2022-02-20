package 简单题.矩阵模拟;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:41
 */
public class LC1380矩阵中的快乐数 {

    public List<Integer> luckyNumbers(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;
        int m = matrix.length, n = matrix[0].length;
        int[] rowMin = new int[m];
        int[] colMax = new int[n];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == rowMin[i] && matrix[i][j] == colMax[j]) {
                    list.add(matrix[i][j]);
                }
            }
        }
        return list;
    }
}
