package com.example.administrator.schedule.Models;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/12/2.
 */

public class Day {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;

    public Day(int year,int month,int day){
        this.day = day;
        this.month = month;
        this.year = year;
        hour = -1;
        minute = -1;
    }

    public Day(int year,int month,int day,int hour,int minute){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public Day(CalendarDay calendarDay){
        this.day = calendarDay.getDay();
        this.month = calendarDay.getMonth()+1;
        this.year = calendarDay.getYear();
        this.hour = -1;
        this.minute = -1;
    }
}
