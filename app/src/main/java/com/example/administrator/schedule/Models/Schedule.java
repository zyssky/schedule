package com.example.administrator.schedule.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.prolificinteractive.materialcalendarview.CalendarDay;


    /**
     * Created by Administrator on 2016/11/7.
     */

    public class Schedule implements Parcelable,Comparable<Schedule> {

//    private transient CalendarDay calendarDay;

        public int sche_id;
        public int user_id;
        public int year;
        public int month;
        public int day;
        public int hour = -1;
        public int minute = -1;
        public int type = 1;
        public int status = 0;

        public String title = "";
        public String content = "";

        public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
            @Override
            public Schedule createFromParcel(Parcel source) {
                return new Schedule(source);
            }

            @Override
            public Schedule[] newArray(int size) {
                return new Schedule[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(year);
            dest.writeInt(month);
            dest.writeInt(day);
            dest.writeInt(hour);
            dest.writeInt(minute);
            dest.writeInt(type);
            dest.writeString(title);
            dest.writeString(content);
            dest.writeInt(user_id);
            dest.writeInt(status);
        }


        public Schedule(CalendarDay calendarDay, int hour, int minute, int type, String title, String content){
//        this.calendarDay = calendarDay;
            this.year = calendarDay.getYear();
            this.month = calendarDay.getMonth();
            this.day = calendarDay.getDay();
            this.title = title;
            this.content  = content;
            this.type = type;
            this.user_id = CurrentUser.getUser().user_id;
        }

        public Schedule(int year,int month,int day){
            this.year = year;
            this.month = month;
            this.day = day;
            this.user_id = CurrentUser.getUser().user_id;
        }

        public Schedule(Day day){
            this.year = day.year;
            this.month = day.month;
            this.minute = day.minute;
            this.hour = day.hour;
            this.day = day.day;
            this.user_id = CurrentUser.getUser().user_id;
        }

        public Schedule(Parcel parcel){
            this(parcel.readInt(),parcel.readInt(),parcel.readInt(),parcel.readInt(),parcel.readInt(),
                    parcel.readInt(),parcel.readString(),parcel.readString(),parcel.readInt(),parcel.readInt());
        }

    public Schedule(){

    }


    public Schedule(int year, int month, int day , int hour, int minute, int type, String title , String content, int user_id,int status){

        this.year = year;
        this.month= month;
        this.day  = day;
        this.hour = hour;
        this.minute = minute;
        this.type   = type;
        this.title  = title;
        this.content= content;
        this.user_id= user_id;
        this.status = status;
    }


    public Schedule(int sche_id, int user_id, String title, String content, int year, int month,
                    int day, int hour, int minute, int type, int status){

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
            this.status  = status;
    }

    public Schedule(int user_id, String title, String content, int year, int month,
                    int day, int hour, int minute, int type, int status){

            this.user_id = user_id;
            this.title   = title;
            this.content = content;
            this.year    = year;
            this.month   = month;
            this.day     = day;
            this.hour    = hour;
            this.minute  = minute;
            this.type    = type;
            this.status  = status;
        }

        @Override
        public int compareTo(Schedule another) {
            if(hour*60+minute>another.hour*60+another.minute)
                return 1;
            else
                if(hour*60+minute<another.hour*60+another.minute)
                return -1;
            return 0;
        }
    }



