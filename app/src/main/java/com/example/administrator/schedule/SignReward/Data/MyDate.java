package com.example.administrator.schedule.SignReward.Data;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by nyq on 2016/11/21.
 */

public class MyDate {
    private int mYear, mMonth, mDay;
    private Date mDate;

    public MyDate(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mDate = new Date(year-1900, month - 1, day);

    }

    private MyDate(Date date) {
        mDate = date;
        String[] dateStr = toDate().split("-");
        mYear = Integer.parseInt(dateStr[0]);
        mMonth = Integer.parseInt(dateStr[1]);
        mDay = Integer.parseInt(dateStr[2]);
    }

    public MyDate(long time) {
        this(new Date(time));
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        this.mYear = year;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        this.mMonth = month;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        this.mDay = day;
    }

    public Date getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return Long.toString(mDate.getTime());
    }

    public String toDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(mDate);
    }
}
