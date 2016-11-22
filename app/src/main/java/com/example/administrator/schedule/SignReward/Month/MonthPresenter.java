package com.example.administrator.schedule.SignReward.Month;

import android.util.Log;

import com.example.administrator.schedule.Models.User;
import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.SignReward.Data.CalendarUtils;
import com.example.administrator.schedule.SignReward.Data.DayInfo;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecordLab;
import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


/**
 * Created by nyq on 2016/11/21.
 */

public class MonthPresenter implements MonthContract.Presenter {
    private MonthContract.View mMonthView;
    private DBRepository mDBRepository;
    private HashSet<Integer> mSignDays;

    public MonthPresenter(MonthContract.View monthView) {
        mMonthView = monthView;
        mDBRepository = DBRepository.getDBRepository();
        ExchangedRecordLab.getExchangedRecordLab().setExchangedRecords(getExchangedRecords());
    }


    public ArrayList<ArrayList<DayInfo>> generateDayInfoMatrix(int year, int month) {

        querySignDays(year, month);

        ArrayList<ArrayList<DayInfo>> dayInfoMatrix = new ArrayList<>();

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(year, month - 1, 1);
        int dayNum = CalendarUtils.getDayNumOfMonth(year, month);
        int dayOfFirstWeek = mCalendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;

        int currentDay = CalendarUtils.isCurrentYearMonth(year, month);
        ArrayList<DayInfo> dayInfoList;
        DayInfo dayInfo;
        int lastMonthDay = (dayOfFirstWeek == 7 ? 0 : dayOfFirstWeek);
        int count = 0;
        for (int i = 0; i < 6; i++) {
            dayInfoList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                dayInfo = new DayInfo();
                if (i == 0 && lastMonthDay > 0) {
                    dayInfo = null;
                    lastMonthDay--;
                }
                else if (count >= dayNum) {
                    dayInfo = null;
                }
                else {
                    dayInfo.day = ++count;
                    ExchangedRecordLab exchangedRecordLab = ExchangedRecordLab.getExchangedRecordLab();
                    int exchangedPosition = exchangedRecordLab.getExchangedPosition(year, month, dayInfo.day);
                    dayInfo.exchangedPosition = exchangedPosition;
                    if (exchangedPosition != -1) {
                        dayInfo.awardID = exchangedRecordLab.getExchangedRecord(exchangedPosition).getAwardID();
                    }
                    dayInfo.isSigned = isSigned(dayInfo.day);
                }
                if (currentDay == count) {
                    dayInfo.isToday = true;
                }
                dayInfoList.add(dayInfo);
            }
            dayInfoMatrix.add(dayInfoList);
        }
        return dayInfoMatrix;
    }

    @Override
    public  ArrayList<ArrayList<DayInfo>> generateDayInfoMatrix(int datePosition) {
        int[] date = CalendarUtils.DatePositionToDate(datePosition);
        return generateDayInfoMatrix(date[0], date[1]);
    }

    public ArrayList<ExchangedRecord> getExchangedRecords() {
        int userID = 1;
        ArrayList<ExchangedRecord> exchangedRecords = new ArrayList<>();
        List<Object> exchanges = mDBRepository.queryExchange(userID);
        if (exchanges == null) {
            return exchangedRecords;
        }
        Collections.sort(exchanges, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String ll = ((exchange)lhs).exchange_date;
                String lr = ((exchange)rhs).exchange_date;
                return Long.getLong(ll).compareTo(Long.getLong(lr));
            }
        });
        for (Object o :
                exchanges) {
            exchange e = (exchange)o;
            ExchangedRecord exchangedRecord = new ExchangedRecord();
            exchangedRecord.setAwardID(e.award_id);
            exchangedRecord.setDate(new MyDate(Long.getLong(e.exchange_date)));

            exchangedRecords.add(exchangedRecord);
        }
        return exchangedRecords;
    }

    private void querySignDays(int year, int month) {
        int userID = 1;
        List<Object> signins = mDBRepository.queryYearMonthSignIn(userID, year, month);
        if (signins == null || signins.size() == 0) {
            mSignDays = null;
            return;
        }
        mSignDays = new HashSet<>();
        for (Object object :
                signins) {
            signin s = (signin) object;
            String[] dateStrArray = s.sign_date.split("-");
            if (dateStrArray.length == 3) {
                int day = Integer.parseInt(dateStrArray[2]);
                mSignDays.add(day);
            }
        }
    }

    private boolean isSigned(int day) {
        return mSignDays != null && mSignDays.contains(day);
    }

//    public ArrayList<ExchangedRecord> getExchangedRecords() {
//        ArrayList<ExchangedRecord> exchangedRecords = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ExchangedRecord exchangedRecord = new ExchangedRecord();
//            MyDate myDate = new MyDate(2016, 11, i+1);
//            exchangedRecord.setDate(myDate);
//            exchangedRecords.add(exchangedRecord);
//        }
//        return exchangedRecords;
//    }
}
