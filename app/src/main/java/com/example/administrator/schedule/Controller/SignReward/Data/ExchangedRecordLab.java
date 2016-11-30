package com.example.administrator.schedule.Controller.SignReward.Data;

import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Controller.SignReward.DBRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by nyq on 2016/11/21.
 */

public class ExchangedRecordLab {
    private static ExchangedRecordLab mExchangedRecordLab;
    private static ArrayList<ExchangedRecord> mExchangedRecords;

    private ExchangedRecordLab() {}

    public static ExchangedRecordLab getExchangedRecordLab() {
        if (mExchangedRecordLab == null) {
            mExchangedRecordLab = new ExchangedRecordLab();
            loadExchangedRecords();
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

    public static ArrayList<ExchangedRecord> loadExchangedRecords() {

        ArrayList<ExchangedRecord> exchangedRecords = new ArrayList<>();
        List<Object> exchanges = DBRepository.getDBRepository().queryExchange();
        if (exchanges == null) {
            return exchangedRecords;
        }
        Collections.sort(exchanges, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String ll = ((exchange)lhs).exchange_date;
                String lr = ((exchange)rhs).exchange_date;
                return DateWrapper.parseDateStr(ll).compareTo(DateWrapper.parseDateStr(lr));
            }
        });
        for (Object o :
                exchanges) {
            exchange e = (exchange)o;
            ExchangedRecord exchangedRecord = new ExchangedRecord();
            exchangedRecord.setAwardID(e.award_id);
            exchangedRecord.setDateWrapper(DateWrapper.parseDateStr(e.exchange_date));

            exchangedRecords.add(exchangedRecord);
        }

        mExchangedRecords = exchangedRecords;

        return exchangedRecords;
    }

}
