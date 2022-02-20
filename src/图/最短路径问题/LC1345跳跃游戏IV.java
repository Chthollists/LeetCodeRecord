package 图.最短路径问题;

import java.util.*;

/**
 * @author Chthollist email:
 * @create 2022-01-21 14:25
 */
public class LC1345跳跃游戏IV {

    // 方法1 ： 单向BFS
    public int minJumps1(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int n = arr.length;
        if (n == 1) return 0;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        // 哈希表存 value 和 index
        for (int i = 0; i < n; i++) {
            List<Integer> list = indexMap.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            indexMap.put(arr[i], list);
        }
        boolean[] visited = new boolean[n];
        Deque<int[]> deque = new LinkedList<>(); // int[] {index, step}
        deque.add(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] temp = deque.poll();
            int index = temp[0];
            int step = temp[1];
            // 搜到了末尾
            if (index == n - 1) return step;
            // 前后跳
            if (index + 1 < n && !visited[index + 1]) {
                deque.add(new int[]{index + 1, step + 1});
                visited[index + 1] = true;
            }
            if (index - 1 >= 0 && !visited[index - 1]) {
                deque.add(new int[]{index - 1, step + 1});
                visited[index - 1] = true;
            }
            // 等值跳
            if (indexMap.containsKey(arr[index])) {
                List<Integer> indexList = indexMap.get(arr[index]);
                for (int idx : indexList) {
                    if(!visited[idx]) {
                        deque.add(new int[]{idx, step + 1});
                        visited[idx] = true;
                    }
                }
            }
            // 注意从哈希表中删除访问过的元素
            indexMap.remove(arr[index]);
        }
        return -1;
    }

    // 方法2 ： 双向BFS
    public int minJumps(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int n = arr.length;
        if (n == 1) return 0;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        // 哈希表存 value 和 index
        for (int i = 0; i < n; i++) {
            List<Integer> list = indexMap.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            indexMap.put(arr[i], list);
        }

        int[] step1 = new int[n];
        int[] step2 = new int[n];
        Arrays.fill(step1, -1);
        Arrays.fill(step2, -1);

        Deque<Integer> deque1 = new LinkedList<>(); //  index
        Deque<Integer> deque2 = new LinkedList<>();
        deque1.add(0);
        step1[0] = 0;
        deque2.add(n - 1);
        step2[n - 1] = 0;

        while (!deque1.isEmpty() && !deque2.isEmpty()) {
            int step = -1;
            if (deque1.size() <= deque2.size()) {
                step = update(deque1, deque2, step1, step2, indexMap, arr);
            } else {
                step = update(deque2, deque1, step2, step1, indexMap, arr);
            }
            if (step != -1) return step;
        }
        return -1;
    }

    private int update(Deque<Integer> deque1, Deque<Integer> deque2, int[] step1, int[] step2, Map<Integer, List<Integer>> indexMap, int[] arr) {
        int size = deque1.size();
        int n = arr.length;
        while (size > 0) {
            int index = deque1.poll();
            int step = step1[index];
            // 前后跳
            if (index + 1 < n) {
                if (step2[index + 1] != -1) return step + 1 + step2[index + 1];
                if (step1[index + 1] == -1) {
                    deque1.add(index + 1);
                    step1[index + 1] = step + 1;
                }
            }
            if (index - 1 >= 0) {
                if (step2[index - 1] != -1) return step + 1 + step2[index - 1];
                if (step1[index - 1] == -1) {
                    deque1.add(index - 1);
                    step1[index - 1] = step + 1;
                }
            }
            // 等值跳
            if (indexMap.containsKey(arr[index])) {
                List<Integer> indexList = indexMap.get(arr[index]);
                for (int idx : indexList) {
                    if (step2[idx] != -1) return step + 1 + step2[idx];
                    if (step1[idx] == -1) {
                        deque1.add(idx);
                        step1[idx] = step + 1;
                    }
                }
            }
            // 注意从哈希表中删除访问过的元素
            indexMap.remove(arr[index]);
            size--;
        }
        return -1;
    }


}
