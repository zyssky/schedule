package com.example.administrator.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import com.prolificinteractive.materialcalendarview.CalendarDay;

/**
 * Created by Administrator on 2016/11/7.
 */

public class Schedule implements Parcelable {

//    private transient CalendarDay calendarDay;

    public int year;
    public int month;
    public int day;

    public int hour = -1;
    public int minute = -1;

    public int type = 1;

    public String title;
    public String content;

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

    }

    public Schedule(int year,int month,int day,int type,int hour,int minute,String title,String content){
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.content  = content;
        this.type = type;
    }

    public Schedule(CalendarDay calendarDay,int hour,int minute,int type,String title,String content){
//        this.calendarDay = calendarDay;
        this.year = calendarDay.getYear();
        this.month = calendarDay.getMonth();
        this.day = calendarDay.getDay();
        this.title = title;
        this.content  = content;
        this.type = type;
    }

    public Schedule(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Schedule(Parcel parcel){
        this(parcel.readInt(),parcel.readInt(),parcel.readInt(),parcel.readInt(),parcel.readInt(),
                parcel.readInt(),parcel.readString(),parcel.readString());
    }
}
