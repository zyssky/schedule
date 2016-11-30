package com.example.administrator.schedule.Controller.SignReward.Store;

import com.example.administrator.schedule.Models.Award;

/**
 * Created by nyq on 2016/11/21.
 */

public interface StoreContract {
    interface View {
        void showNoExchangeToady();
    }
    interface Presenter {
        boolean dealExchange(Award award);
        int getPoint();
        boolean checkExchangedToday();
    }
}
