package 贪心;

/**
 * @author Chthollist email:
 * @create 2022-02-20 16:01
 */
public class LC1414和为K的最少斐波那契数 {
    public int findMinFibonacciNumbers(int k) {
        // 求出比 k 小的斐波那契数列
        int a = 1, b = 1;
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }
        // 求最小组合的数量
        int ans = 0;
        while (k != 0) {
            if (k >= b) {
                k -= b;
                ans++;
            }
            int c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
}
