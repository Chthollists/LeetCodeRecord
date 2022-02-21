package 双指针;

/**
 * @author Chthollist email:
 * @create 2022-02-21 16:38
 */
public class LC75颜色分类 {
    public void sortColors1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int p0 = 0, p1 = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int temp;
            if (nums[i] == 1) {
                temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                p1++;
            } else if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                // 如果 0 的指针在 1 的指针前面，说明有一个 1 被换到后面了，需要再换一次
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                p0++;
                p1++;
            }
        }
    }


    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i < n; i++) {
            int temp;
            // 2 往后放，且注意换到前面的是 2 时还需要循环后放
            while (p2 >= i && nums[i] == 2) {
                temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2--;
            }

            // 不管本来就是 0 还是是换 2 的时候换来的 0， 都换到前面去
            if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
        }
    }
}
