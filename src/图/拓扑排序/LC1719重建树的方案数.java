package 图.拓扑排序;

import com.sun.javafx.geom.Edge;

import java.util.*;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-16 13:47
 */
public class LC1719重建树的方案数 {
    public static void main(String[] args) {
        System.out.println(new LC1719重建树的方案数()
                .checkWays(new int[][]{{1, 2}, {2, 3}}));
    }

    int N = 505;
    int[] count = new int[N]; // 祖先集合个数
    int[] parent = new int[N]; // 父节点
    boolean[] visited = new boolean[N];

    public int checkWays(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return -1;
        // 记录节点个数
        Set<Integer> set = new HashSet<>();
        // 邻接矩阵 : 祖先子集
        List<List<Integer>> edges = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }
        // 各个节点的祖先子集
        for (int[] pair : pairs) {
            int x = pair[0], y = pair[1];
            count[x]++;
            count[y]++;
            set.add(x);
            set.add(y);
            edges.get(x).add(y);
            edges.get(y).add(x);
        }

        int n = set.size(), m = pairs.length;
        if (m < n - 1) return 0;
        if (m == n * (n - 1) / 2) return 2;
        List<Integer> nodes = new ArrayList<>(set);
        nodes.sort((o1, o2) -> count[o2] - count[o1]);
        // 根节点判断
        if (count[nodes.get(0)] != n - 1) return 0;
        int type = 1;
        for (int[] pair : pairs) {
            parent[pair[0]] = nodes.get(0);
            parent[pair[1]] = nodes.get(0);
        }
        // 其他节点
        for (int i = 0; i < n; i++) {
            int curNode = nodes.get(i); // 当前节点
            List<Integer> adjNodes = edges.get(curNode);
            for (int adjNode : adjNodes) {
                // 两个父子节点的祖先子集个数相等
                if (count[curNode] == count[adjNode]) type = 2;
                if (!visited[adjNode]) {
                    // 父节点不同时，无法构造树
                    if (parent[curNode] != parent[adjNode]) return 0;
                    parent[adjNode] = curNode;
                }
            }
            visited[curNode] = true;
        }
        return type;
    }


    public int checkWays1(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return -1;
        // 邻接矩阵 : 各个节点的祖先子集
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] pair : pairs) {
            map.putIfAbsent(pair[0], new HashSet<>());
            map.get(pair[0]).add(pair[1]);
            map.putIfAbsent(pair[1], new HashSet<>());
            map.get(pair[1]).add(pair[0]);
        }
        int n = map.size();
        Set<Map.Entry<Integer, Set<Integer>>> entries = map.entrySet();
        // 根节点必须有 n - 1 个子节点
        int root = -1;
        for (Map.Entry<Integer, Set<Integer>> entry : entries) {
            int cur = entry.getKey();
            Set<Integer> nodes = entry.getValue();
            if (nodes.size() == n - 1) root = cur;
        }
        if (root == -1) return 0;
        int type = 1;
        // 其他节点：父子判断
        for (Map.Entry<Integer, Set<Integer>> entry : entries) {
            int cur = entry.getKey();
            if (cur == root) continue;
            Set<Integer> nodes = entry.getValue();
            int curSize = nodes.size();
            int parent = -1, parentSize = Integer.MAX_VALUE;
            // 查找当前节点的父节点，查找不到表示无法构成树
            for (int node : nodes) {
                int nodeSize = map.get(node).size();
                if (nodeSize >= curSize && nodeSize < parentSize) {
                    parent = node;
                    parentSize = nodeSize;
                }
            }
            if (parent == -1) return 0;
            // 父节点的子祖先子集一定包含子节点的祖先子集，否则无法构成树
            for (int node : nodes) {
                if (node == parent) continue;
                if (!map.get(parent).contains(node)) return 0;
            }
            // 判断是否存在多种重建方法，条件是两个父子节点的祖先子集相等
            if (parentSize == curSize) type = 2;
        }
        return type;
    }
}
