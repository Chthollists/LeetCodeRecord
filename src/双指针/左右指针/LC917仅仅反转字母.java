package 双指针.左右指针;

/**
 * @author Chthollist email:
 * @create 2022-02-23 12:51
 */
public class LC917仅仅反转字母 {
    public String reverseOnlyLetters(String s) {
        if(s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        int n = s.length();
        int l = 0, r = n - 1;
        while(l < r) {
            while(!Character.isLetter(chars[l])) l++;
            while(!Character.isLetter(chars[r])) r--;
            swap(chars, l, r);
            l++;
            r--;
        }
        return new String(chars);
    }

    private void swap(char[] chars, int l, int r) {
        char temp = chars[l];
        chars[l] = chars[r];
        chars[r] = temp;
    }
}
