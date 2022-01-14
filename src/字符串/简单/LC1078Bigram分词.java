package 字符串.简单;

/**
 * @author Chthollists email:
 * @create 2021-12-29 21:03
 * 给出第一个词first 和第二个词second，考虑在某些文本text中可能以 "first second third" 形式出现的情况
 * 其中second紧随first出现，third紧随second出现。
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案
 */
public class LC1078Bigram分词 {

    public String[] findOcurrences(String text, String first, String second) {
        if (text == null || text.length() == 0)
            return new String[]{};

        String[] strs = text.split(" ");
        int n = strs.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n - 2; i++) {
            if (!strs[i].equals(first) || !strs[i + 1].equals(second))
                continue;
            sb.append(strs[i + 2]).append(" ");
        }

        if (sb == null || sb.length() == 0)
            return new String[]{};

        // System.out.println(sb.length());
        sb.deleteCharAt(sb.length() - 1);
        String[] res = sb.toString().split(" ");
        return res;
    }
}
