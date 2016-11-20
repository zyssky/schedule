package com.example.administrator.schedule.Models;

/**
 * Created by wand on 2016/11/21.
 */

public class Schedule {

    public int sche_id;
    public int user_id;
    public String title;
    public String content;
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int type;

    public Schedule(){

    }

    public Schedule(int sche_id, int user_id, String title, String content, int year, int month,
                    int day, int hour, int minute, int type){

            this.sche_id = sche_id;
            this.user_id = user_id;
            this.title   = title;
            this.content = content;
            this.year    = year;
            this.month   = month;
            this.day     = day;
            this.hour    = hour;
            this.minute  = minute;
            this.type    = type;
    }

    public Schedule(int user_id, String title, String content, int year, int month,
                    int day, int hour, int minute, int type){

            this.user_id = user_id;
            this.title   = title;
            this.content = content;
            this.year    = year;
            this.month   = month;
            this.day     = day;
            this.hour    = hour;
            this.minute  = minute;
            this.type    = type;
    }
}
