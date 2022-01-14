package 贪心;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Chthollists email:
 * @create 2021-12-31 15:20
 */
public class LC846一手顺子 {

    public static void main(String[] args) {
        int[] hand = new int[]{1, 2, 3, 2, 3, 4, 6, 7, 8};
        int groupSize = 3;
        boolean res = isNStraightHand2(hand, groupSize);
        System.out.println(res);
    }

    // 方法一：哈希计数 + 排序 + 贪心 : O(nlogn)
    public static boolean isNStraightHand1(int[] hand, int groupSize) {
        // 边界条件判断
        if (hand == null || hand.length == 0) return false;
        int n = hand.length;
        if (n % groupSize != 0) return false;
        // 排序
        Arrays.sort(hand);
/*        // 哈希计数
        int[] hash = new int[hand[n - 1] + 1 - hand[0] + 1 + groupSize];
        for (int num : hand) {
            hash[num]++;
        }
        // 搜索顺子
        for (int num : hand) {
            if (hash[num] == 0) continue;
            // 找到一组顺子，并将计数减一
            for (int i = 0; i < groupSize; i++) {
                if (hash[num + i] <= 0) return false;
                hash[num + i]--;
            }
        }*/
        // 哈希计数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 搜索顺子
        for (int num : hand) {
            if (map.containsKey(num)) {
                int val = map.get(num);
                for (int i = 0; i < groupSize; i++) {
                    if (!map.containsKey(num + i)) {
                        return false;
                    }
                    map.put(num + i, map.get(num + i) - val);
                    if (map.get(num + i) < 0) {
                        return false;
                    }
                    if (map.get(num + i) == 0) {
                        map.remove(num + i);
                    }
                }
            }
        }
        return true;
    }

    // 方法二：哈希计数 + 小顶堆(优先队列) + 贪心: O(nlogn)
    public static boolean isNStraightHand2(int[] hand, int groupSize) {
        // 边界条件判断
        if (hand == null || hand.length == 0) return false;
        int n = hand.length;
        if (n % groupSize != 0) return false;

        // 哈希计数
        Map<Integer, Integer> map = new HashMap<>();
        // 小顶堆：优先队列动态维护最小元素
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            heap.add(num);
        }
        // System.out.println(Arrays.toString(heap.toArray()));

        // 搜索顺子
        while (!heap.isEmpty()) {
            int num = heap.poll();
            if (map.containsKey(num)) {
                for (int i = 0; i < groupSize; i++) {
                    if (!map.containsKey(num + i)) return false;
                    // 更新个数
                    map.put(num + i, map.get(num + i) - 1);
                    if (map.get(num + i) == 0) {
                        map.remove(num + i);
                    } else if (map.get(num + i) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 方法三：双指针: O(nlogn)
    public static boolean isNStraightHand3(int[] hand, int groupSize) {
        // 边界条件判断
        if (hand == null || hand.length == 0) return false;
        int n = hand.length;
        if (n % groupSize != 0) return false;
        // 排序
        Arrays.sort(hand);
        for (int i = 0; i < n; i++) {
            if(hand[i] == -1) continue;
            int len = 1; // 表示一个顺子的个数是否达到groupSize
            for (int j = i+1; j < n && len != groupSize; j++) {
                if(hand[j] == hand[i] + len) { // 找到一个满足顺子的元素
                    len++;
                    hand[j] = -1; // 标记
                }
            }
            if(len != groupSize) return false; // len不等于groupSize表示没有凑齐一个顺子
        }
        return true;
    }

    // 方法四：哈希计数 + 小顶堆(优先队列) + 贪心: O(nlogn)
    public static boolean isNStraightHand4(int[] hand, int groupSize) {
        // 边界条件判断
        if (hand == null || hand.length == 0) return false;
        int n = hand.length;
        if (n % groupSize != 0) return false;
        // 排序
        Arrays.sort(hand);
        // 搜索顺子
        for (int i = 0; i < n; i++) {
            boolean flag = true; // 表示是否可以组成顺子
            int minVal = hand[i];
            if (minVal != -1) {
                hand[i] = -1;  // 标记当前元素是否使用过
                flag = dfs(minVal + 1, hand, i + 1, groupSize, 1);
            }
            if (!flag) return false;
        }
        return true;
    }

    // DFS：搜索以minVal开头是否可以组成顺子
    private static boolean dfs(int curVal, int[] hand, int start, int groupSize, int len) {
        if (len == groupSize) {
            return true;
        }
        while (start < hand.length) {
            if (hand[start] != -1) {
                if (hand[start] == curVal) {
                    hand[start] = -1; // 使用过后就标记
                    return dfs(curVal + 1, hand, start + 1, groupSize, len + 1);
                }
            }
            start++;
        }
        return false;
    }
}
