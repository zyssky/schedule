package com.example.administrator.schedule.Controller.SignReward.Store;

import com.example.administrator.schedule.Models.CurrentUser;
import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Controller.SignReward.DBRepository;
import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.Controller.SignReward.Data.DateWrapper;
import com.example.administrator.schedule.Controller.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.Controller.SignReward.Data.ExchangedRecordLab;

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
        int userID = CurrentUser.getUser().user_id;
        int awardID = award.getAwardID();
        DateWrapper today = DateWrapper.getToday();
        String dateStr = today.toDateStr();
        exchange e = new exchange(userID, awardID, dateStr);

        int newPoint = mDBRepository.queryPoint() - award.getPoint();
        mDBRepository.addExchange(e);
        ExchangedRecordLab.getExchangedRecordLab().getExchangedRecords().add(new ExchangedRecord(today, awardID));
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
