package 哈希表.简单哈希;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:40
 */
public class LC1748唯一元素的和 {
    // 一次遍历
    public int sumOfUnique(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> state = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!state.containsKey(num)) {
                ans += num;
                state.put(num, 1);
            } else if (state.get(num) == 1) {
                ans -= num;
                state.put(num, 2);
            }
        }
        return ans;
    }
}
