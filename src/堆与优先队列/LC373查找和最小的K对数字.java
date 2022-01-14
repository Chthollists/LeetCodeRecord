package 堆与优先队列;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Chthollists email:
 * @create 2022-01-14 12:33
 */
public class LC373查找和最小的K对数字 {
    public static void main(String[] args) {
    }

    // 方法一：多路归并 & 小顶堆
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return null;
        List<List<Integer>> smallestPairs = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;
//        if (k > m * n) return null;
        PriorityQueue<int[]> queue = new PriorityQueue<>(k,
                (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        // 1. 维护 K 个元素到堆中 : (i, 0)
        for (int i = 0; i < Math.min(n, k); i++) {
            queue.add(new int[]{i, 0});
        }
        // 2. 取出堆顶元素并加入新元素
        while (k > 0 && !queue.isEmpty()) {
            int[] pairs = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[pairs[0]]);
            list.add(nums2[pairs[1]]);
            smallestPairs.add(list);
            if (pairs[1] + 1 < m) queue.add(new int[]{pairs[0], pairs[1] + 1});
            k--;
        }
        return smallestPairs;
    }

    // 方法二：二分
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return null;
        List<List<Integer>> smallestPairs = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;

        // 二分查找第 k 小的数对和的大小
        int left = nums1[0] + nums2[0];
        int right = nums1[n - 1] + nums2[m - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            long count = 0; // mid之前的元素的个数
            int start = 0;
            int end = m - 1;
            // 双指针查找当前比 mid 小的元素个数，用来确定二分的方向
            while (start < n && end >= 0) {
                if(count >= k) break;
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    count += end + 1;
                    start++;
                }
            }
            // mid前的元素超过k个，向左二分，没超过向右
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 分界点的值
        int divideNum = left;
        // 找到小于分界点的值的数对，并添加到TopK中

        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if( k > 0 && num1 + num2 < divideNum) {
                    List<Integer> list = new ArrayList<>();
                    list.add(num1);
                    list.add(num2);
                    smallestPairs.add(list);
                    k--;
                }else break;
            }
        }

        // 找到等于分界点的值的数对
        int index = m - 1;
        for (int i = 0; i < n && k > 0; i++) {
            // 找到第一个不大于分界点值的数对
            while (index >= 0 && nums1[i] + nums2[index] > divideNum) {
                index--;
            }
            for (int j = i; j >= 0; j--) {
                if(k > 0 && nums1[j] + nums2[index] == divideNum) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums1[j]);
                    list.add(nums2[index]);
                    smallestPairs.add(list);
                    k--;
                }else break;
            }
        }
        return smallestPairs;
    }


}
