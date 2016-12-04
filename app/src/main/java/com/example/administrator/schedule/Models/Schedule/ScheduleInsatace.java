package com.example.administrator.schedule.Models.Schedule;

import com.example.administrator.schedule.Util.Day;


import static com.example.administrator.schedule.Models.Schedule.ScheduleHandler.getInstance;

/**
 * Created by Administrator on 2016/12/1.
 */

public class ScheduleInsatace {
    private Schedule schedule;

    public ScheduleInsatace(Schedule schedule){
        this.schedule = schedule;
    }

    public ScheduleInsatace(int position){
        if(position >= 0){
            this.schedule = getInstance().getSchedule(position);
        }
        else
            this.schedule = new Schedule(getInstance().year, getInstance().month, getInstance().day);
    }

    public String getTitle(){
        if(schedule.title!=null)
            return schedule.title;
        else
            return "";
    }
    public String getContent(){
        if(schedule.content!=null)
            return schedule.content;
        else
            return "";
    }
    public Day getTime(){
        return new Day(schedule.year,schedule.month,schedule.day,schedule.hour,schedule.minute);
    }

    public int getType(){
        return schedule.type;}

    public void setTitle(String title){ schedule.title = title; }
    public void setContent(String content){ schedule.content = content; }
    public void setType(int type){ schedule.type = type; }
    public void setTime(Day date){
        schedule.year = date.year;
        schedule.month = date.month;
        schedule.day = date.day;
        schedule.hour = date.hour;
        schedule.minute = date.minute;
    }

    public void setTime(int hour , int minute){
        schedule.hour = hour;
        schedule.minute = minute;
    }

    public void save(int position){
        if(schedule.title.length()>0)
            if(position<0)
                ScheduleHandler.getInstance().addSchedule(schedule);
            else
                ScheduleHandler.getInstance().updateSchedule(position,schedule);

    }
}
