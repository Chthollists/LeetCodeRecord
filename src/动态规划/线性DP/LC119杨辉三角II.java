package 动态规划.线性DP;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-02-23 22:17
 */
public class LC119杨辉三角II {

    // 二维DP：每一行
    public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> list = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) list.add(1);
                else {
                    list.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
                }
            }
            lists.add(list);
        }
        return lists.get(rowIndex);
    }

    // 滚动数组优化：一维DP，前一行、当前行
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> cur = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) cur.add(1);
                else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return pre;
    }

    // 一个数组：动态更新
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            list.add(0);
            for (int j = i; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return list;
    }
}
