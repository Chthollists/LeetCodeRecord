package 回溯.特殊问题;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollists email:
 * @create 2022-01-08 20:36
 */
public class LC89格雷编码 {
    public static void main(String[] args) {
        List<Integer> res = new LC89格雷编码().grayCode(3);
        System.out.println(res);
    }

    // 方法三：二进制规律+位运算
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0); // 第零项
        for (int i = 1; i < (1 << n); i++) {
            int prev = res.get(i - 1);
            // 1. 改变最右边的位元
            if (i % 2 == 1) {
                prev ^= 1;  // 0 ^ 1 = 1 and 1 ^ 1 = 0
                res.add(prev);
            } else { // 2. 改变右起第一个为1的位元的左边位
                int temp = prev;
                // 寻找右边起第一个为1的位元
                for (int j = 0; j < n; j++) {
                    if ((temp & 1) == 1) {
                        // 改变该位置的左边位置
                        prev = prev ^ (1 << (j + 1));
                        res.add(prev);
                        break;
                    }
                    // 向右移位
                    temp = temp >> 1;
                }
            }
        }
        return res;
    }

    // 方法二：动态规划
    public List<Integer> grayCode2(int n) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        for (int i = 0; i < n; i++) {
            int prefix = 1 << i; // 前缀为 1, 左移i位
            for (int j = dp.size() - 1; j >= 0 ; j--) {
                dp.add(dp.get(j) + prefix);
            }
        }
        return dp;
    }

    List<Integer> grayCodeList = new ArrayList<>();
    StringBuilder binaryCode = new StringBuilder();
    int[] left = new int[]{0, 1};
    int[] right = new int[]{1, 0};


    // 方法一：回溯
    public List<Integer> grayCode1(int n) {
        // int[] nums ： 该数组表示接下来去取的二进制数字
        backTracing(n, binaryCode, left);
        return grayCodeList;
    }

    private void backTracing(int n, StringBuilder binaryCode, int[] nums) {
        // 找到一个符合的结果并添加到结果集中
        if (binaryCode.length() == n) {
            // 二进制与十进制转换 Integer.valueOf(binaryCode.toString(), 2);
            int grayCode = Integer.parseInt(binaryCode.toString(), 2);
            grayCodeList.add(grayCode);
            return;
        }

        // 回溯{0，1}数组
        binaryCode.append(nums[0]);
        backTracing(n, binaryCode, left);
        binaryCode.deleteCharAt(binaryCode.length() - 1);
        // 回溯{1，0}数组
        binaryCode.append(nums[1]);
        backTracing(n, binaryCode, right);
        binaryCode.deleteCharAt(binaryCode.length() - 1);
    }

/*
     List<List<Integer>> binaryCodeList = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int[] left = new int[]{0, 1};
    int[] right = new int[]{1, 0};

    public List<Integer> grayCode(int n) {
        backTracing(n, list, left);
        // 二进制与十进制转换
        List<Integer> grayCodeList = new ArrayList<>();
        for(List<Integer> binaryCode:binaryCodeList) {
            int grayCode = 0;
            for(int i = 0; i < binaryCode.size(); i++) {
                grayCode = grayCode*2 + binaryCode.get(i);
            }
            grayCodeList.add(grayCode);
        }
        return grayCodeList;
    }

    private void backTracing(int n, List<Integer> list, int[] nums) {
        // 找到一个符合的结果并添加到结果集中
        if (list.size() == n) {
            binaryCodeList.add(new ArrayList<>(list));
            return;
        }

        // 回溯{0，1}数组
        list.add(nums[0]);
        backTracing(n, list, left);
        list.remove(list.size() - 1);

        // 回溯{1，0}数组
        list.add(nums[1]);
        backTracing(n, list, right);
        list.remove(list.size() - 1);
    }
 */
}
