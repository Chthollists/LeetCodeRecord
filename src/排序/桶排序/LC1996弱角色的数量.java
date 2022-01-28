package 排序.桶排序;

import java.util.Arrays;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-01-28 21:54
 */
public class LC1996弱角色的数量 {

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
        });
        int maxDefense = 0;
        int count = 0;
        for (int[] property : properties) {
            if (property[1] < maxDefense) {
                count++;
            } else {
                maxDefense = property[1];
            }
        }
        return count;
    }
}

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        // 求最大攻击力
        int maxAttack = 0;
        for (int[] property : properties) {
            maxAttack = Math.max(maxAttack, property[0]);
        }

        // bucket数组，先存储每个攻击力对应的最大防御力
        int[] bucket = new int[maxAttack + 1];
        for (int[] property : properties) {
            bucket[property[0]] = Math.max(bucket[property[0]], property[1]);
        }

        // bucket数组，存储大于当前攻击力的元素的最大防御力
        int maxDefence = 0; // 已遍历过的元素中的最大防御力
        for (int i = maxAttack; i >= 0; i--) {
            int prev = maxDefence;
            maxDefence = Math.max(maxDefence, bucket[i]);
            bucket[i] = prev;
        }

        int count = 0;
        // 遍历寻找弱角色
        // 小于最大攻击力，小于比当前元素攻击力大的元素的最大防御力
        for (int[] property : properties) {
            if (property[0] < maxAttack && property[1] < bucket[property[0]]) {
                count++;
            }
        }
        return count;
    }
}
