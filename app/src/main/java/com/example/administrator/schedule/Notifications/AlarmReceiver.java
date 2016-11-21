package com.example.administrator.schedule.Notifications;

/**
 * Created by Administrator on 2016/11/21.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.administrator.schedule.*;

import static android.content.ContentValues.TAG;

public class AlarmReceiver extends BroadcastReceiver {

    public SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        //设置通知内容并在onReceive()这个函数执行时开启
        context.stopService(intent);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder nb=new Notification.Builder(context).setSmallIcon(R.drawable.school_days);
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(KEY.SCHEDULE_TYPE)){
            case 1:
                manager.notify(1, getImportantNotify(nb,bundle.getString(KEY.SCHEDULE_NAME)));
                break;
            case 2:
                manager.notify(1, getNormalNotify(nb,bundle.getString(KEY.SCHEDULE_NAME)));
                break;
            case 3:
                manager.notify(1, getSimpleNotify(nb,bundle.getString(KEY.SCHEDULE_NAME)));
                break;
        }


        context.stopService(intent);

        //再次开启LongRunningService这个服务，从而可以一直提醒
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);
    }

    public Notification getImportantNotify(Notification.Builder nb,String title){
        Notification notification = nb.setContentTitle(title).build();
        if(sharedPreferences.getBoolean("important_vibrate",false))
            notification.defaults |= Notification.DEFAULT_VIBRATE;
        if(sharedPreferences.getBoolean("important_noise",false))
        notification.sound = Uri.parse(sharedPreferences.getString("ring",null));
        return notification;
    }

    public Notification getNormalNotify(Notification.Builder nb,String title){
        Notification notification = nb.setContentTitle(title).build();
        if(sharedPreferences.getBoolean("normal_vibrate",false))
            notification.defaults |= Notification.DEFAULT_VIBRATE;
        if(sharedPreferences.getBoolean("normal_noise",false))
            notification.sound = Uri.parse(sharedPreferences.getString("ring",null));
        return notification;
    }

    public Notification getSimpleNotify(Notification.Builder nb,String title){
        Notification notification = nb.setContentTitle(title).build();
        if(sharedPreferences.getBoolean("simple_vibrate",false))
            notification.defaults |= Notification.DEFAULT_VIBRATE;
        if(sharedPreferences.getBoolean("simple_noise",false))
            notification.sound = Uri.parse(sharedPreferences.getString("ring",null));
        return notification;
    }
}
