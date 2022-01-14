package 二叉树.搜索;


import 二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 如果一棵二叉树满足下述几个条件，则可以称为**奇偶树**：
 *   二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推
 *   偶数下标 层上的所有节点的值都是**奇整数**，从左到右按顺序**严格递增**
 *   奇数下标 层上的所有节点的值都是**偶整数**，从左到右按顺序**严格递减**
 * 给你二叉树的根节点，如果二叉树为奇偶树 ，则返回 true ，否则返回 false
 */
public class LC1609奇偶树 {


    // 方法一：BFS
    public boolean isEvenOddTreeBFS(TreeNode root) {
        if(root == null) return false;
        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean flag = true; // 标记层数的奇偶性，默认从0(偶数-true)开始, 偶数层要求数字为奇数且递增，奇数层要求数字为偶数且递减
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            int prev = flag ? 0 : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                int cur = node.val;
                // 偶数层
                if(flag && ((cur & 1) == 0 || cur <= prev)) return false;
                // 奇数层
                if(!flag && ((cur & 1) == 1 || cur >=prev)) return false;
                prev = cur;
                if(node.left != null) deque.addLast(node.left);
                if(node.right != null) deque.addLast(node.right);
            }
            flag = !flag;
        }
        return true;
    }


    // 方法二：DFS
    Map<Integer, Integer> map = new HashMap<>();

    public boolean isEvenOddTreeDFS(TreeNode root) {
        return dfs(root, 0);
    }

    private boolean dfs(TreeNode root, int level) {
        if (root == null) return false;
        boolean flag = (level & 1) == 0; // 层数的奇偶性
        int prev = map.getOrDefault(level, flag ? 0 : Integer.MAX_VALUE); // 从Map里面获取上次的节点值
        int cur = root.val;
        // 偶数层
        if (flag && ((cur & 1) == 0 || cur <= prev)) return false;
        // 奇数层
        if (!flag && ((cur & 1) == 1 || cur >= prev)) return false;
        map.put(level, cur);
        if (root.left != null && !dfs(root.left, level + 1)) return false;
        if (root.right != null && !dfs(root.right, level + 1)) return false;
        return true;
    }


}
