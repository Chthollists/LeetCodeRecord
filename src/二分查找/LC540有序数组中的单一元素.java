package 二分查找;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:39
 */
public class LC540有序数组中的单一元素 {

    public int singleNonDuplicate2(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        int mid;
        while(left < right) {
            mid = (right - left) / 2 + left;
            // mid为奇数：nums[mid] == nums[mid - 1]
            // mid为偶数：nums[mid] == nums[mid + 1]
            if(nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }

    public int singleNonDuplicate1(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        int mid;
        while(left < right) {
            mid = (right - left) / 2 + left;
            mid -= mid & 1; // 偶数不变、奇数减一变偶数
            if(nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }


    public int singleNonDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int[] bit = new int[32];
        int res = 0;
        int k = 2;

        for(int num : nums) {
            for(int i = 0; i < 32; i++) {
                if(((num >> i) & 1) == 1) {
                    bit[i]++;
                }
            }
        }

        for(int i = 0; i < 32; i++) {
            if(((bit[i] % k) & 1) == 1) {
                res += 1 << i;
            }
        }
        return res;
    }
}
