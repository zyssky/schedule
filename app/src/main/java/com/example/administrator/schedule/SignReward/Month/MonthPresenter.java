package com.example.administrator.schedule.SignReward.Month;

import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.SignReward.Utils.CalendarUtils;
import com.example.administrator.schedule.SignReward.Data.DateWrapper;
import com.example.administrator.schedule.SignReward.Data.DayInfo;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecordLab;

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
                dayInfo = null;
                if (i == 0 && lastMonthDay > 0) {
                    lastMonthDay--;
                }
                else if (count < dayNum){
                    dayInfo = new DayInfo();
                    dayInfo.day = ++count;
                    ExchangedRecordLab exchangedRecordLab = ExchangedRecordLab.getExchangedRecordLab();
                    int exchangedPosition = exchangedRecordLab.getExchangedPosition(year, month, dayInfo.day);
                    dayInfo.exchangedPosition = exchangedPosition;
                    if (exchangedPosition != -1) {
                        dayInfo.awardID = exchangedRecordLab.getExchangedRecord(exchangedPosition).getAwardID();
                    }
                    dayInfo.isSigned = isSigned(dayInfo.day);
                    if (currentDay == count) {
                        dayInfo.isToday = true;
                    }
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

    private void querySignDays(int year, int month) {
        List<Object> signins = mDBRepository.queryYearMonthSignIn(year, month);
        if (signins == null || signins.size() == 0) {
            mSignDays = null;
            return;
        }
        mSignDays = new HashSet<>();
        for (Object object :
                signins) {
            signin s = (signin) object;
            mSignDays.add(DateWrapper.parseDateStr(s.sign_date).getDay());
        }
    }

    private boolean isSigned(int day) {
        return mSignDays != null && mSignDays.contains(day);
    }

}
