package 双指针.区间指针;

/**
 * @author Chthollist email:
 * @create 2022-02-21 20:57
 */
public class LC838推多米诺 {
    public static void main(String[] args) {
        new LC838推多米诺().pushDominoes(".L.R..R.R...L");
    }


    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() == 0) return "";
        dominoes = "L" + dominoes + "R";
        int n = dominoes.length();
        int left = 0;
        int right = left + 1;
        StringBuilder s = new StringBuilder();
        while (right < n) {
            if (dominoes.charAt(right) == '.') {
                right++;
                continue;
            }
            if (left != 0) s.append(dominoes.charAt(left));
            // 有"."站立的牌
            if (right - left > 1) {
                int count = right - left - 1; // . 的个数
                if (dominoes.charAt(left) == dominoes.charAt(right)) {
                    while (count > 0) {
                        s.append(dominoes.charAt(left));
                        count--;
                    }
                } else if (dominoes.charAt(left) == 'L' && dominoes.charAt(right) == 'R') {
                    while (count > 0) {
                        s.append(".");
                        count--;
                    }
                } else { // R ... L
                    for (int i = 0; i < count / 2; i++) s.append("R");
                    if ((count & 1) == 1) s.append(".");
                    for (int i = 0; i < count / 2; i++) s.append("L");
                }
            }
            left = right;
            right++;
        }
        return s.toString();
    }
}
