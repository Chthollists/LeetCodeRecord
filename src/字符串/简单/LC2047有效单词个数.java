package 字符串.简单;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-01-27 21:07
 */
public class LC2047有效单词个数 {

    public int countValidWords(String sentence) {
        if (sentence == null || sentence.length() == 0) return 0;
        int n = sentence.length();
        int count = 0;
        int left = 0, right = 0;
        while (true) {
            // 删除开头的空格
            while (left < n && sentence.charAt(left) == ' ') left++;
            if (left >= n) break;
            right = left + 1;
            // 找到单词
            while (right < n && sentence.charAt(right) != ' ') right++;
            if (isValid(sentence.substring(left, right))) count++;
            left = right + 1; // 去寻找下一个单词
        }
        return count;
    }

    private boolean isValid(String word) {
        int n = word.length();
        int hyphenCount = 0, markCount = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(word.charAt(i))) return false;
            else if (word.charAt(i) == '-') {
                hyphenCount++;
                if (hyphenCount > 1 || i == 0 || i == n - 1
                        || !Character.isLetter(word.charAt(i - 1))
                        || !Character.isLetter(word.charAt(i + 1))) {
                    return false;
                }
            } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
                markCount++;
                if (markCount > 1) return false;
                if (i != n - 1) return false;
            }
        }
        return true;
    }
}