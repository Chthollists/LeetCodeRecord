package 其他算法.摩尔投票;

/**
 * @author Chthollist email:
 * @create 2022-02-21 13:35
 */
public class LC169多数元素 {
    public static void main(String[] args) {
        System.out.println(1<<2);
    }

    // 从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1
    // 减到0就重新换个数开始计数，总能找到最多的那个

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int count = 1, num = nums[0];
        for (int i = 1; i < n; i++) {
            if (count == 0) num = nums[i];
            if (nums[i] == num) count++;
            else count--;
        }
        return num;
    }
}
