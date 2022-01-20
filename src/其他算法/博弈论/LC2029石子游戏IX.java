package 其他算法.博弈论;

/**
 * @author Chthollist email:
 * @create 2022-01-20 15:18
 */
public class LC2029石子游戏IX {
    public static void main(String[] args) {
        new LC2029石子游戏IX().stoneGameIX(new int[]{2,2,3});
    }

    public boolean stoneGameIX(int[] stones) {
        if (stones == null || stones.length <= 1) return false;
        int countZero = 0;
        int countOne = 0;
        int countTwo = 0;
        // 统计三种类型的个数
        for (int stone : stones) {
            if (stone % 3 == 0) countZero++;
            else if (stone % 3 == 1) countOne++;
            else countTwo++;
        }
        if ((countZero & 1) == 0) {
            return countOne >= 1 && countTwo >= 1;
        }
        return Math.abs(countOne - countTwo) > 2;
    }

}
