package 简单题.矩阵模拟;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-02-23 22:52
 */
public class LC54螺旋矩阵 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        int l = 0, r = n - 1, t = 0, b = m - 1;
        while (l <= r && t <= b) {
            for (int col = l; col <= r; col++) {
                list.add(matrix[t][col]);
            }
            for (int row = t + 1; row <= b; row++) {
                list.add(matrix[row][r]);
            }
            if (l < r && t < b) {
                for (int col = r - 1; col > l; col--) {
                    list.add(matrix[b][col]);
                }
                for (int row = b; row > t; row--) {
                    list.add(matrix[row][l]);
                }
            }
            l++;
            r--;
            t++;
            b--;
        }
        return list;
    }
}
