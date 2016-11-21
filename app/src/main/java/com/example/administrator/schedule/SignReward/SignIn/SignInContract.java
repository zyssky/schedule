package com.example.administrator.schedule.SignReward.SignIn;

import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.Date;

/**
 * Created by nyq on 2016/11/20.
 */

public interface SignInContract {
    interface View {
        void updateViewAfterSign();
        void setPointsView(int points);
        void unableSignButton();
    }
    interface Presenter {
        void signToday();
        void getPoints();
        void checkSigned(MyDate date);
    }

    interface Model {
        boolean isSigned(MyDate date);
        int getPoints();
        void sign(MyDate date);
    }
}
