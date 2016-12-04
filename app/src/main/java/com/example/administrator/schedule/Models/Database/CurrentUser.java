package com.example.administrator.schedule.Models.Database;

/**
 * Created by nyq on 2016/11/29.
 */

public class CurrentUser {
    private static User sUser;

    public static User getUser() {
        if (sUser == null) {
            sUser = new User(1, "0xcc","since2016",0,"2016-11-21 08:21:57");
        }
        return sUser;
    }
    public static void setUser(User user) {
        sUser = user;
    }
}
