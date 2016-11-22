package com.example.administrator.schedule.SignReward.Utils;


import com.example.administrator.schedule.SignReward.Data.DateRange;

import java.util.Calendar;

/**
 * Created by nyq on 2016/11/20.
 */

public class CalendarUtils {



    public static int DateToDatePosition(int year, int month) {
        return (year - DateRange.getDateRange().getMinYear()) * 12 + month;
    }

    public static int[] DatePositionToDate(int datePosition) {
        DateRange dateRange = DateRange.getDateRange();
        int minYear = dateRange.getMinYear();
        int maxYear = dateRange.getMaxYear();

        int year = datePosition / 12 + minYear;
        int month = datePosition % 12;
        if (month == 0) { month = 12; year--; }

        if (year > maxYear) {
            year = maxYear;
        }
        return new int[]{year, month};
    }

    public static int getDayNumOfMonth(int year, int month) {
        int dayNum = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||
                month == 12) {
            dayNum = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            dayNum = 30;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                dayNum = 29;
            } else {
                dayNum = 28;
            }
        }
        return dayNum;
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    public static int isCurrentYearMonth(int year, int month) {
        month--;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == month) {
            return calendar.get(Calendar.DATE);
        }
        return -1;
    }

    public static String formatDate(int year, int month) {
        return Integer.toString(year) + "年" + Integer.toString(month) + "月";
    }

}
