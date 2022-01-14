package 排序.归并;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Chthollists email:
 * @create 2022-01-12 23:12
 */
public class MergeSorterArray {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]); // 7354613
        }
        new MergeSorterArray().mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        System.out.println(Arrays.toString(nums));
    }


    public void mergeSort(int[] nums, int left, int right, int[] save) {
        if (left < right) {
            int mid = (right - left) / 2 + left;
            mergeSort(nums, left, mid, save);
            mergeSort(nums, mid + 1, right, save);
            merge(nums, left, mid, right, save);
        }
    }

    private void merge(int[] nums, int left, int mid, int right, int[] save) {
        int i = left, j = mid + 1; // 双指针，用来合并数组
        int index = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                save[index++] = nums[i++];
            } else {
                save[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            save[index++] = nums[i++];
        }
        while (j <= right) {
            save[index++] = nums[j++];
        }
        // 将排序后的辅助数组copy回原数组
        index = 0;
        while (left <= right) {
            nums[left++] = save[index++];
        }
    }
}
