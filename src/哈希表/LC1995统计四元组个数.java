package 哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chthollists email:
 * @create 2021-12-29 22:44
 * * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同四元组 (a, b, c, d) 的数目
 * * 条件：
 * * nums[a] + nums[b] + nums[c] == nums[d]
 * * a < b < c < d
 * * 注意同样值的数字可以多次使用
 */
public class LC1995统计四元组个数 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,3,5};
        int count = countQuadruplets(nums);
        new LC1995统计四元组个数().countQuadruplets1(nums);
        new LC1995统计四元组个数().countQuadruplets2(nums);
        new LC1995统计四元组个数().countQuadruplets3(nums);
        System.out.println(count);
    }

    public int countQuadruplets1(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) return res;
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int c = n - 2; c >= 2; c--) {
            map.put(nums[c + 1], map.getOrDefault(nums[c + 1], 0) + 1);

            for (int b = c - 1; b >= 1; b--) {
                for (int a = b - 1; a >= 0; a--) {
                    res += map.getOrDefault(nums[a] + nums[b] + nums[c], 0);
                }
            }
        }
        return res;
    }

    public int countQuadruplets2(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) return res;
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                map.put(nums[d] - nums[b + 1], map.getOrDefault(nums[d] - nums[b + 1], 0) + 1);
            }

            for (int a = 0; a < b; a++) {
                res += map.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return res;
    }


    public int countQuadruplets3(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) return res;
        int n = nums.length;
        int[][][] dp = new int[n + 1][101][4];
        dp[0][0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    dp[i][j][k] += dp[i - 1][j][k];
                    if (j - nums[i - 1] >= 0 && k - 1 >= 0) {
                        dp[i][j][k] += dp[i - 1][j - nums[i - 1]][k - 1];
                    }
                }
            }
        }
        for (int i = 3; i < n; i++) {
            res += dp[i][nums[i]][3];
        }
        return res;
    }


    // 复杂度最低
    public static int countQuadruplets(int[] nums) {
        int[][] dp = new int[4][10]; // 101d
        dp[0][0] = 1;
        int ans = 0;
        for (int num : nums) {
            ans += dp[3][num];
            for (int j = dp.length - 1; j > 0; j--)
                for (int i = num; i < dp[0].length; i++)
                    dp[j][i] += dp[j - 1][i - num];
        }
        return ans;
    }
}
