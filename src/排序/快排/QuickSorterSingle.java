package 排序.快排;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Chthollists email:
 * @create 2022-01-12 21:53
 */
public class QuickSorterSingle {
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        new QuickSorterSingle().quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int idx = partition(nums, l, r);
            quickSort(nums, l, idx - 1);
            quickSort(nums, idx + 1, r);
        }
    }

        private int partition(int[] nums, int l, int r) {
            int idx = random.nextInt(r - l) + l;
            swap(nums, l, idx);
            int pivot = nums[l];
            int mark = l;
            for (int i = l + 1; i <= r; i++) {
                if(nums[i] < pivot) {
                    mark++;
                    swap(nums, mark, i);
                }
            }
            swap(nums, l , mark);
            return mark;
        }


    private void swap(int[] nums, int i, int j) {
        // 位运算
        nums[i] = nums[i] ^ nums[j] ^ (nums[j] = nums[i]);
    }
}
