package com.example.administrator.schedule.Models.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static com.example.administrator.schedule.Models.Database.Settings.create_exchange;
import static com.example.administrator.schedule.Models.Database.Settings.create_schedule;
import static com.example.administrator.schedule.Models.Database.Settings.create_signaward;
import static com.example.administrator.schedule.Models.Database.Settings.create_signin;
import static com.example.administrator.schedule.Models.Database.Settings.create_user;

/**
 * Created by wand on 2016/11/20.
 * This class is for establishing
 * a sql-table for storing data locally
 * implementing CURD Operations.
 */

public class SQLite extends SQLiteOpenHelper{

    private Context mContext;

    //Invoke each time when need using the database.
    //Db Version must be an integer increasing.
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, name, factory, version);
        mContext = context;

    }

    //Invoke when first--Create.
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(create_user);
        db.execSQL(create_schedule);
        db.execSQL(create_signaward);
        db.execSQL(create_exchange);
        db.execSQL(create_signin);
        //Show Debug Info
        Toast.makeText(mContext, "Create Tables Succeed.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db){

    }

}
