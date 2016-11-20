package com.example.administrator.schedule.Models;

/**
 * Created by wand on 2016/11/21.
 */

public class signaward {

    public int award_id;
    public String award_item;
    public int point;

    public signaward(){

    }

    public signaward(int award_id, String award_item, int point){

        this.award_id   = award_id;
        this.award_item = award_item;
        this.point      = point;
    }

    public signaward(String award_item, int point){

        this.award_item = award_item;
        this.point      = point;
    }
}
