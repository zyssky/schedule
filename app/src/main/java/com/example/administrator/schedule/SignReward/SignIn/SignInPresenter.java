package com.example.administrator.schedule.SignReward.SignIn;

import com.example.administrator.schedule.Models.exchange;
import com.example.administrator.schedule.Models.signin;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.SignReward.Data.DateWrapper;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.SignReward.Data.ExchangedRecordLab;
import com.example.administrator.schedule.SignReward.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        int currentPoint = getPoints();
        int newPoint =  currentPoint + 2;

        DateWrapper dateWrapper = DateWrapper.getToday();
        String dateStr = dateWrapper.toDateStr();


        boolean bs1 = mDBRepository.addSignIn(dateStr);
        boolean bs2 = mDBRepository.updateUserPoint(newPoint);
//        if (bs1 && bs2) {
//            mSignView.updateViewAfterSign(newPoint);
//        }
        mSignView.updateViewAfterSign(newPoint);
    }

    @Override
    public int getPoints() {
        int points = mDBRepository.queryPoint();
        mSignView.setPointsView(points);

        return points;
    }

    @Override
    public int checkTodaySigned() {
        DateWrapper dateWrapper = DateWrapper.getToday();
        String dateStr = dateWrapper.toDateStr();
        int isSigned = mDBRepository.querySignIn(dateStr);
        mSignView.updateSignButton(isSigned == 0);
        return isSigned;
    }

}
