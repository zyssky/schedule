package com.example.administrator.schedule.Notifications;

/**
 * Created by Administrator on 2016/11/21.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.administrator.schedule.*;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //设置通知内容并在onReceive()这个函数执行时开启
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(context).setSmallIcon(R.drawable.school_days)
                .setContentText("content").setContentTitle("title").build();
        manager.notify(1, notification);


        //再次开启LongRunningService这个服务，从而可以
//        Intent i = new Intent(context, LongRunningService.class);
//        context.startService(i);
    }


}
