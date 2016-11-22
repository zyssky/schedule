package com.example.administrator.schedule.SignReward.SignIn;


/**
 * Created by nyq on 2016/11/20.
 */

public interface SignInContract {
    interface View {
        void updateViewAfterSign(int point);
        void setPointsView(int points);
        void unableSignButton();
    }
    interface Presenter {
        void signToday();
        int getPoints();
        int checkTodaySigned();
    }
}
