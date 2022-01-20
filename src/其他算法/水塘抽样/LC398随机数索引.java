package 其他算法.水塘抽样;

import java.util.*;

/**
 * @author Chthollist email:
 * @create 2022-01-19 23:51
 */
public class LC398随机数索引 {

    // 方法一：哈希表
    private int n;
    private Map<Integer, List<Integer>> map = new HashMap<>();

/*    public LC389随机数索引(int[] nums) {
        this.n = nums.length;
        random = new Random();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(i);
            map.put(nums[i], list);
        }

    }*/

    public int pick1(int target) {
        List<Integer> targetList = map.get(target);
        int size = targetList.size();
        int index = random.nextInt(size);
        return targetList.get(index);
    }


    // 方法二：水塘抽样
    Random random;
    int[] nums;

    public LC398随机数索引(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        random = new Random();
    }

    public int pick(int target) {
        int index = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                count++;
                if (random.nextInt(count) == 0) index = i;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 8, 3, 5, 2, 3, 1, 7, 2, 4, 3, 9, 6, 9, 3};
        new LC398随机数索引(nums);
    }
}
