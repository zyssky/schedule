package com.example.administrator.schedule.Models.SignReward;

/**
 * Created by wand on 2016/11/21.
 */

public class signin {

    public int signin_id;
    public int user_id;
    public String sign_date;

    public signin(){

    }

    public signin(int signin_id, int user_id, String sign_date){

        this.signin_id = signin_id;
        this.user_id   = user_id;
        this.sign_date = sign_date;
    }

    public signin(int user_id, String sign_date){

        this.user_id   = user_id;
        this.sign_date = sign_date;
    }
}

