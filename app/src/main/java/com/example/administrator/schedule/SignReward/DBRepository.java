package com.example.administrator.schedule.SignReward;

import android.content.Context;
import android.support.v4.app.ListFragment;

import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.Models.User;
import com.example.administrator.schedule.Models.dbOpt;
import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.List;
import java.util.Objects;

/**
 * Created by nyq on 2016/11/21.
 */

public class DBRepository {
    private static DBRepository sDBRepository;
    private dbOpt mdbOpt;

    private DBRepository() {
        mdbOpt = new dbOpt();
    }

    public static DBRepository getDBRepository() {
        if (sDBRepository == null) {
            sDBRepository = new DBRepository();
        }
        return sDBRepository;
    }


    public boolean addSignIn(signin s) {
        mdbOpt.add_signin(s);
        return true;
    }

    public boolean updateUserPoint(int point, int id) {
        mdbOpt.update_table("user", "point", "user_id", Integer.toString(point), Integer.toString(id));
        return true;
    }

    public int querySignIn(int userID, String dateStr) {
        String userIDStr = Integer.toString(userID);
        List<Object> signInRecords = mdbOpt.userdef_query("signin", "select * from signin where user_id = ? and sign_date = ?", new String[] {userIDStr, dateStr});
        if (signInRecords == null) {
            return -1;
        }
        else if (signInRecords.size() == 0) {
            return 0;
        }

        return 1;
    }

    public int queryPoint(int userID) {
        String userIDStr = Integer.toString(userID);
        List<Object> users = mdbOpt.query_info("user", "user_id", userIDStr);
        if (users == null || users.size() == 0) {
            return -1;
        }
        User user = (User)users.get(0);
        return user.point;
    }

    public List<Object> queryExchange(int userID) {
        String userIDStr = Integer.toString(userID);
        List<Object> exchanges = mdbOpt.query_info("exchange", "user_id", userIDStr);
        return exchanges;
    }

    public boolean addSignAward(Award award) {
        mdbOpt.add_signaward(award);
        return true;
    }

    public boolean addExchange(exchange e) {
        mdbOpt.add_exchange(e);
        return true;
    }

    public List<Object> querySignAward() {
        return mdbOpt.query_info("signaward", "", "");
    }

    public List<Object> queryYearMonthSignIn(int userID, int year, int month) {
        String userIDStr = Integer.toString(userID);
        String yearMonthStr = Integer.toString(year) + "-" + Integer.toString(month) + "%";
        List<Object> signins = mdbOpt.userdef_query("signin",
                "select * from signin where user_id = ? and sign_date like ?",
                new String[]{userIDStr, yearMonthStr});
        return signins;
    }
}
