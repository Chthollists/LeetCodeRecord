package 动态规划.LIS问题DP二分;

import java.util.Arrays;

/**
 * @author Chthollists email:
 * @create 2022-01-12 14:40
 */
public class LC334递增的三元子序列 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 0, 5, 1, 3, 6};
        nums = new int[]{2, 5, 3, 1, 2, 6, 3, 4, 5};
        int len = getLengthOfLIS(nums);
        boolean b = new LC334递增的三元子序列().increasingTriplet2(nums);
        System.out.println(len);
    }

    // 方法三：另类的动态规划
    public boolean increasingTriplet3(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        // 左侧的最小值数组
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        // 右侧的最大值数组
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        // 寻找递增三元子序列
        for (int i = 1; i < n - 1; i++) {
            if (leftMin[i - 1] < nums[i] && rightMax[i + 1] > nums[i]) return true;
        }
        return false;
    }

    // 方法二优化：LIS最优解：动态规划（贪心） + 二分
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) min = num;
            else if (num <= secondMin) secondMin = num;
            else return true;
        }
        return false;
    }

    // 方法二：LIS最优解：动态规划（贪心） + 二分
    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > dp[len - 1]) {
                dp[len++] = num;
                if (len >= 3) return true;
                continue;
            }
            int left = 0, right = len - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (dp[mid] >= num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            dp[right] = num;
            if (len >= 3) return true;
        }
        return false;
    }

    // 方法一：动态规划，暴力 n^2
    public boolean increasingTriplet1(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i])
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
            }
            if (dp[i] >= 3) return true;
        }
        return false;
    }

    public static int getLengthOfLIS(int[] nums) {
        int n = nums.length;
        int max = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i])
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
