package 简单题.数学公式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollists email:
 * @create 2022-01-02 19:14
 */
public class LC390消除游戏 {
    public static void main(String[] args) {
//        int last = lastRemaining(9);
//        System.out.println(last);
    }

    public static int lastRemaining1(int n) {
        int res = 0;
        if (n == 1) return 1;
        List<Integer> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        boolean flag = true; // 从左到右
        // 删除的轮数等于n一直除2直到等于0
        while (n != 0) {
            if (flag) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.size() == 1) res = list.get(0);
                    list.remove(i);
                }
            } else {
                for (int i = list.size() - 1; i >= 0; i -= 2) {
                    if (list.size() == 1) res = list.get(0);
                    list.remove(i);
                }
            }
            flag = !flag;
            n /= 2;
        }
        return res;
    }


    public static int lastRemaining2(int n) {
        if (n == 1) return 1;
        int first = 1; // 初始的首元素
        int step = 1; // 初始步长
        boolean flag = true; // 从左到右
        while (n > 1) {
            // 奇数次删除、奇数个元素的偶数次删除
            if (flag || (n & 1) == 1) {
                first += step;
            }
            // 偶数次删除，无需变化
            n >>= 1;
            step <<= 1;
            flag = !flag;
        }
        return first;
    }

    public int lastRemaining3(int n) {
        int a1 = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }

    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }

}
