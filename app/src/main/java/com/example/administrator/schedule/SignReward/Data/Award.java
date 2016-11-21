package com.example.administrator.schedule.SignReward.Data;

import com.example.administrator.schedule.R;

/**
 * Created by nyq on 2016/11/21.
 */

public class Award {
    private int mAwardID;
    private String mDescription;
    private int mPoints;
    private int mSrc;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int mPoints) {
        this.mPoints = mPoints;
    }

    public int getSrc() {
        return mSrc;
    }

    public void setSrc(int mSrc) {
        this.mSrc = mSrc;
    }

    public int getAwardID() {
        return mAwardID;
    }

    public void setAwardID(int mAwardID) {
        this.mAwardID = mAwardID;
    }
}
