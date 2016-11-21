package com.example.administrator.schedule.SignReward.Month;

import com.example.administrator.schedule.SignReward.Data.CalendarUtils;
import com.example.administrator.schedule.SignReward.Data.DayInfo;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecordLab;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by nyq on 2016/11/21.
 */

public class MonthPresenter implements MonthContract.Presenter {
    private MonthContract.View mMonthView;
    private MonthContract.Model mMonthModel;

    public MonthPresenter(MonthContract.View monthView) {
        mMonthView = monthView;
        mMonthModel = new MonthModel();
        ExchangedRecordLab.getExchangedRecordLab().setExchangedRecords(mMonthModel.getExchangedRecords());
    }


    public ArrayList<ArrayList<DayInfo>> generateDayInfoMatrix(int year, int month) {
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
                    dayInfo.exchangedPosition = ExchangedRecordLab.getExchangedRecordLab().getExchangedPosition(year, month, dayInfo.day);
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

}
