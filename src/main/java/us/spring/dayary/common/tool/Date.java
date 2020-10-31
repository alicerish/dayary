package us.spring.dayary.common.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {
    public static final int[] LAST_DAY = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //1970-1-1 00:00부터 현재까지 경과 된 밀리초 반환.
    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    //1970-1-1 00:00부터 현재까지 경과 된 밀리초(1585653589741)를 넘기면 ex)'2020-03-31 20:19:49.741' 반환.
    public static String format(long timeMillis, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);//"yyyy-MM-dd HH:mm:ss.SSS"
        return simpleDateFormat.format(timeMillis);
    }

    //ex)'2020-03-31 20:19:49.741'를 넘기면 1970-1-1 00:00부터 현재까지 경과 된 밀리초(1585653589741) 반환.
    public static long getTimeMillisForDateTimeString(String dateTimeString, String dateFormat) throws ParseException {//2019-02-02 01:29, yyyy-MM-dd HH:mm
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        java.util.Date date = simpleDateFormat.parse(dateTimeString);
        return date.getTime();
    }

    //요일
    public static int getDayOfWeek(int year, int month, int day) {
        int perYear = year - 1;
        int totDays = perYear * 365 + (perYear / 4 - perYear / 100 + perYear / 400);
        for (int i = 1; i < month; i++) {
            totDays += findLastDay(year, i);
        }
        totDays += day;
        return totDays % 7;
    }

    //마지막일
    public static int findLastDay(int year, int month) {
        return isLeapYear(year) && month == 2 ? 29 : LAST_DAY[month - 1];
    }

    //윤년
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}