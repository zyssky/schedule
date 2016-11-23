package com.example.administrator.schedule;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.administrator.schedule.Models.Schedule;
import com.example.administrator.schedule.Models.dbOpt;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

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
    private OnScheduleFinishListener listener;

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
        scheduleList.clear();
        selectedList.clear();
        // TODO: 2016/11/20 load from the database
        scheduleList = ScheduleHandler.dbopt.userdef_query("schedule","SELECT * FROM schedule WHERE year=? and month=? and day=?",
                new String[]{year+"",month+"",day+""});
        sort();

        /*
        // TODO: 2016/11/23 initialize the OnScheduleListener
        // listener = ?
         */
    }

    public List<Schedule> getList(){
        return scheduleList;
    }


    public Schedule getSchedule(int position){
        return (Schedule) scheduleList.get(position);
    }

    public void addSelectedSchedule(int position){
        if(position<scheduleList.size()) {
            Schedule schedule = (Schedule) scheduleList.get(position);
            if (!selectedList.contains(schedule))
                selectedList.add(schedule);
            else
                selectedList.remove(schedule);
        }
    }

    public void cleanSelected(){
        selectedList.clear();
    }


    public void addSchedule(Schedule schedule){
        // TODO: 2016/11/20 add schedule into the databases
        scheduleList.add(schedule);
        ScheduleHandler.dbopt.add_schedule(schedule);
        sort();
    }


    public void deleteSchedule(Schedule schedule){
        // TODO: 2016/11/3 delete the item from storage
        scheduleList.remove(schedule);
        ScheduleHandler.dbopt.delete_func("schedule","title",schedule.title);
    }

    public void finishSchedule(Schedule schedule){
        schedule.status = 1;
        if(listener!=null)
            listener.addIntegration();
        updateSchedule(scheduleList.indexOf(schedule),schedule);
    }

    public void finishSchedules(){
        for (Object index : selectedList) {
            finishSchedule((Schedule) index);
        }
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
        scheduleHandler.dbopt.userdef_update("UPDATE schedule SET user_id=? , title=? , " +
                                             "content=? , year=? , month = ? , " +
                                             "day = ? , hour = ? , " +
                                             "minute = ? , type = ? WHERE title = ? ",
                new String[]{schedule.user_id+"",schedule.title,
                        schedule.content,schedule.year+"",schedule.month+"", schedule.day+"",
                        schedule.hour+"",schedule.minute+"",schedule.type+"",old_temp.title});
        sort();
    }

    public void sort(){
        Collections.sort(scheduleList);
    }
}
