package com.example.administrator.schedule.Controller.SignReward.Data;

import java.util.Calendar;

/**
 * Created by nyq on 2016/11/22.
 */

public class DateWrapper implements Comparable<DateWrapper>{
    private int year, month, day;

    public DateWrapper(int year, int month, int day) {
        this.year = year;this.month = month;this.day = day;
    }

    public DateWrapper() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static DateWrapper getToday() {
        return new DateWrapper();
    }

    public static DateWrapper parseDateStr(String dateStr) {
        DateWrapper dateWrapper = null;
        try {
            String[] ds = dateStr.split("-");
            dateWrapper = new DateWrapper(Integer.parseInt(ds[0]), Integer.parseInt(ds[1]), Integer.parseInt(ds[2]));
        }
        catch (Exception e) {

        }
        return dateWrapper;
    }

    @Override
    public int compareTo(DateWrapper another) {
        if (getYear() < another.getYear()) {
            return -1;
        }
        else if(getYear() > another.getYear()) {
            return 1;
        }
        else if(getMonth() < another.getMonth()) {
            return -1;
        }
        else if(getMonth() > another.getMonth()) {
            return 1;
        }
        else if (getDay() < another.getDay()) {
            return -1;
        }
        else if(getDay() > another.getDay()) {
            return 1;
        }
        return 0;
    }

    public String toDateStr() {
        return Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
