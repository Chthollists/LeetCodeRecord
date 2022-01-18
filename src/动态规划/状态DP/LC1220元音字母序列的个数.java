package 动态规划.状态DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chthollist email:
 * @create 2022-01-17 13:19
 */
public class LC1220元音字母序列的个数 {
    public static void main(String[] args) {
        new LC1220元音字母序列的个数().countVowelPermutation2(4);

    }

    // 方法一 优化： 状态机、动态规划 一维DP
    public int countVowelPermutation0(int n) {
        if (n == 1) return 5;
        long[] dp = new long[5]; // 状态机DP数组：对应五个元音字母结尾的字符串个数
        long[] help = new long[5]; // 辅助数组
        Arrays.fill(dp, 1);
        Arrays.fill(help, 1);
        long sum = 0L;
        long mod = (long) (Math.pow(10, 9) + 7);
        for (int i = 1; i < n; i++) {
            dp[0] = (help[1] + help[2] + help[4]) % mod;
            dp[1] = (help[0] + help[2]) % mod;
            dp[2] = (help[1] + help[3]) % mod;
            dp[3] = help[2] % mod;
            dp[4] = (help[2] + help[3]) % mod;

            // help = Arrays.copyOf(dp, 5);
            System.arraycopy(dp, 0, help, 0, 5);
        }
        // System.out.println(Arrays.toString(dp));
        for (long count : dp) {
            sum = (sum + count) % mod;
        }
        return (int) sum;
    }

    // 方法一 ： 状态机、动态规划 二维DP
    public int countVowelPermutation1(int n) {
        if (n == 1) return 5;
        long[][] dp = new long[n + 1][5]; // 状态机DP数组：对应长度为 i 且五个元音字母结尾的字符串个数
        for (int i = 0; i < dp[0].length; i++) {
            dp[1][i] = 1;
        }
        long sum = 0L;
        long mod = (long) (Math.pow(10, 9) + 7);
        for (int i = 2; i < n + 1; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % mod;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod;
            dp[i][3] = dp[i - 1][2] % mod;
            dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
        }
        for (int i = 0; i < 5; i++) {
            sum = (sum + dp[n][i]) % mod;
        }
        return (int) sum;
    }

    // 方法二 ： DFS + 记忆化搜索
    char[] letter = new char[]{'a', 'e', 'i', 'o', 'u'};
    // 记忆化搜索
    Map<String, Long> map = new HashMap<>();
    long mod = (long) (Math.pow(10, 9) + 7);

    public int countVowelPermutation2(int n) {
        if (n == 1) return 5;
        long sum = 0L;
        for (int i = 0; i < letter.length; i++) {
            sum = (sum + dfs(letter[i], 1, n)) % mod;
        }
        return (int) sum;
    }

    private long dfs(char pre, int len, int n) {
        if (len == n) {
            return 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pre).append(len);
        if (map.containsKey(sb.toString())) {
            return map.get(sb.toString());
        }
        long res = 0;
        for (char c : letter) {
            if (pre == 'a' && c == 'e') {
                res += dfs(c, len + 1, n);
            } else if (pre == 'e' && (c == 'a' || c == 'i')) {
                res += dfs(c, len + 1, n);
            } else if (pre == 'i' && c != 'i') {
                res += dfs(c, len + 1, n);
            } else if (pre == 'o' && (c == 'i' || c == 'u')) {
                res += dfs(c, len + 1, n);
            } else if (pre == 'u' && c == 'a') {
                res += dfs(c, len + 1, n);
            }
            res %= mod;
            // 记忆化搜索
            map.put(sb.toString(), res);
        }
        return res;
    }

    // 方法三：矩阵快速幂
    int MOD = (int) 1e9 + 7;

    public int countVowelPermutation(int n) {
        if (n == 1) return 5;
        long[][] matrix = new long[][]{
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0},
        };
        long[][] first = new long[][]{
                {1}, {1}, {1}, {1}, {1}
        };

        /*long[][] first = new long[matrix.length][matrix[0].length];
        for (int i = 0; i < m; ++i) {
            first[i][i] = 1;
        }*/

        // 快速幂
        int x = n - 1;
        while (x != 0) {
            if ((x & 1) == 1) first = mul(matrix, first); // 奇次幂
            matrix = mul(matrix, matrix); // 偶次幂
            x >>= 1;
        }
        long sum = 0;
        for (int i = 0; i < 5; i++) sum += first[i][0];
        return (int) (sum % MOD);
    }

    private long[][] mul(long[][] matrixA, long[][] matrixB) {
        // 第一个矩阵的行数，和第二个矩阵的列数
        int row = matrixA.length, col = matrixB[0].length;
        // 第一个矩阵的列数 = 第二个矩阵的行数(最内侧循环)
        int len = matrixB.length;
        long[][] res = new long[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < len; k++) {
                    res[i][j] += matrixA[i][k] * matrixB[k][j]; // 行和列相乘的和
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
    }
}
