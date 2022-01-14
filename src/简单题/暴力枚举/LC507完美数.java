package 简单题.暴力枚举;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollists email:
 * @create 2021-12-31 14:16
 * * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」
 * * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 * * 注意同样值的数字不可以多次使用
 */
public class LC507完美数 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> perfectNumber = printPerfectNumber(n);
        System.out.println(perfectNumber);
    }
    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;
        int sum = 1;
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) sum += num / i;
            }
        }
        return sum == num;
    }

    // 打印n以内的完美数
    public static List<Integer> printPerfectNumber(int n) {
        List<Integer> res = new ArrayList<>();
        if(n <= 0) return res;

        for (int num = 2; num <= n; num++) {
            int sum = 1;
            for (int i = 2; i <= num / i; i++) {
                if (num % i == 0) {
                    sum += i;
                    if (i * i != num) sum += num / i;
                }
            }
            if(sum == num) res.add(num);
        }
        return res;
    }
}
