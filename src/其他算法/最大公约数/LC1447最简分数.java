package 其他算法.最大公约数;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:36
 */
public class LC1447最简分数 {

    public List<String> simplifiedFractions(int n) {
        List<String> list = new ArrayList<>();
        if(n < 2) return list;
        StringBuilder sb;
        for(int down = 2; down <= n; down++) {
            for(int up = 1; up < down; up++) {
                sb = new StringBuilder();
                if(gcd(down, up) == 1) {
                    sb.append(up).append("/").append(down);
                    list.add(sb.toString());
                }
            }
        }
        // Collections.sort(list);
        return list;
    }

    private int gcd(int num1, int num2) {
        if(num2 != 0) return gcd(num2, num1 % num2);
        else return num1;
    }


    public List<String> simplifiedFractions1(int n) {
        List<String> list = new ArrayList<>();
        Set<Double> set = new HashSet<>();
        if(n < 2) return list;
        StringBuilder sb;
        for(int down = 2; down <= n; down++) {
            for(int up = 1; up < down; up++) {
                sb = new StringBuilder();
                sb.append(up).append("/").append(down);
                if(set.contains((double)down / up)) continue;
                set.add((double)down / up);
                list.add(sb.toString());
            }
        }
        // Collections.sort(list);
        return list;
    }
}
