package 图.最短路径问题;

import java.util.*;

/**
 * @author Chthollist email:
 * @create 2022-01-24 14:17
 */
public class LC2045到达目的地的第二短时间 {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = new HashMap<>(); // 邻接表
        // 构建图：邻接表
        for (int[] edge : edges) {
            List<Integer> nodes = graph.getOrDefault(edge[0], new ArrayList<>());
            List<Integer> nodes_ = graph.getOrDefault(edge[1], new ArrayList<>());
            nodes.add(edge[1]);
            graph.put(edge[0], nodes);
            nodes_.add(edge[0]);
            graph.put(edge[1], nodes_);
        }
        // 最短路与次短路
        int[] first = new int[n + 1]; // 到节点i的最短路，步数or时间
        int[] second = new int[n + 1]; // 到节点i的次短路，步数or时间
        Arrays.fill(first, Integer.MAX_VALUE);
        Arrays.fill(second, Integer.MAX_VALUE);
        int[] visited = new int[n + 1]; // 访问次数计数器
        visited[1]++;

        Deque<int[]> deque = new LinkedList<>(); // int[] {nodeId, costTime}
        deque.add(new int[]{1, 0}); // 起点出发
        while (!deque.isEmpty()) {
            // 剪枝1
            if(second[n] != Integer.MAX_VALUE) break;
            int[] cur = deque.poll();
            int node = cur[0], costTime = cur[1];
            int nextTime = costTime + time;
            List<Integer> nextNodeList = graph.get(node);
            for (int nextNode : nextNodeList) {
                if(visited[nextNode] >= 2) continue;
                if (nextTime < first[nextNode]) {
                    visited[nextNode]++;
                    first[nextNode] = nextTime;
                    deque.add(new int[]{nextNode, nextTime});
                } else if (nextTime > first[nextNode] && nextTime < second[nextNode]) {
                    visited[nextNode]++;
                    second[nextNode] = nextTime;
                    deque.add(new int[]{nextNode, nextTime});
                }
            }
        }
        // 红绿灯等待时间
        int waitTime = 0;
        for (int i = 1; i < second[n] / time; i++) {
            if ((((waitTime + i * time) / change) & 1) == 1) { // 奇数，红灯
                waitTime += change - (i * time + waitTime) % change;
            }
        }
        // System.out.println(Arrays.toString(first));
        // System.out.println(Arrays.toString(second));
        return second[n] + waitTime;
    }
}

class Solution {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // 处理无向图路径
        List<Integer>[] grap = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            grap[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            grap[edge[0]].add(edge[1]);
            grap[edge[1]].add(edge[0]);
        }
        // dist1[i] 记录到节点i的最短路径 dist2[i]记录到节点i的次最短路径
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);

        // 队列
        Queue<int[]> queue = new ArrayDeque<int[]>();
        // 第一个节点入队 <index,step>
        queue.offer(new int[]{1, 0});
        // 同样的配方不一样的面～
        while (dist2[n] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int cur = arr[0], len = arr[1];
            for (int next : grap[cur]) {
                // 更新最短和次最短的步数
                if (len + 1 < dist1[next]) {
                    dist1[next] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }// 次最短路径步数 并加入队列中
                else if (len + 1 > dist1[next] && len + 1 < dist2[next]) {
                    dist2[next] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }
            }
        }
        // 计算次最短路径的时间
        int result = 0;
        for (int i = 0; i < dist2[n]; i++) {
            // 需要加入等红绿灯的时间 由于红绿转换 所以循环是时间 2*change
            if (result % (2 * change) >= change) { //遇到红灯
                // 比如time等于3 change=5 这个时候result如果等于6 就需要等4分钟 （2*5）-6%10 = 10-6 = 4
                result = result + (2 * change - result % (2 * change));
            }
            // 加上路径的时间
            result = result + time;
        }
        return result;
    }
}