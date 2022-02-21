package 字符串.字符串反转;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:57
 */
public class LC2000反转单词前缀 {
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index >= 0) {
            char[] arr = word.toCharArray();
            int left = 0, right = index;
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
            word = new String(arr);
        }
        return word;
    }
}
