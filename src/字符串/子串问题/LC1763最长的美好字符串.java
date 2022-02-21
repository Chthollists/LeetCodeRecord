package 字符串.子串问题;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:55
 */
public class LC1763最长的美好字符串 {
    public String longestNiceSubstring(String s) {
        if (s.length() < 2)
            return "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c <= 'Z' && !s.contains(String.valueOf((char) ((int) c + 32))))
                    || (c >= 'a' && !s.contains(String.valueOf((char) ((int) c - 32))))) {
                String s1 = longestNiceSubstring(s.substring(0, i));
                String s2 = longestNiceSubstring(s.substring(i + 1));
                if (s1.length() >= s2.length())
                    return s1;
                return s2;
            }
        }
        return s;
    }
}
