package 字符串;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Chthollists email:
 * @create 2022-01-07 13:31
 */
public class LC1614括号的嵌套深度 {

    public int maxDepth(String s) {
        if(s == null || s.length() == 0) return 0;
        int curDepth = 0;
        int maxDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)  == '(') {
                curDepth++;
                maxDepth = Math.max(maxDepth,curDepth);
            }else if(s.charAt(i) == ')') {
                curDepth--;
            }
        }
        return maxDepth;
    }
    public int maxDepth1(String s) {
        Deque<Character> stack = new LinkedList<>();
        int maxDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            // 遇到左括号入栈
            if (s.charAt(i) == '(') {
                stack.add(s.charAt(i));
                maxDepth = Math.max(maxDepth, stack.size());
                // 遇到右括号则栈顶的左括号出栈
            } else if (s.charAt(i) == ')') {
                stack.removeLast();
            }
        }
        return maxDepth;
    }


}
