package com.example.administrator.schedule;

import android.widget.ArrayAdapter;

import com.example.administrator.schedule.Models.Schedule;
import com.example.administrator.schedule.Models.dbOpt;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/3.
 */

public class ScheduleHandler {
    public List selectedList;
    public int year;
    public int month;
    public int day;
    private List scheduleList;
    private static ScheduleHandler scheduleHandler = null;
    public static boolean isMultiSelected = false;
    public static dbOpt dbopt = new dbOpt();

    public static ScheduleHandler getInstance(){
        if(scheduleHandler != null)
            return scheduleHandler;
        else
            return getInstance(CalendarDay.today());
    }

    public static ScheduleHandler getInstance(CalendarDay selectedDay){
        scheduleHandler = new ScheduleHandler(selectedDay);
        return scheduleHandler;
    }

    public static ScheduleHandler getInstance(int year,int month,int day){
        scheduleHandler = new ScheduleHandler(year, month, day);
        return scheduleHandler;
    }

    private ScheduleHandler(CalendarDay selectedDay){
        this(selectedDay.getYear(),selectedDay.getMonth(),selectedDay.getDay());
    }

    private ScheduleHandler(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;

        scheduleList = new ArrayList<Schedule>();
        selectedList = new ArrayList<Schedule>();
        // TODO: 2016/11/20 load from the database
        scheduleList = ScheduleHandler.dbopt.query_info("schedule","","anything");

    }

    public List<Schedule> getList(){
        return scheduleList;
    }


    public Schedule getSchedule(int position){
        return (Schedule) scheduleList.get(position);
    }

    public void addSelectedSchedule(int position){
        if(!selectedList.contains(scheduleList.get(position)))
        selectedList.add(scheduleList.get(position));
    }


    public void addSchedule(Schedule schedule){
        // TODO: 2016/11/20 add schedule into the databases
        scheduleList.add(schedule);
        ScheduleHandler.dbopt.add_schedule(schedule);
    }


    public void deleteSchedule(Schedule schedule){
        // TODO: 2016/11/3 delete the item from storage
        scheduleList.remove(schedule);
        ScheduleHandler.dbopt.delete_func("schedule","title",schedule.title);
    }

    public void deleteSchedules(){
        for (Object index : selectedList) {
            deleteSchedule((Schedule) index);
        }
    }


    // where is the old one ???
    public void updateSchedule(int position,Schedule schedule){

        // TODO: 2016/11/3 update the item detail to storage
        Schedule old_temp = (Schedule) scheduleList.get(position);
        scheduleList.set(position,schedule);
        ScheduleHandler.dbopt.update_table("schedule","title","title",old_temp.title,schedule.title);
    }

    public static class Index{
        private int index;
        public Index(int index){
            this.index = index;
        }
    }
}
