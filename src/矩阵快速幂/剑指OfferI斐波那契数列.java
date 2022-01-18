package 矩阵快速幂;

/**
 * @author Chthollist email:
 * @create 2022-01-18 14:27
 */
public class 剑指OfferI斐波那契数列 {
    public static void main(String[] args) {
        System.out.println(new 剑指OfferI斐波那契数列().fib(50));
    }

    final int MOD = (int) 1e9 + 7;

    // 1. 递归 & 记忆化
    private int[] memo = new int[101];

    public int fib1(int n) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fib1(n - 1) + fib1(n - 2);
        memo[n] %= MOD;
        return memo[n];
    }

    // 2. 状态机DP
    public int fib2(int n) {
        if(n <= 1) return n;
        int a = 0, b = 1;
        int res = 0;
        for(int i = 2; i < n+1; i++) {
            res = (a + b) % MOD;
            a = b;
            b = res;
        }
        return res;
    }

    // 3. 矩阵快速幂
    public int fib(int n) {
        if(n <= 1) return n;
        long[][] ini = new long[][] {
                {1},{0}
        };
        long[][] martix = new long[][] {
                {1,1},
                {1,0}
        };
        int x = n - 1;
        while (x > 0) {
            if((x & 1) == 1) ini = mul(martix, ini);
            martix = mul(martix, martix);
            x >>= 1;
        }
        return (int) ini[0][0] % MOD;
    }

    private long[][] mul(long[][] martixA, long[][] martixB) {
        int size = martixB.length;
        int row = martixA.length;
        int col = martixB[0].length;
        long[][] res = new long[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < size; k++) {
                    res[i][j] += martixA[i][k]*martixB[k][j];
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
    }
}
