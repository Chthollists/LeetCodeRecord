package common;

/**
 * @author Chthollist email:
 * @create 2022-02-25 14:00
 */

import java.io.*;
import java.util.*;

public class CodeTest {

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            String str = s[n + 1];
            int k = Integer.parseInt(s[n + 2]);
            if(k > n || n == 0) return;
            List<String> bros = new ArrayList<>();
            Map<Character, Integer> map;
            for(int i = 1; i <= n; i++) {
                if(str.equals(s[i])) continue;
                if(s[i].length() != str.length()) continue;
                map = new HashMap<>();
                for(int j = 0; j < str.length(); j++) {
                    map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
                    map.put(s[i].charAt(j), map.getOrDefault(s[i].charAt(j), 0) - 1);
//                 if(map.get(str.charAt(j)) == 0) map.remove(str.charAt(j));
                }
                boolean flag = true;
                for(Character cur : map.keySet()) {
                    if(map.get(cur) != 0) {
                        flag = false;
                        break;
                    }
                }
                if(map.size() == 0) bros.add(s[i]);
            }
            bros.sort(Comparator.naturalOrder());
            if(k > bros.size()) return;
            System.out.println(bros.size());
            System.out.println(bros.get(k));
        }
}