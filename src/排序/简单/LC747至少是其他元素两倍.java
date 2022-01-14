package 排序.简单;

/**
 * @author Chthollists email:
 * @create 2022-01-12 21:13
 */
public class LC747至少是其他元素两倍 {
    public static void main(String[] args) {
        int res = dominantIndex(new int[]{1, 0});
        System.out.println(res);
    }

    public static int dominantIndex(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;
        if(n == 1) return 0;
        int max = nums[0];
        int secondMax = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 1; i < n; i++) {
            if(nums[i] >= max){
                secondMax = max;
                max = nums[i];
                index = i;
            }else if(nums[i] >= secondMax) secondMax = nums[i];
        }
        // System.out.print(1 >= 0 << 1);
        return max >= secondMax << 1 ? index : -1;
    }
}
