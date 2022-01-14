package 字符串.括号问题;

import java.util.*;

/**
 * @author Chthollists email:
 * @create 2022-01-06 16:08
 */
public class LC71简化路径 {
    public static void main(String[] args) {
        String res = simplifyPath("/../I/./love//996/../you/");
        System.out.println(res);
        res = simplifyPath("/home/../../..");
        System.out.println(res);
        res = simplifyPath("/home///aa/");
        System.out.println(res);
        res = simplifyPath("/a/./b/../../c/");
        System.out.println(res);
    }

    public static String simplifyPath1(String path) {
        if (path == null || path.length() == 0 || path.charAt(0) != '/') return null;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> start = new Stack<>();
        Stack<Integer> end = new Stack<>();
        start.push(0);
        end.push(1);
        String[] strs = path.split("/");
//        System.out.println(Arrays.toString(strs));
        for (String str : strs) {
            if ("..".equals(str)) { // 处理 [..]
                if (!start.isEmpty()) {
                    sb.delete(start.pop(), end.pop());
                } else {
                    sb.delete(0, 1);
                }
            } else if (!".".equals(str) && !"".equals(str)) {  // 处理 [.]  [//]
                start.push(sb.length());
                sb.append("/").append(str);
                end.push(sb.length());
            }
        }
        return "".equals(sb.toString()) ? "/" : sb.toString();
    }

    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0 || path.charAt(0) != '/') return null;
        Deque<String> stack = new LinkedList<>();
        String[] strs = path.split("/");
        for (String str : strs) {
            if ("..".equals(str)) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else if (!".".equals(str) && !"".equals(str)) {
                stack.offerLast(str);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.removeFirst());
        }
        return "".equals(sb.toString()) ? "/" : sb.toString();
    }


    public String simplifyPath2(String path) {
        // 存入目录的List
        List<String> temp = new ArrayList<>(); //按顺序存所有目录名
        int len = path.length();
        for (int i = 0; i < len; ) {
            if (path.charAt(i) == '/') i++;
            else {
                // 记录当前位置
                int now_index = i;
                while (i < len && path.charAt(i) != '/') {
                    i++;
                }
                // 截取两个/ 之间的字符串
                String s = path.substring(now_index, i);
                if (s.equals("..") && !temp.isEmpty())
                    //返回上一级，删除最后一个目录
                    temp.remove(temp.size() - 1);
                else if (!s.equals(".") && !s.equals(".."))
                    //存储合法目录名
                    temp.add(s);
            }
        }
        // 如果为空 直接返回/
        if (temp.isEmpty())
            return "/"; //仅含根目录
        StringBuilder res = new StringBuilder();
        for (String s : temp)
            res.append("/").append(s);
        return res.toString();
    }
}
