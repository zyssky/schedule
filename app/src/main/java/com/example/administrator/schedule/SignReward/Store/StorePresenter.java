package com.example.administrator.schedule.SignReward.Store;

import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.SignReward.Data.CalendarUtils;

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
        String dateStr = CalendarUtils.getTodayDate().toString();
        exchange e = new exchange(userID, awardID, dateStr);

        int newPoint = mDBRepository.queryPoint(userID) - award.getPoint();

        mDBRepository.addExchange(e);
        mDBRepository.updateUserPoint(newPoint, userID);
        return true;
    }
}
