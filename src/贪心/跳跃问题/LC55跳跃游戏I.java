package 贪心.跳跃问题;

/**
 * @author Chthollist email:
 * @create 2022-01-21 23:54
 */
public class LC55跳跃游戏I {
    // 问题是判断能否到达末尾
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0 )return false;
        int n = nums.length;
        if(n == 1) return true;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if(i <= maxIndex) {
                maxIndex = Math.max(maxIndex, i + nums[i]);
            }
            if(maxIndex >= n - 1) return true;
        }
        return false;
    }


    public boolean canJumpDP(int[] nums) {
        if(nums == null || nums.length == 0 )return false;
        int n = nums.length;
        if(n == 1) return true;
        int[] dp = new int[n];
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if(i <= maxIndex) {
                dp[i] = Math.max(dp[i], i + nums[i]);
            }
            maxIndex = Math.max(maxIndex, dp[i]);
            if(maxIndex >= n - 1) return true;
        }
        return false;
    }

}
