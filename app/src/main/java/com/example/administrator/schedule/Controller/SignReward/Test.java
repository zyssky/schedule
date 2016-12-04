package com.example.administrator.schedule.Controller.SignReward;

import android.util.Log;

import com.example.administrator.schedule.Models.SignReward.Award;

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
        Award award1 = new Award(1, "This is a description", 1, "yinhun");
        Award award2 = new Award(2, "This is a decription", 2, "yinsan");
        Award award3 = new Award(3,"just a description", 2, "kela");
        Award award4 = new Award(4, "just a description", 2, "kazila");
        Award award5 = new Award(5, "just a description", 2, "yilisaba");
        DBRepository.getDBRepository().addSignAward(award1);
        DBRepository.getDBRepository().addSignAward(award2);
        DBRepository.getDBRepository().addSignAward(award3);
        DBRepository.getDBRepository().addSignAward(award4);
        DBRepository.getDBRepository().addSignAward(award5);
        Log.d("in Test", "add add add");
    }
}
