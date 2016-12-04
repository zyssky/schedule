package com.example.administrator.schedule.Models.Database;

/**
 * Created by wand on 2016/11/21.
 */

public class exchange {

    public int ex_id;
    public int user_id;
    public int award_id;
    public String exchange_date;

    public exchange(){

    }

    public exchange(int ex_id, int user_id, int award_id, String exchange_date){

        this.ex_id   = ex_id;
        this.user_id = user_id;
        this.award_id= award_id;
        this.exchange_date = exchange_date;
    }

    public exchange(int user_id, int award_id, String exchange_date){

        this.user_id = user_id;
        this.award_id= award_id;
        this.exchange_date = exchange_date;
    }

}
