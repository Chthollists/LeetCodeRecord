package 排序.简单;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollist email:
 * @create 2022-02-20 15:12
 */
public class LC969煎饼排序 {
    public static void main(String[] args) {
        System.out.println(new LC969煎饼排序().pancakeSort(new int[]{3, 1, 2, 4}));
    }

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        if(arr == null || arr.length == 0) return list;
        int n = arr.length;
        for(int i = n; i > 1; i--) {
            int maxIdx = 0; // k
            for(int j = 1; j < i; j++){
                if(arr[j] > arr[maxIdx]) maxIdx = j;
            }
            if(maxIdx == i - 1) continue;
            reverse(arr, maxIdx + 1);
            list.add(maxIdx + 1);
            reverse(arr, i);
            list.add(i);
        }
        return list;
    }


    private void reverse(int[] arr, int index) {
        for(int i = 0; i < index  / 2; i++) {
            arr[i] = arr[i]^arr[index - i - 1];
            arr[index - i - 1] = arr[index - i - 1]^arr[i];
            arr[i] = arr[i]^arr[index - i - 1];
        }
    }
}
