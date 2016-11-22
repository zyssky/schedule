package com.example.administrator.schedule.SignReward.Month;

import com.example.administrator.schedule.SignReward.Data.DayInfo;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecord;

import java.util.ArrayList;

/**
 * Created by nyq on 2016/11/21.
 */

public interface MonthContract {
    interface View {

    }

    interface Presenter {
        ArrayList<ArrayList<DayInfo>> generateDayInfoMatrix(int datePosition);
    }

//    interface Model {
//        ArrayList<ExchangedRecord> getExchangedRecords();
//    }
}
