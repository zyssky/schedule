package com.example.administrator.schedule.SignReward.Month;

import com.example.administrator.schedule.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nyq on 2016/11/21.
 */

public class MonthModel implements MonthContract.Model {
    @Override
    public ArrayList<ExchangedRecord> getExchangedRecords() {
        ArrayList<ExchangedRecord> exchangedRecords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExchangedRecord exchangedRecord = new ExchangedRecord();
            MyDate myDate = new MyDate(2016, 11, i+1);
            exchangedRecord.setDate(myDate);
            exchangedRecords.add(exchangedRecord);
        }
        return exchangedRecords;
    }
}
