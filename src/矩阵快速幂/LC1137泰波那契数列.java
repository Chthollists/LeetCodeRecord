package 矩阵快速幂;

/**
 * @author Chthollist email:
 * @create 2022-01-17 20:16
 */
public class LC1137泰波那契数列 {
    public static void main(String[] args) {
        System.out.println(new LC1137泰波那契数列().tribonacci(25));
    }

    public int tribonacci(int n) {
        if (n <= 1) return n;
        if (n == 2) return 1;
        int[][] ini = new int[][]{
                {1},{1},{0}
        };
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 0},
                {0, 1, 0}
        };
        int x = n - 2;
        while (x > 0) {
            if ((x & 1) == 1) {
                ini = mul(matrix, ini);
            }
            matrix = mul(matrix, matrix);
            x >>= 1;
        }
//        for (int[] ints : ini) {
//            System.out.println(Arrays.toString(ints));
//        }
        return ini[0][0];
    }

    private int[][] mul(int[][] matrixA, int[][] matrixB) {
        int row = matrixA.length;
        int col = matrixB[0].length;
        int[][] res = new int[row][col];
        int size = matrixB.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < size; k++) {
                    res[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return res;
    }
}
