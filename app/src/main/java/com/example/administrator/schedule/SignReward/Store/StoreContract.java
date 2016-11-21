package com.example.administrator.schedule.SignReward.Store;

import com.example.administrator.schedule.SignReward.Data.Award;

/**
 * Created by nyq on 2016/11/21.
 */

public interface StoreContract {
    interface Presenter {
        boolean dealExchange(Award award);
    }
}
