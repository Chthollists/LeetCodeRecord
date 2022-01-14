package 简单题.简单模拟;

/**
 * @author Chthollists email:
 * @create 2022-01-09 14:22
 */
public class LC1629按键持续时间最大的键 {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        if (releaseTimes == null || releaseTimes.length == 0 ||
                keysPressed == null || keysPressed.length() == 0)
            return 0;
        if (releaseTimes.length != keysPressed.length()) return 0;
        int n = releaseTimes.length;
        int maxTime = releaseTimes[0];
        char maxKey = keysPressed.charAt(0);
        for (int i = 1; i < n; i++) {
            if (releaseTimes[i] - releaseTimes[i - 1] > maxTime ||
                    (releaseTimes[i] - releaseTimes[i - 1] == maxTime && keysPressed.charAt(i) > maxKey)) {
                maxTime = releaseTimes[i] - releaseTimes[i - 1];
                maxKey = keysPressed.charAt(i);
            }
        }
        return maxKey;
    }
}
