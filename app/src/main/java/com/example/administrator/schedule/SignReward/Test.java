package com.example.administrator.schedule.SignReward;

import android.util.Log;

import com.example.administrator.schedule.Models.Award;

/**
 * Created by nyq on 2016/11/22.
 */

public class Test {
    private static boolean isRun = false;

    public void addAwards() {
        if (isRun) {
            Log.d("in Test", "return return return");
            return;
        }
        isRun = true;
        Award award = new Award(2,"just a description", 2, "kela");
        Award award1 = new Award(3, "just a description", 2, "kazila");
        DBRepository.getDBRepository().addSignAward(award);
        DBRepository.getDBRepository().addSignAward(award1);
        Log.d("in Test", "add add add");
    }
}
