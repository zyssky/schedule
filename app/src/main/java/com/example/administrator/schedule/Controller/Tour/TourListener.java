package com.example.administrator.schedule.Controller.Tour;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2016/12/11.
 */

public interface TourListener {
    Activity getActivityContext();
    void showMsg(String message);
}
