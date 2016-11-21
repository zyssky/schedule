package com.example.administrator.schedule.SignReward;

import android.content.Context;

import com.example.administrator.schedule.Models.dbOpt;

/**
 * Created by nyq on 2016/11/21.
 */

public class DBRepository {
    private static DBRepository sDBRepository;
    private Context mContext;
    private dbOpt mdbOpt;

    private DBRepository() {

    }

    public void setContext(Context context) {
        mContext = context;
    }

    public static DBRepository getDBRepository() {
        if (sDBRepository == null) {
            sDBRepository = new DBRepository();
        }
        return sDBRepository;
    }

    public dbOpt getdbOpt() {
        if (mdbOpt == null) {
            mdbOpt = new dbOpt();
        }
        return mdbOpt;
    }
}
