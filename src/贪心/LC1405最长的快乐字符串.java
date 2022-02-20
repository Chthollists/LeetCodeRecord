package 贪心;

import java.util.*;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-07 12:18
 */
public class LC1405最长的快乐字符串 {
    public static void main(String[] args) {
        String s = new LC1405最长的快乐字符串().longestDiverseString(7, 1, 1);
        System.out.println(s);
    }
    public String longestDiverseString1(int a, int b, int c) {
        if(a + b + c == 0) return "";
        StringBuilder sb = new StringBuilder();
        int[][] pair = new int[][]{{'a', a}, {'b', b}, {'c', c}};
        while (true) {
            Arrays.sort(pair, (o1, o2) -> o2[1] - o1[1]);
            int[] max = pair[0];
            if(max[1] == 0) break;
            int n = sb.length();
            if(n >= 2 && sb.charAt(n - 1) == max[0] && sb.charAt(n - 2) == max[0]) {
                if(pair[1][1] == 0) break;
                sb.append((char) pair[1][0]);
                pair[1][1]--;
            }else {
                sb.append((char) max[0]);
                max[1]--;
            }
        }
        return sb.toString();
    }

    public String longestDiverseString(int a, int b, int c) {
        if(a + b + c == 0) return "";
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1,o2) -> o2[1] - o1[1]);
        maxHeap.add(new int[]{'a', a});
        maxHeap.add(new int[]{'b', b});
        maxHeap.add(new int[]{'c', c});
        while (!maxHeap.isEmpty()) {
            int n = sb.length();
            int[] max = maxHeap.poll();
            if(max[1] == 0) break;
            if(n >= 2 && sb.charAt(n - 1) == max[0] && sb.charAt(n - 2) == max[0]) {
                int[] mid = maxHeap.poll();
                if(mid[1] == 0) break;
                sb.append((char) mid[0]);
                maxHeap.add(new int[]{mid[0], mid[1] - 1});
                maxHeap.add(max);
            }else {
                sb.append((char) max[0]);
                maxHeap.add(new int[]{max[0], max[1] - 1});
            }
        }
        return sb.toString();
    }

 /*   public String longestDiverseString(int a, int b, int c) {
        if(a + b + c == 0) return "";
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer,Character> map = new TreeMap<>((o1, o2) -> o2 - o1);
        map.put(a, 'a');
        map.put(b, 'b');
        map.put(c, 'c');
        while (true) {
            int n = sb.length();
            Map.Entry<Integer, Character> max = map.firstEntry();
            if(max.getKey() == 0) break;
            if(n >= 2 && sb.charAt(n - 1) == max.getValue() && sb.charAt(n - 2) == max.getValue()) {
                map.remove(max.getKey(),max.getValue());
                Map.Entry<Integer, Character> mid = map.firstEntry();
                if(mid.getKey() == 0) break;
                map.remove(mid.getKey(),mid.getValue());
                sb.append((char) mid.getValue());
                map.put(mid.getKey() - 1, mid.getValue());
                map.put(max.getKey(), max.getValue());
            }else {
                sb.append((char) max.getValue());
                map.remove(max.getKey(), max.getValue());
                map.put(max.getKey() - 1, max.getValue());
            }
        }
        return sb.toString();
    }*/
}
