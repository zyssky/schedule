package com.example.administrator.schedule.Notification;

/**
 * Created by Administrator on 2016/11/20.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.administrator.schedule.*;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //设置通知内容并在onReceive()这个函数执行时开启
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.school_days);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(context).setSmallIcon(R.drawable.school_days).setContentTitle("休息啦").setContentText("真的休息啦！").build();
        manager.notify(1, notification);


        //再次开启LongRunningService这个服务，从而可以
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);
    }


}