package com.example.administrator.schedule.SignReward.Store;

import android.util.Log;

import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.SignReward.Data.DateWrapper;

import java.util.List;

/**
 * Created by nyq on 2016/11/21.
 */

public class StorePresenter implements StoreContract.Presenter {
    private DBRepository mDBRepository = DBRepository.getDBRepository();
    private StoreContract.View mView;

    public StorePresenter(StoreContract.View view) {
        mView = view;
    }

    @Override
    public boolean dealExchange(Award award) {
        int userID = 1;
        int awardID = award.getAwardID();
        String dateStr = DateWrapper.getToday().toDateStr();
        exchange e = new exchange(userID, awardID, dateStr);

        int newPoint = mDBRepository.queryPoint() - award.getPoint();
        mDBRepository.addExchange(e);
        mDBRepository.updateUserPoint(newPoint);
        return true;
    }
    @Override
    public int getPoint() {
        int points = mDBRepository.queryPoint();
        return points;
    }

    @Override
    public boolean checkExchangedToday() {
        List<Object> exchanges = mDBRepository.queryExchange(DateWrapper.getToday().toDateStr());
        if (exchanges.size() != 0) {
            mView.showNoExchangeToady();
            return true;
        }
        return false;
    }
}
