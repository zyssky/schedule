package com.example.administrator.schedule.SignReward.Data;

/**
 * Created by nyq on 2016/11/20.
 */

public class DateRange {
    private static DateRange sDateRange;
    public static final int mDefaultMaxYear = 2100;
    public static final int mDefaultMinYear = 1900;
    private int mMaxYear;
    private int mMinYear;

    private DateRange() {
        mMaxYear = mDefaultMaxYear;
        mMinYear = mDefaultMinYear;
    }

    public static DateRange getDateRange() {
        if (sDateRange == null) {
            sDateRange = new DateRange();
        }
        return sDateRange;
    }


    public int getMinYear() {
        return mMinYear;
    }

    public int getMaxYear() {
        return mMaxYear;
    }

    public void setMinYear(int year) {
        mMaxYear = year;
    }
    public void setMaxYear(int year) {
        mMinYear = year;
    }
    public int getCount() {
        if (mMaxYear < mMinYear) {
            resetRange();
        }
        return (mMaxYear - mMinYear) * 12;
    }

    private void resetRange() {
        mMaxYear = mDefaultMaxYear;
        mMinYear = mDefaultMinYear;
    }
}

