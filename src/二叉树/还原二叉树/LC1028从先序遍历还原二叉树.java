package 二叉树.还原二叉树;

import 二叉树.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Chthollists email:
 * @create 2022-01-15 16:27
 */
public class LC1028从先序遍历还原二叉树 {
    public static void main(String[] args) {
        String s = "1-2--3--4-5--6--7";
        new LC1028从先序遍历还原二叉树().recoverFromPreorder(s);

    }

    // 方法一：迭代、队列回溯
    public TreeNode recoverFromPreorder1(String traversal) {
        TreeNode root = null;
        if (traversal == null || traversal.length() == 0) return root;
        String[] vals = traversal.split("-");
        root = new TreeNode(Integer.parseInt(vals[0]));
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int depth = 1;
        // 构建二叉树的层序遍历输出
        for (int i = 1; i < vals.length; i++) {
            if ("".equals(vals[i])) {
                depth++; // 遇到空串相当于二叉树深度 + 1
            } else { // 数值
                TreeNode cur = new TreeNode(Integer.parseInt(vals[i]));
                // 深度与队列长度一致时，表示队列中放的节点路径到了 depth 深度层，直接连接到左节点
                if (depth == deque.size()) {
                    if (!deque.isEmpty()) {
                        deque.peek().left = cur;
                    }
                } else {
                    // 深度与队列长度不一致时，表示队列中放的节点路径超过了 depth 深度层，取出元素，直到长度相等后在连接到右节点
                    while (depth != deque.size()) deque.poll(); // 删除队列中的尾部元素
                    deque.peek().right = cur;
                }
                deque.addFirst(cur); // 当前节点加入队列头部
                depth = 1; // 重置深度
            }
        }
        return root;
    }

    // 方法二：DFS 递归
    int index = 0;
    public TreeNode recoverFromPreorder(String traversal) {
        if (traversal == null || traversal.length() == 0) return null;
        return dfs(traversal, 0);
    }

    private TreeNode dfs(String traversal, int depth) {
        int n = traversal.length();
        int curDepth = 0;
        while (index < n && traversal.charAt(index) == '-') {
            curDepth++;
            index++;
        }
        if(curDepth != depth) {
            index -= curDepth; // 如果当前元素所在深度curDepth与目标深度不相等，说明前一个节点没有子节点，当前节点是上一层的子节点
            return null;
        }
        int value = 0;
        while (index < n && Character.isDigit(traversal.charAt(index))) {
            value = value * 10 + (traversal.charAt(index) - '0');
            index++;
        }
        TreeNode cur = new TreeNode(value);
        cur.left = dfs(traversal, depth + 1);
        cur.right = dfs(traversal, depth + 1);
        return cur;
    }
}
