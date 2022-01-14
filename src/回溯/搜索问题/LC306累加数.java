package 回溯.搜索问题;

/**
 * @author Chthollists email:
 * @create 2022-01-10 16:27
 */
public class LC306累加数 {
    public static void main(String[] args) {
        System.out.println(new LC306累加数().isAdditiveNumber("00000")); // true
        System.out.println(new LC306累加数().isAdditiveNumber("012345")); // false
        System.out.println(new LC306累加数().isAdditiveNumber("0121224")); // true
        System.out.println(new LC306累加数().isAdditiveNumber("123546")); // false
        System.out.println(new LC306累加数().isAdditiveNumber("1235813")); // true
        System.out.println(new LC306累加数().isAdditiveNumber("10235813")); // false
        System.out.println(new LC306累加数().isAdditiveNumber("1002102104206"));  // true
        System.out.println(new LC306累加数().isAdditiveNumber("101"));  // true
    }


    // 方法2: 非递归
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        String first = "", second = "", third = "";
        int n = num.length();
        // 简化分类讨论
        for (int i = 1; i <= n / 2; i++) {
            // 1. 对应0xxxx,且非0000情况
            if (first.length() > 1 && first.charAt(0) == '0') {
                return false;
            }
            for (int j = i + 1; j <= Math.min(n - i, n / 2 + i / 2) + 1; j++) {
                first = num.substring(0, i);
                second = num.substring(i, j);
                // 2. 对应N0xxxx
                if (second.length() > 1 && second.charAt(0) == '0') break;
                // 3. 其他情况，包括 0000 类型
                third = getTarget(first, second);
//                String rest = num.substring(j); // 剩下的序列
                StringBuilder rest = new StringBuilder(num.substring(j)); // 剩下的序列
                while (rest.indexOf(third) != -1) { // rest.startsWith(third)
                    first = second;
                    second = third;
                    third = getTarget(first, second);
//                    rest = rest.substring(second.length());
                    rest.delete(0, second.length());
                    if (rest.length() == 0) return true;
                }
            }
        }
        return false;
    }

    // 方法1优化 : DFS+字符串相加，不对前两个数是否为0进行分类讨论
    public boolean isAdditiveNumber2(String num) {
        if (num == null || num.length() < 3) return false;
        String first = "", second = "", third = "";
        int n = num.length();
        // 简化分类讨论
        for (int i = 1; i <= n / 2; i++) {
            // 1. 对应0xxxx,且非0000情况
            if (first.length() > 1 && first.charAt(0) == '0') {
                return false;
            }
            for (int j = i + 1; j <= Math.min(n - i, n / 2 + i / 2) + 1; j++) {
                first = num.substring(0, i);
                second = num.substring(i, j);
                // 2. 对应N0xxxx
                if (second.length() > 1 && second.charAt(0) == '0') break;
                // 3. 其他情况，包括 0000 类型
                third = getTarget(first, second);
                if (dfs(second, third, num, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 方法一：DFS + 字符串实现高精度加法
    public boolean isAdditiveNumber1(String num) {
        if (num == null || num.length() < 3) return false;
        String first = "", second = "", third = "";
        int n = num.length();
        boolean zeroFlag = false;
        // 情况一：开头是 00xxxx的情况
        if (num.charAt(0) == '0' && num.charAt(1) == '0') {
            zeroFlag = true;
            for (int i = 2; i < n; i++) {
                if (num.charAt(i) != '0') return false;
            }
            // 情况二：开头是 0xxxxx的情况
        } else if (num.charAt(0) == '0') {
            first = "0";
            for (int i = 2; i < n; i++) {
                second = num.substring(1, i);
                third = second;
//                System.out.println(third);
                if (dfs(second, third, num, i)) {
                    return true;
                }
            }
        } else {
            // 情况三：开头是 Nxxxxx的情况
            for (int i = 1; i < n; i++) {
                // j < n 优化 为 j <= n - i，因为最后如果存在第三个数字的话，至少要和第一个数的位数一样
                for (int j = i + 1; j <= n - i; j++) { // j < n
                    first = num.substring(0, i);
                    second = num.substring(i, j);
                    // 剪枝：如果Second的开头存在0，则不符合题意，直接跳出本次循环,进入外层循环，将该0作为first的一部分
                    if (second.length() > 1 && second.charAt(0) == '0') break;
//                    System.out.println(first + "  " + second);
                    third = getTarget(first, second);
                    if (dfs(second, third, num, j)) {
                        return true;
                    }
                }

            }
        }
        return zeroFlag;
    }

    private boolean dfs(String second, String third, String num, int index) {
        // DFS搜索到了字符串的结尾，自然结束，即是累加数 true
        if (index == num.length()) return true;
        int len = third.length();
        // 下一次搜索需要找到的数的长度超出字符串剩余的长度，一定搜索不到，false
        if (index + len > num.length()) return false;
        // 取出字符串中与要查找的third长度相等的数
        String subStr = num.substring(index, index + len);
        // 匹配就继续搜索下一轮，否则直接返回false
        if (third.equals(subStr)) {
            return dfs(third, getTarget(second, third), num, index + len);
        } else {
            return false;
        }
    }

    private String getTarget(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) carry += num1.charAt(i--) - '0';
            if (j >= 0) carry += num2.charAt(j--) - '0';
            sb.append(carry % 10);
            carry /= 10;
        }
        String res = sb.reverse().toString();
//        System.out.println(res);
        return res;
    }

}
