package com.example.administrator.schedule.SignReward.SignIn;

import android.util.Log;

import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.SignReward.Data.CalendarUtils;
import com.example.administrator.schedule.SignReward.Data.MyDate;

import java.util.Date;

/**
 * Created by nyq on 2016/11/20.
 */

public class SignInPresenter implements SignInContract.Presenter{

    private SignInContract.View mSignView;
    private DBRepository mDBRepository;

    public SignInPresenter(SignInContract.View signInView) {
        mSignView = signInView;
        mDBRepository = DBRepository.getDBRepository();
    }

    @Override
    public void signToday() {
        int userID = 1;
        int currentPoint = getPoints();
        int newPoint =  currentPoint + 2;

        MyDate date = CalendarUtils.getTodayDate();
        String dateStr = date.toDate();
        signin s = new signin(userID, dateStr);

        boolean bs1 = mDBRepository.addSignIn(s);
        boolean bs2 = mDBRepository.updateUserPoint(userID, newPoint);
//        if (bs1 && bs2) {
//            mSignView.updateViewAfterSign(newPoint);
//        }
        mSignView.updateViewAfterSign(newPoint);
    }

    @Override
    public int getPoints() {
        int userID = 1;
        int points = mDBRepository.queryPoint(userID);
        mSignView.setPointsView(points);

        return points;
    }

    @Override
    public void checkSigned(MyDate date) {
        int userID = 1;
        String dateStr = date.toDate();
        int isSigned = mDBRepository.querySignIn(userID, dateStr);
        if (isSigned != 0) {
            mSignView.unableSignButton();
        }
    }
}
