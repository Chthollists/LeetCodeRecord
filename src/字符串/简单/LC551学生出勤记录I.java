package 字符串.简单;

/**
 * @author Chthollist email:
 * @create 2022-01-18 14:45
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录
 */
public class LC551学生出勤记录I {

    public boolean checkRecord(String s) {
        return !s.contains("LLL") && s.indexOf("A") == s.lastIndexOf("A");
    }

    public boolean checkRecord1(String s) {
        if (s == null || s.length() == 0) return false;
        int n = s.length();
        int absent = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'A') {
                absent++;
                if (absent >= 2) return false;
            }else if (s.charAt(i) == 'L') {
                int j = i + 1;
                while (j < n && s.charAt(j) == 'L') {
                    if(j - i + 1 >= 3) return false;
                    j++;
                }
                i = j - 1; // 跳过已经判断的字符
            }
        }
        return true;
    }

}
