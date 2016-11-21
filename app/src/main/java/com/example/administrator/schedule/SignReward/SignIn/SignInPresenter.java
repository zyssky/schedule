package com.example.administrator.schedule.SignReward.SignIn;

import com.example.administrator.schedule.SignReward.Data.CalendarUtils;
import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.Date;

/**
 * Created by nyq on 2016/11/20.
 */

public class SignInPresenter implements SignInContract.Presenter{

    private SignInContract.View mSignView;
    private SignInContract.Model mSignModel;

    public SignInPresenter(SignInContract.View signInView) {
        mSignView = signInView;
        mSignModel = new SignInModel();
    }

    @Override
    public void signToday() {
        mSignModel.sign(CalendarUtils.getTodayDate());
        mSignView.updateViewAfterSign();
    }

    @Override
    public void getPoints() {
        int points = mSignModel.getPoints();
        mSignView.setPointsView(points);
    }

    @Override
    public void checkSigned(MyDate date) {
        boolean isSigned = mSignModel.isSigned(date);
        if (isSigned) {
            mSignView.unableSignButton();
        }
    }
}
