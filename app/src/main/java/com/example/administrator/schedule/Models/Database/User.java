package com.example.administrator.schedule.Models.Database;

/**
 * Created by wand on 2016/11/21.
 */

public class User {

    public int user_id;
    public String username;
    public String userpass;
    public int point;
    public String first_sign;


    public User(){

    }

    public User(int user_id, String username, String userpass, int point, String first_sign){

        this.user_id = user_id;
        this.username= username;
        this.userpass= userpass;
        this.point   = point;
        this.first_sign = first_sign;
    }

    public User(String username, String userpass, int point ,String first_sign){

        this.username= username;
        this.userpass= userpass;
        this.point   = point;
        this.first_sign = first_sign;
    }

}
