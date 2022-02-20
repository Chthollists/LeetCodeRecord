package 动态规划.区间DP;

import java.util.Arrays;

/**
 * @author Chthollist email:
 * @create 2022-01-22 13:36
 */
public class LC1246删除回文子数组 {

    public static void main(String[] args) {
        System.out.println(new LC1246删除回文子数组().minimumMoves(new int[]{1, 3, 4, 1, 5}));
        System.out.println(new LC1246删除回文子数组().minimumMovesDP(new int[]{1, 3, 4, 1, 5}));
    }


    // 方法1 记忆化搜索
    int[][] memo;

    public int minimumMoves(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        if (n == 1) return 1;
        memo = new int[n][n];
        return dfs(arr, 0, n - 1);
    }

    private int dfs(int[] arr, int left, int right) {
        if (left > right) return 0;
        if (memo[left][right] != 0) return memo[left][right];
        int count = 0;
        // 第一种情况，单独删除
        count = dfs(arr, left + 1, right) + 1;
        // 第二种情况，构成回文串删除
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] == arr[left]) {
                // 取两种情况中的最小值，且第二种情况是将数组拆分开来分别计算的
                count = Math.min(count, dfs(arr, left + 1, i - 1) + dfs(arr, i + 1, right));
                count += left == i - 1 ? 1 : 0;
            }
        }
        memo[left][right] = count;
        return count;
    }

    // 方法2 动态规划
    public int minimumMovesDP(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        if (n == 1) return 1;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 1; // i == j
        }
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (i == j - 1) {
                    dp[i][j] = arr[i] == arr[j] ? 1 : 2;
                    continue;
                }
                if (arr[i] == arr[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
