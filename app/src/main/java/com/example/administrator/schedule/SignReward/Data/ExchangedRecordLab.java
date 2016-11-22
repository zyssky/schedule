package com.example.administrator.schedule.SignReward.Data;

import java.util.ArrayList;

/**
 * Created by nyq on 2016/11/21.
 */

public class ExchangedRecordLab {
    private static ExchangedRecordLab mExchangedRecordLab;
    private ArrayList<ExchangedRecord> mExchangedRecords;

    private ExchangedRecordLab() {}

    public static ExchangedRecordLab getExchangedRecordLab() {
        if (mExchangedRecordLab == null) {
            mExchangedRecordLab = new ExchangedRecordLab();
        }
        return mExchangedRecordLab;
    }

    public int getExchangedPosition(int year, int month, int day) {
        for (int i = 0; i < mExchangedRecords.size(); i++) {
            DateWrapper dateWrapper = mExchangedRecords.get(i).getDateWrapper();
            if (dateWrapper.getYear() == year && dateWrapper.getMonth() == month && dateWrapper.getDay() == day) {
                return i;
            }
        }
        return -1;
    }
    public ExchangedRecord getExchangedRecord(int year, int month, int day) {
        ExchangedRecord exchangedRecord;
        for (int i = 0; i < mExchangedRecords.size(); i++) {
            exchangedRecord = mExchangedRecords.get(i);
            DateWrapper dateWrapper = exchangedRecord.getDateWrapper();
            if (dateWrapper.getYear() == year && dateWrapper.getMonth() == month && dateWrapper.getDay() == day) {
                return exchangedRecord;
            }
        }
        return null;
    }
    public ExchangedRecord getExchangedRecord(int position) {
        return mExchangedRecords.get(position);
    }

    public int getSize() {
        return mExchangedRecords.size();
    }

    public ArrayList<ExchangedRecord> getExchangedRecords() {
        return mExchangedRecords;
    }

    public void setExchangedRecords(ArrayList<ExchangedRecord> exchangedRecords) {
        mExchangedRecords = exchangedRecords;
    }
}
