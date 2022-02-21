package 双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-02-21 15:40
 */
public class LC15三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int first = -nums[i];
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[j] + nums[k] > first) k--;
                else if (nums[j] + nums[k] < first) j++;
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (k > j && nums[k] == nums[k - 1]) k--;
                    j++;
                    k--;
                }
            }
        }
        return res;

    }
}
