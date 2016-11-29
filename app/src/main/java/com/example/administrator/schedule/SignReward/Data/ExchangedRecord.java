package com.example.administrator.schedule.SignReward.Data;


/**
 * Created by nyq on 2016/11/21.
 */

public class ExchangedRecord {
    private DateWrapper mDate;
    private int mAwardID;

    public ExchangedRecord(){}
    public ExchangedRecord(DateWrapper date, int awardID) {
        mDate = date;
        mAwardID = awardID;
    }

    public DateWrapper getDateWrapper() {
        return mDate;
    }

    public void setDateWrapper(DateWrapper mDate) {
        this.mDate = mDate;
    }

    public int getAwardID() {
        return mAwardID;
    }

    public void setAwardID(int mAwardID) {
        this.mAwardID = mAwardID;
    }
}
