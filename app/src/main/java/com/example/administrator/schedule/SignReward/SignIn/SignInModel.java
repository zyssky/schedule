package com.example.administrator.schedule.SignReward.SignIn;

import com.example.administrator.schedule.Models.dbOpt;
import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.SignReward.Data.CalendarUtils;
import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.Date;

/**
 * Created by nyq on 2016/11/21.
 */

public class SignInModel implements SignInContract.Model {
    @Override
    public void sign(MyDate date) {
        // add a row in table signin
        // update points in table user
    }

    @Override
    public boolean isSigned(MyDate date) {
        //query from table signin
        return false;
    }

    @Override
    public int getPoints() {
        return 10;
    }
}
