package com.example.administrator.schedule.Models;

/**
 * Created by wand on 2016/11/21.
 * Configuration File.
 */

public class Settings {

    //SQLite DbOpt Settings:

    public static final String db_name = "schedule.db";
    public static String create_head = "CREATE TABLE IF NOT EXISTS ";
    public static String Error_logTag      = "[*]Error:";

    public static String  create_user = create_head + "user(" +
            "user_id  INTEGER  PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "userpass TEXT," +
            "point INTEGER,"  +
            "first_sign TEXT" +
            ");";

    public static String  create_schedule = create_head + "schedule("+
            "sche_id INTEGER   PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER," +
            "title TEXT," +
            "content TEXT," +
            "year INTEGER,"  +
            "month INTEGER,"  +
            "day   INTEGER,"   +
            "hour  INTEGER,"    +
            "minute INTEGER,"    +
            "type   INTEGER,"     +
            "status INTEGER,"      +
            "FOREIGN KEY(user_id) REFERENCES user(user_id)" +
            ");";

    public static String  create_signaward = create_head + "signaward("  +
            "award_id INTEGER PRIMARY KEY,"  +
            "award_name TEXT," +
            "point INTEGER,"    +
            "award_description TEXT" +
            ");";

    public static String  create_exchange  = create_head + "exchange(" +
            "ex_id INTEGER PRIMARY KEY AUTOINCREMENT,"   +
            "user_id INTEGER," +
            "award_id INTEGER," +
            "exchange_date TEXT," +
            "FOREIGN KEY(user_id) REFERENCES user(user_id)," +
            "FOREIGN KEY(award_id) REFERENCES signaward(award_id)"+
            ");";

    public static String  create_signin    = create_head  + "signin(" +
            "sign_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER," +
            "sign_date TEXT,"  +
            "FOREIGN KEY(user_id) REFERENCES user(user_id)" +
            ");";


    //

}
