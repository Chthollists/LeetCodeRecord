package 排序.堆排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Chthollists email:
 * @create 2022-01-12 23:12
 */
public class HeapSorter {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]); // 7354613
        }
        new HeapSorter().heapSort(nums, nums.length);
        System.out.println(Arrays.toString(nums));
    }

    public void heapSort(int[] nums, int n) {
        buildHeap(nums, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, i, 0); // 交换堆顶和末尾的元素，最大值
            heapify(nums, i, 0); // 从根节点开始调整整个树
        }
    }

    // 创建大顶堆
    private void buildHeap(int[] nums, int n) {
        int lastIndex = n - 1;
        int parentIndex = (lastIndex - 1) / 2;
        for (int i = parentIndex; i >= 0; i--) {
            heapify(nums, n, i);
        }
        System.out.println(Arrays.toString(nums));
    }

    private void heapify(int[] nums, int n, int i) {
        if (i >= n) return;
        int left = 2 * i + 1, right = 2 * i + 2; // 左子节点和后子节点的索引
        int max = i; // 最大元素的索引，初始化为目前要调整的子树的根节点
        // 比较交换局部最大值
        if (left < n && nums[left] > nums[max]) max = left;
        if (right < n && nums[right] > nums[max]) max = right;
        if (max != i) { // 如果max变化了
            swap(nums, i, max);
            heapify(nums, n, max); // 继续取调整节点值被提升到 i 节点后，新的需要调整的子树
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
