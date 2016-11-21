package com.example.administrator.schedule.SignReward.Data;

import java.util.Date;

/**
 * Created by nyq on 2016/11/21.
 */

public class ExchangedRecord {
    private MyDate mDate;
    private int mAwardID;

    public MyDate getDate() {
        return mDate;
    }

    public void setDate(MyDate mDate) {
        this.mDate = mDate;
    }

    public int getAwardID() {
        return mAwardID;
    }

    public void setAwardID(int mAwardID) {
        this.mAwardID = mAwardID;
    }
}
