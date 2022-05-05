package 滑动窗口;

import java.util.Arrays;

/**
 * @author Chthollist email:
 * @create 2022-05-05 21:19
 */
public class LC713乘积小于K的子数组个数 {

    // 子数组：连续
    // O(n) 滑动窗口，固定右端点 end
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int cnt = 0;
        int val = 1;
        int start = 0;
        for (int end = 0; end < n; end++) {
            val *= nums[end];
            while (val >= k && start <= end) val /= nums[start++];
            cnt += end - start + 1;
        }
        return cnt;
    }

    // 子集：不连续
    // DFS回溯爆搜
    int count = 0;

    public int numSubSetProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            backTracing(nums, i, k, nums[i]);
        }
        return count;
    }

    private void backTracing(int[] nums, int start, int k, int val) {
        if (val < k) count++;
        if (val >= k) return;

        for (int i = start + 1; i < nums.length && val < k; i++) {
            val *= nums[i];
            if(val >= k ) break;
            backTracing(nums, i , k, val);
            val /= nums[i];
        }
    }


    public static void main(String[] args) {
        LC713乘积小于K的子数组个数 solution = new LC713乘积小于K的子数组个数();
        solution.numSubSetProductLessThanK(new int[]{10, 5, 2, 6}, 100);
        System.out.println(solution.count);
    }


}
