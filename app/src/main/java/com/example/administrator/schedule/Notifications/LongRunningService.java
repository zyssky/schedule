package com.example.administrator.schedule.Notifications;

/**
 * Created by Administrator on 2016/11/21.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.example.administrator.schedule.Models.Schedule;
import com.example.administrator.schedule.Models.dbOpt;
import com.example.administrator.schedule.Util.KEY;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;


public class LongRunningService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        dbOpt dbopt = new dbOpt();

        List<Object> list = dbopt.userdef_query("schedule","SELECT * FROM schedule WHERE year=? and month=? and day=?"+
                " and  hour>=?",
                new String[]{year+"",month+"",day+"",hour+""});

        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for(Object object:list){
            Schedule s = (Schedule)object;
            if(hour*60+minute<s.hour*60+s.minute)
            scheduleList.add(s);
        }

        Log.d(TAG, "onStartCommand: ");

        Collections.sort(scheduleList);


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if(scheduleList.size()>0){
            int Minutes = (hour-scheduleList.get(0).hour)*60+minute-scheduleList.get(0).minute;
            //SystemClock.elapsedRealtime()表示1970年1月1日0点至今所经历的时间
            long triggerAtTime = SystemClock.elapsedRealtime() - Minutes*60*1000;
            //此处设置开启AlarmReceiver这个Service
            Intent i = new Intent(this, AlarmReceiver.class);
            i.putExtra(KEY.SCHEDULE_NAME,scheduleList.get(0).title);
            i.putExtra(KEY.SCHEDULE_TYPE,scheduleList.get(0).type);
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
            //ELAPSED_REALTIME_WAKEUP表示让定时任务的出发时间从系统开机算起，并且会唤醒CPU。
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //在Service结束后关闭AlarmManager
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.cancel(pi);

    }


}

