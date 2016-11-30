package com.example.administrator.schedule.Controller.SignReward;

import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.Models.CurrentUser;
import com.example.administrator.schedule.Models.User;
import com.example.administrator.schedule.Models.dbOpt;
import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.Util.OnScheduleFinishListener;

import java.util.List;

/**
 * Created by nyq on 2016/11/21.
 */

public class DBRepository implements OnScheduleFinishListener{
    private static DBRepository sDBRepository;
    private dbOpt mdbOpt;


    private int mUserID = 1;

    private DBRepository() {
        mdbOpt = new dbOpt();
    }

    public static DBRepository getDBRepository() {
        if (sDBRepository == null) {
            sDBRepository = new DBRepository();
        }
        return sDBRepository;
    }


    public boolean addSignIn(String dateStr) {
        int userID = CurrentUser.getUser().user_id;
        signin s = new signin(userID, dateStr);
        mdbOpt.add_signin(s);
        return true;
    }

    public boolean updateUserPoint(int point) {
        int id = CurrentUser.getUser().user_id;
        mdbOpt.update_table("user", "point", "user_id", Integer.toString(id), Integer.toString(point));
        return true;
    }

    public int querySignIn(String dateStr) {
        int userID = CurrentUser.getUser().user_id;
        String userIDStr = Integer.toString(userID);
        List<Object> signInRecords = mdbOpt.userdef_query("signin", "select * from signin where user_id = ? and sign_date = ?", new String[] {userIDStr, dateStr});
//        if (signInRecords == null||signInRecords.size() == 0) {
//            return 0;
//        }
//
//        return 1;
        return signInRecords.size();
    }

    public int queryPoint() {

        int userID = CurrentUser.getUser().user_id;

        String userIDStr = Integer.toString(userID);
        List<Object> users = mdbOpt.query_info("user", "user_id", userIDStr);
        if (users == null || users.size() == 0) {
            return -1;
        }
        User user = (User)users.get(0);
        return user.point;
    }

    public List<Object> queryExchange() {
        int userID = CurrentUser.getUser().user_id;
        String userIDStr = Integer.toString(userID);
        List<Object> exchanges = mdbOpt.query_info("exchange", "user_id", userIDStr);
        return exchanges;
    }

    public List<Object> queryExchange(String timeStr) {
        int userID = CurrentUser.getUser().user_id;
        String userIDStr = Integer.toString(userID);
        timeStr += "%";
        List<Object> exchanges = mdbOpt.userdef_query("exchange",
                "select * from exchange where user_id = ? and exchange_date like ?", new String[]{userIDStr, timeStr});
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

    public List<Object> queryYearMonthSignIn(int year, int month) {
        int userID = CurrentUser.getUser().user_id;
        String userIDStr = Integer.toString(userID);
        String yearMonthStr = Integer.toString(year) + "-" + Integer.toString(month) + "%";
        List<Object> signins = mdbOpt.userdef_query("signin",
                "select * from signin where user_id = ? and sign_date like ?",
                new String[]{userIDStr, yearMonthStr});
        return signins;
    }

    @Override
    public void addIntegration() {
        int point = queryPoint();
        updateUserPoint(point + 2);
    }
}
