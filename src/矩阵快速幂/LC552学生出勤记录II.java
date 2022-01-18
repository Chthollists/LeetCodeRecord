package 矩阵快速幂;

/**
 * @author Chthollist email:
 * @create 2022-01-18 14:42
 */
public class LC552学生出勤记录II {

    final int MOD = (int) 1e9 + 7;

    // 方法1. 记忆化DFS
    public int checkRecord1(int n) {
        int[][][] memo = new int[n][2][3]; // 长度、A的个数、末尾L的连续个数
        return dfs(0, n, 0, 0, memo);
    }

    private int dfs(int size, int n, int absent, int late, int[][][] memo) {
        if (size == n) return 1;
        if (memo[size][absent][late] != 0) return memo[size][absent][late];
        int res = 0;
        // 放置 P
        res += dfs(size + 1, n, absent, 0, memo);
        res %= MOD;
        // 放置 A
        if (absent < 1) {
            res += dfs(size + 1, n, absent + 1, 0, memo);
            res %= MOD;
        }
        // 放置 L
        if (late < 2) {
            res += dfs(size + 1, n, absent, late + 1, memo);
            res %= MOD;
        }
        memo[size][absent][late] = res;
        return res;
    }


    // 方法2. 状态机DP
    public int checkRecord2(int n) {
        long[][][] dp = new long[n][2][3]; // 长度、A的个数、末尾L的连续个数
        dp[0][0][0] = 1;
        dp[0][1][0] = 1;
        dp[0][0][1] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD; // 添加 P
            dp[i][0][1] = dp[i - 1][0][0] % MOD; // 添加 L
            dp[i][0][2] = dp[i - 1][0][1] % MOD; // 添加 L
            // 前三个添加 A  & 后三个添加 P
            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
            dp[i][1][1] = dp[i - 1][1][0] % MOD; // 添加 L
            dp[i][1][2] = dp[i - 1][1][1] % MOD; // 添加 L
        }
        int count = 0;
        for (int i = 0; i < dp[0].length; i++) {
            for (int j = 0; j < dp[0][0].length; j++) {
                count += dp[n - 1][i][j];
                count %= MOD;
            }
        }
        return count;
    }

    // 方法2 优化. 空间压缩，三维DP 优化为 二维DP
    public int checkRecord2_1(int n) {
        long[][] dp = new long[n][6]; // 长度、六种状态分别为 0A0L、0A1L、0A2L、1A0L、1A1L、1A2L
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][3] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = dp[i - 1][0] % MOD;
            dp[i][2] = dp[i - 1][1] % MOD;
            dp[i][3] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3] + dp[i - 1][4] + dp[i - 1][5]) % MOD;
            dp[i][4] = dp[i - 1][3] % MOD;
            dp[i][5] = dp[i - 1][4] % MOD;
        }
        int count = 0;
        for (int i = 0; i < dp[0].length; i++) {
            count += dp[n - 1][i];
            count %= MOD;
        }
        return count;
    }

    // 方法2 优化. 空间压缩，三维DP 优化为 一维DP，状态机 + 滚动数组
    public int checkRecord2_2(int n) {
        if (n == 1) return 3;
        long[][] dp = new long[2][6]; // 当前长度、六种状态分别为 0A0L、0A1L、0A2L、1A0L、1A1L、1A2L
        // 辅助数组 初始化即可
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][3] = 1;

        for (int i = 1; i < n; i++) {
            dp[0][0] = (dp[1][0] + dp[1][1] + dp[1][2]) % MOD;
            dp[0][1] = dp[1][0] % MOD;
            dp[0][2] = dp[1][1] % MOD;
            dp[0][3] = (dp[1][0] + dp[1][1] + dp[1][2] + dp[1][3] + dp[1][4] + dp[1][5]) % MOD;
            dp[0][4] = dp[1][3] % MOD;
            dp[0][5] = dp[1][4] % MOD;
            // 滚动辅助数组
            /*for (int j = 0; j < dp[0].length; j++) {
                dp[1][j] = dp[0][j];
            }*/
            System.arraycopy(dp[0], 0, dp[1], 0, dp[0].length);
        }
        int count = 0;
        for (long cnt : dp[0]) {
            count += cnt;
            count %= MOD;
        }
        return count;
    }

    // 方法3. 矩阵快速幂
    public int checkRecord(int n) {
        long[][] ini = new long[][]{
                {1}, {1}, {0}, {1}, {0}, {0}
        };
        long[][] matrix = new long[][]{
                {1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0},
        };

        int x = n - 1;
        while (x > 0) {
            if ((x & 1) == 1) ini = mul(matrix, ini);
            matrix = mul(matrix, matrix);
            x >>= 1;
        }
        int count = 0;
        for (long[] cnt : ini) {
            count += cnt[0];
            count %= MOD;
        }
        return count;

    }

    private long[][] mul(long[][] matrixA, long[][] matrixB) {
        int row = matrixA.length, col = matrixB[0].length;
        int size = matrixB.length;
        long[][] res = new long[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < size; k++) {
                    res[i][j] += matrixA[i][k] * matrixB[k][j];
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
    }

}
