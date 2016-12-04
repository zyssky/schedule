package com.example.administrator.schedule.Util;

import com.example.administrator.schedule.Models.Schedule.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;

/**
 * Created by Administrator on 2016/11/1.
 */

public class Format {
    public static String formatDateTitle(int year,int month){
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("年");
        sb.append(month+1);
        sb.append("月");
        return sb.toString();
    }

    public static String formatDateTitle(CalendarDay date){
        int year=date.getYear();
        int month = date.getMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("年");
        sb.append(month+1);
        sb.append("月");
        return sb.toString();
    }

    public static String formatDay(CalendarDay date){
        int day=date.getDay();
        int month = date.getMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(month+1);
        sb.append("月");
        sb.append(day);
        sb.append("日");
        return sb.toString();
    }

    public static String getTime(Schedule schedule){
        StringBuilder sb = new StringBuilder();
        sb.append(schedule.month);
        sb.append("月");
        sb.append(schedule.day);
        sb.append("日");
        if(schedule.hour>=0){
            sb.append(",");
            sb.append(schedule.hour);
            sb.append(":");
            sb.append(schedule.minute);
        }
        return sb.toString();
    }

    public static String getTime(Day day){
        StringBuilder sb = new StringBuilder();
        sb.append(day.month);
        sb.append("月");
        sb.append(day.day);
        sb.append("日");
        if(day.hour>0){
            sb.append(",");
            sb.append(day.hour);
            sb.append(":");
            sb.append(day.minute);
        }
        return sb.toString();
    }

    public static String formatDay(int month,int day){
        StringBuilder sb = new StringBuilder();
        sb.append(month);
        sb.append("月");
        sb.append(day);
        sb.append("日");
        return sb.toString();
    }

    public static String formatRemindTitle(Schedule schedule){
        StringBuilder sb = new StringBuilder();
        if(schedule.hour>=0) {

//        sb.append("活动提醒时间： ");
            sb.append(schedule.hour);
            sb.append(":");
            sb.append(schedule.minute);

        }
        return sb.toString();
    }
}
