package 设计题;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author Chthollist email:
 * @create 2022-01-23 13:50
 */
// StockPrice
public class LC2034股票价格波动 {
    public static void main(String[] args) {
        LC2034股票价格波动 lc2034 = new LC2034股票价格波动();
        lc2034.update(1,10);
        lc2034.update(2,5);
        System.out.println(lc2034.maximum());
        lc2034.update(1,5);
        System.out.println(lc2034.maximum());
    }

    int maxTimeStamp;
    Map<Integer, Integer> timeStampMap;
    TreeMap<Integer, Integer> priceMap;

    public LC2034股票价格波动() {
        maxTimeStamp = 0;
        timeStampMap = new HashMap<>();
        priceMap = new TreeMap<>();

    }

    public void update(int timestamp, int price) {
        maxTimeStamp = Math.max(maxTimeStamp, timestamp);
        int oldPrice = timeStampMap.getOrDefault(timestamp, -1);
        timeStampMap.put(timestamp, price);
        // 更新 TreeMap
        if (oldPrice != -1) {
            priceMap.put(oldPrice, priceMap.get(oldPrice) - 1);
            if (priceMap.get(oldPrice) == 0) priceMap.remove(oldPrice);
        }
        priceMap.put(price, priceMap.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return timeStampMap.get(maxTimeStamp);
    }

    public int maximum() {
        return priceMap.lastKey();
    }

    public int minimum() {
        return priceMap.firstKey();
    }

}


class StockPrice {
    int maxTimeStamp;
    Map<Integer, Integer> timeStampMap;
    PriorityQueue<int[]> maxDeap;
    PriorityQueue<int[]> minDeap;

    public StockPrice() {
        maxTimeStamp = 0;
        timeStampMap = new HashMap<>();
        minDeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        maxDeap = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
    }

    public void update(int timestamp, int price) {
        maxTimeStamp = Math.max(maxTimeStamp, timestamp);
        timeStampMap.put(timestamp, price);
        maxDeap.add(new int[]{price, timestamp});
        minDeap.add(new int[]{price, timestamp});
    }

    public int current() {
        return timeStampMap.get(maxTimeStamp);
    }

    public int maximum() {
        while (!maxDeap.isEmpty()) {
            int price = maxDeap.peek()[0];
            int timeStamp = maxDeap.peek()[1];
            if(timeStampMap.get(timeStamp) == price) return price;
            maxDeap.poll();
        }
        return -1;
    }

    public int minimum() {
        while (!minDeap.isEmpty()) {
            int price = minDeap.peek()[0];
            int timeStamp = minDeap.peek()[1];
            if(timeStampMap.get(timeStamp) == price) return price;
            minDeap.poll();
        }
        return -1;
    }

}