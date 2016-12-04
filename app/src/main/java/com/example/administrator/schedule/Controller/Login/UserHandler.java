package com.example.administrator.schedule.Controller.Login;

import com.example.administrator.schedule.Models.Database.User;
import com.example.administrator.schedule.Models.Database.dbOpt;

import java.util.List;

/**
 * Created by nyq on 2016/11/29.
 */

public class UserHandler {
    private dbOpt mDBOpt = new dbOpt();
    public User queryUser(String name) {
        List<Object> objects = mDBOpt.query_info("user", "username", name);
        if (objects.size() == 0) {
            return null;
        }
        return (User)objects.get(0);
    }
    public void insertUser(String name, String pass) {
        mDBOpt.add_user(new User(name, pass, 0, ""));
    }
}
