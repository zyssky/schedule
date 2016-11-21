package com.example.administrator.schedule.SignReward.Data;

import java.util.Date;

/**
 * Created by nyq on 2016/11/21.
 */

public class MyDate {
    private int mYear, mMonth, mDay;
    private Date mDate;

    public MyDate(int year, int month,int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mDate = new Date(year-1900, month - 1, day);
    }

    public MyDate(Date date) {
        mDate = date;
        mYear = date.getYear() + 1900;
        mMonth = date.getMonth() + 1;
        mDay = date.getDay();
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

}
