package 动态规划.LIS问题DP二分;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chthollists email:
 * @create 2022-01-13 16:03
 */
public class LC354俄罗斯套娃信封问题 {
    public static void main(String[] args) {
        int[][] nums = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}, {2, 1}, {2, 2}};
        int res = new LC354俄罗斯套娃信封问题().maxEnvelopes(nums);
        System.out.println(res);
    }

    // 方法一：线性DP
    public int maxEnvelopes1(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        int[] dp = new int[n];
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxCount = Math.max(maxCount, dp[i]);
        }
        return maxCount;
    }

    // 方法二：序列DP & 二分 & 贪心
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int[] dp_len = new int[n];  // 以序列中第 i 个元素结尾的LIS的最长长度
        int[] dp_height = new int[n]; // 长度为 i 的LIS的末尾最小元素，只存了信封的高
        Arrays.fill(dp_height, Integer.MAX_VALUE);
        dp_height[0] = 0;
        int maxCount = 1;
        // widthIndex 表示前一种宽度的开始索引
        int widthIndex = 0;
        int len = 1;
        for (int i = 0; i < n; i++) {
            int height = envelopes[i][1];
            // 1. 遍历到的新元素的宽度与 LIS结尾元素数组dp_height中最新的宽度不同的情况，此时更新 dp_height
            if (envelopes[i][0] != envelopes[widthIndex][0]) {
                // 比较当前元素与前一种宽度的所有元素有元素结尾的LIS长度与目前的长度是否相等
                while (widthIndex < i) {
                    int prevLen = dp_len[widthIndex]; // 前一种宽度的所有元素有元素结尾的LIS长度与目前的长度
                    if (prevLen == len) {
                        // 相等在 dp_height 的末尾添加元素
                        dp_height[len++] = envelopes[widthIndex][1];
                    } else {
                        // 不相等时，LIS长度不变，dp_height 中对应长度的位置替换为前一种宽度中的最小值高度
                        dp_height[prevLen] = Math.min(dp_height[prevLen], envelopes[widthIndex][1]);
                    }
                    widthIndex++;
                }
            }
            // 2. 两者宽度不相等时，通过二分的方式来寻找当前元素的高度应该替换、插入到 dp_height 数组的哪个位置，但是
            int index = binarySearch(dp_height, height, 0, len);
            dp_len[i] = index;
            maxCount = Math.max(maxCount, dp_len[i]);
        }
        return maxCount;
    }


    private int binarySearch(int[] dp, int height, int left, int right) {
        while (right > left) {
            int mid = (right - left) / 2 + left;
            if (dp[mid] >= height) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    // 方法三：树状数组
    int[] tree;

    int lowbit(int x) {
        return x & -x;
    }

    public int maxEnvelopes3(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);

        // 先将所有的 h 进行离散化
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(envelopes[i][1]);
        int cnt = set.size();
        int[] hs = new int[cnt];
        int idx = 0;
        for (int i : set) hs[idx++] = i;
        Arrays.sort(hs);
        for (int i = 0; i < n; i++) envelopes[i][1] = Arrays.binarySearch(hs, envelopes[i][1]) + 1;

        // 创建树状数组
        tree = new int[cnt + 1];

        // f(i) 为考虑前 i 个物品，并以第 i 个物品为结尾的最大值
        int[] f = new int[n];
        int ans = 1;
        for (int i = 0, j = 0; i < n; i++) {
            // 对于 w 相同的数据，不更新 tree 数组
            if (envelopes[i][0] != envelopes[j][0]) {
                // 限制 j 不能越过 i，确保 tree 数组中只会出现第 i 个信封前的「历史信封」
                while (j < i) {
                    for (int u = envelopes[j][1]; u <= cnt; u += lowbit(u)) {
                        tree[u] = Math.max(tree[u], f[j]);
                    }
                    j++;
                }
            }
            f[i] = 1;
            for (int u = envelopes[i][1] - 1; u > 0; u -= lowbit(u)) {
                f[i] = Math.max(f[i], tree[u] + 1);
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}


/**
 * 解答错误，对于宽度相等时，dp数组的更新又问题，等待解决
 */
/*    public int maxEnvelopesError(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n];
        dp[0] = envelopes[0][1];
        int maxCount = 1;
        int prevIndex = 0; // 表示前一种宽度的元素的下标，因为只需要宽度，所以是其中的哪一个都可以
        int curIndex = 0; // 表示已经遍历过的最新宽度的元素的下标，因为只需要宽度，所以是其中的哪一个都可以
        for (int i = 1; i < n; i++) {
            int height = envelopes[i][1];
            int width = envelopes[curIndex][0];
            // 分类讨论：1. 新遍历到的元素的宽度与刚遍历过的最新宽度
            if (envelopes[i][0] != width) {
                if (height > dp[maxCount - 1]) {
                    dp[maxCount++] = height;
                    prevIndex = curIndex; // 更新前一种宽度的索引
                    curIndex = i; // 更新宽度的索引
                    continue;
                }
                int index = binarySearch(dp, height, maxCount);
                dp[index] = height;
                prevIndex = curIndex; // 更新前一种宽度的索引
                curIndex = i; // 更新宽度的索引
                // 2. 新遍历到的元素的宽度与刚遍历过的最新宽度相等,选择高度比前一种宽度大的，且是当前宽度中最小高度的元素
            } else {
                // 如果当前同宽度元素的高度比已添加的同宽度元素的高度大，直接跳过
                if (height > dp[maxCount - 1] && height > envelopes[prevIndex][1]) {
                    dp[maxCount++] = height;
                    continue;
                    // 如果当前同宽度元素的高度比已添加的同宽度元素的高度小，且大于前一个宽度元素的高度时
                } else if (height < envelopes[curIndex][1] && height > envelopes[prevIndex][1]) {
                    dp[maxCount] = height; // 替换，注意LIS长度不变
                    continue;
                    // 因为宽度没有改变，所以prevIndex和curIndex不需要改变
                }else if(height == envelopes[curIndex][1]) {
                    continue;
                }
                // 如果当前同宽度元素的高度比已添加的同宽度元素的高度小，但是小于前一个宽度元素的高度时
                // 二分查找并替换
                int index = binarySearch(dp, height, maxCount);
                dp[index] = height;
            }
        }
        return maxCount;
    }*/