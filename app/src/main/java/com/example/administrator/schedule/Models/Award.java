package com.example.administrator.schedule.Models;

/**
 * Created by nyq on 2016/11/21.
 */

public class Award {
    private int mAwardID;
    private String mDescription;
    private int mPoint;
    private int mSrc;
    private String mName;

    public Award(){}

    public Award(int awardID, String description, int point, int src, String name) {
        mAwardID = awardID;
        mDescription = description;
        mPoint = point;
        mSrc = src;
        mName = name;
    }

    public Award(int awardID, String description, int point, String name) {
        mAwardID = awardID;
        mDescription = description;
        mPoint = point;
        mName = name;
    }

    public Award(String description, int point, int src, String name) {
        mDescription = description;
        mPoint = point;
        mSrc = src;
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getPoint() {
        return mPoint;
    }

    public void setPoint(int point) {
        this.mPoint = point;
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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
