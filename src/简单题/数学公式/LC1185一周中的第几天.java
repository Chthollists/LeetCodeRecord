package 简单题.数学公式;

/**
 * @author Chthollists email:
 * @create 2022-01-03 10:59
 */
public class LC1185一周中的第几天 {


    public String dayOfTheWeek1(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 4; // 初始是星期四
        boolean isLeapYear = false;
        for (int i = 1971; i < year; i++) {
            isLeapYear = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
            days += isLeapYear ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            days += monthDays[i - 1];
        }
        isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        days += (isLeapYear && month > 2) ? 1 : 0; // 当前年是否是闰年
        days += day;
        return week[days % 7];
    }

    public String dayOfTheWeek(int day, int month, int year) {
        String week[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        int index = 0;
        //基姆拉尔森计算公式
        index = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400 + 1) % 7;
        return week[index];
    }

    public String dayOfTheWeek2(int day, int month, int year) {
        //注意开始是周日！
        String week[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month < 3) {
            month += 12;
            year--;
        }
        int index = 0;
        int c = year / 100;
        int y = year % 100;
        //蔡勒公式
        index = (c / 4 - 2 * c + y + y / 4 + 13 * (month + 1) / 5 + day - 1) % 7;
        return week[index < 0 ? index + 7 : index];
    }

}
