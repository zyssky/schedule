package com.example.administrator.schedule.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.schedule.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wand on 2016/11/21.
 */

public class dbOpt {

    //Init Database At the beginning of the program.
    public SQLite dbOperator;
    public static Context mContext;
    private SQLiteDatabase db = null;
    public dbOpt(){

        dbOperator = new SQLite(mContext, Settings.db_name,null ,1);

        //Really Create Database:
        db = dbOperator.getReadableDatabase();
    }



    //Insert Mods:
    public void add_user(User user){

        try{
            db.execSQL("INSERT INTO user(username,userpass,point,first_sign) values(?,?,?,?)",
                    new Object[]{user.username, user.userpass, user.point, user.first_sign});
        }catch(Exception e){
            Log.d(Settings.Error_logTag, e.toString());
            Toast.makeText(mContext,"Adding User Failed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void add_schedule(Schedule schedule){

        try {
            db.execSQL("INSERT INTO schedule(user_id,title,content,year,month,day,hour,minute,type) values(?,?,?,?,?,?,?,?,?)",
                    new Object[]{schedule.user_id, schedule.title, schedule.content,
                            schedule.year, schedule.month, schedule.day,
                            schedule.hour, schedule.minute, schedule.type});
        }
        catch(Exception e){
            Log.d(Settings.Error_logTag, e.toString());
            Toast.makeText(mContext,"Adding Schedule Failed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void add_signaward(signaward signaward){

        try{
            db.execSQL("INSERT INTO signaward(award_item,point) values(?,?)",
                    new Object[]{signaward.award_item,signaward.point});
        }catch(Exception e){
            Log.d(Settings.Error_logTag, e.toString());
            Toast.makeText(mContext,"Adding signaward Failed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void add_exchange(exchange exchange){

        try{
            db.execSQL("INSERT INTO exchange(user_id,award_id,exchange_date) values(?,?,?)",
                    new Object[]{exchange.user_id,exchange.award_id,exchange.exchange_date});
        }catch(Exception e){
            Log.d(Settings.Error_logTag, e.toString());
            Toast.makeText(mContext,"Adding exchange Failed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void add_signin(signin signin){

        try{
            db.execSQL("INSERT INTO signin(user_id,sign_date) values(?,?)",
                    new Object[]{signin.user_id,signin.sign_date});
        }catch(Exception e){
            Log.d(Settings.Error_logTag, e.toString());
            Toast.makeText(mContext,"Adding signin Failed.", Toast.LENGTH_SHORT).show();
        }
    }



    //Delete Mods:
    public void delete_func(String tablename, String column_name, String evalue){

        try{
            db.execSQL("DELETE FROM " + tablename + " WHERE " + column_name + " =?" , new Object[]{evalue});
        }catch(Exception e){
            Log.d(Settings.Error_logTag,e.toString());
            Toast.makeText(mContext,"Deleting " + column_name + " in " + tablename + " Failed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete_all(String tablename){
        //Invoke with Cautious!
        //Will empty whole table.
        try{
            db.execSQL("DELETE FROM " + tablename);
        }catch(Exception e){
            Log.d(Settings.Error_logTag,e.toString());
            Toast.makeText(mContext,"Deleteing Table " + tablename + " Failed.", Toast.LENGTH_SHORT).show();
        }
    }



    //Update Mods:
    public void update_table(String table_name, String column_name, String condition_column,String oldvalue, String newvalue){

        try{

            db.execSQL("UPDATE " + table_name + " SET " + column_name + " =? " + "WHERE " + condition_column + " =?",
                    new Object[]{newvalue,oldvalue});
        }catch(Exception e){
            Log.d(Settings.Error_logTag,e.toString());
            Toast.makeText(mContext,"Updating Table " + table_name + " Failed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void userdef_update(String update_SQL, String[] values){

        try{
            db.execSQL(update_SQL,values);

        }catch(Exception e){
            Log.d(Settings.Error_logTag, e.toString());
            Toast.makeText(mContext,"Updating Table " + " Failed.", Toast.LENGTH_SHORT).show();
        }

    }

    //Basic - Query Mods:
    public List<Object> query_info(String table_name, String query_column, String value){

        List<Object> ret = new ArrayList<Object>();
        try {
            if (query_column.length() == 0) {
                //Query-all
                Cursor cursor = null;
                switch (table_name) {

                    case "user":
                        cursor = db.rawQuery("SELECT * FROM user", null);
                        while (cursor.moveToNext()) {

                            User user = new User();
                            user.user_id = cursor.getInt(0);
                            user.username = cursor.getString(1);
                            user.userpass = cursor.getString(2);
                            user.point = cursor.getInt(3);
                            user.first_sign = cursor.getString(4);
                            ret.add(user);
                        }
                        cursor.close();
                        return ret;
                     

                    case "schedule":
                        cursor = db.rawQuery("SELECT * FROM schedule", null);
                        while (cursor.moveToNext()) {
                            Schedule schedule = new Schedule();
                            schedule.sche_id = cursor.getInt(0);
                            schedule.user_id = cursor.getInt(1);
                            schedule.title = cursor.getString(2);
                            schedule.content = cursor.getString(3);
                            schedule.year = cursor.getInt(4);
                            schedule.month = cursor.getInt(5);
                            schedule.day = cursor.getInt(6);
                            schedule.hour = cursor.getInt(7);
                            schedule.minute = cursor.getInt(8);
                            schedule.type = cursor.getInt(9);
                            ret.add(schedule);
                        }
                        cursor.close();
                        return ret;
                     

                    case "signaward":
                        cursor = db.rawQuery("SELECT * FROM signaward", null);
                        while (cursor.moveToNext()) {

                            signaward signaward = new signaward();
                            signaward.award_id = cursor.getInt(0);
                            signaward.award_item = cursor.getString(1);
                            signaward.point = cursor.getInt(2);
                            ret.add(signaward);
                        }
                        cursor.close();
                        return ret;
                     

                    case "exchange":
                        cursor = db.rawQuery("SELECT * FROM exchange", null);
                        while (cursor.moveToNext()) {

                            exchange exchange = new exchange();
                            exchange.ex_id = cursor.getInt(0);
                            exchange.user_id = cursor.getInt(1);
                            exchange.award_id = cursor.getInt(2);
                            exchange.exchange_date = cursor.getString(3);
                            ret.add(exchange);
                        }
                        cursor.close();
                        return ret;
                     

                    case "signin":

                        cursor = db.rawQuery("SELECT * FROM exchange", null);
                        while (cursor.moveToNext()) {

                            signin signin = new signin();
                            signin.signin_id = cursor.getInt(0);
                            signin.user_id = cursor.getInt(1);
                            signin.sign_date = cursor.getString(2);
                            ret.add(signin);
                        }
                        cursor.close();
                        return ret;

                     

                    default:

                        Log.d(Settings.Error_logTag, "Query With Wrong Table Name: " + table_name);
                        return null;
                }

            } else {
                Cursor cursor = null;
                switch (table_name) {

                    case "user":
                        cursor = db.rawQuery("SELECT * FROM user " + " WHERE " + query_column + " =?", new String[]{value});
                        while (cursor.moveToNext()) {

                            User user = new User();
                            user.user_id = cursor.getInt(0);
                            user.username = cursor.getString(1);
                            user.userpass = cursor.getString(2);
                            user.point = cursor.getInt(3);
                            user.first_sign = cursor.getString(4);
                            ret.add(user);
                        }
                        cursor.close();
                        return ret;
                     

                    case "schedule":
                        cursor = db.rawQuery("SELECT * FROM schedule " + " WHERE " + query_column + " =?", new String[]{value});
                        while (cursor.moveToNext()) {
                            Schedule schedule = new Schedule();
                            schedule.sche_id = cursor.getInt(0);
                            schedule.user_id = cursor.getInt(1);
                            schedule.title = cursor.getString(2);
                            schedule.content = cursor.getString(3);
                            schedule.year = cursor.getInt(4);
                            schedule.month = cursor.getInt(5);
                            schedule.day = cursor.getInt(6);
                            schedule.hour = cursor.getInt(7);
                            schedule.minute = cursor.getInt(8);
                            schedule.type = cursor.getInt(9);
                            ret.add(schedule);
                        }
                        cursor.close();
                        return ret;
                     

                    case "signaward":
                        cursor = db.rawQuery("SELECT * FROM signaward" + " WHERE " + query_column + " =?", new String[]{value});
                        while (cursor.moveToNext()) {

                            signaward signaward = new signaward();
                            signaward.award_id = cursor.getInt(0);
                            signaward.award_item = cursor.getString(1);
                            signaward.point = cursor.getInt(2);
                            ret.add(signaward);
                        }
                        cursor.close();
                        return ret;
                     

                    case "exchange":
                        cursor = db.rawQuery("SELECT * FROM exchange" + " WHERE " + query_column + " =?", new String[]{value});
                        while (cursor.moveToNext()) {

                            exchange exchange = new exchange();
                            exchange.ex_id = cursor.getInt(0);
                            exchange.user_id = cursor.getInt(1);
                            exchange.award_id = cursor.getInt(2);
                            exchange.exchange_date = cursor.getString(3);
                            ret.add(exchange);
                        }
                        cursor.close();
                        return ret;
                     

                    case "signin":

                        cursor = db.rawQuery("SELECT * FROM exchange" + " WHERE " + query_column + " =?", new String[]{value});
                        while (cursor.moveToNext()) {

                            signin signin = new signin();
                            signin.signin_id = cursor.getInt(0);
                            signin.user_id = cursor.getInt(1);
                            signin.sign_date = cursor.getString(2);
                            ret.add(signin);
                        }
                        cursor.close();
                        return ret;

                     

                    default:

                        Log.d(Settings.Error_logTag, "Query With Wrong Table Name: " + table_name);
                        return null;
                }
            }
        }
        catch(Exception e){
            Log.d(Settings.Error_logTag,e.toString());
            Toast.makeText(mContext,"Query-Info At User Table Failed.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }



    //Self-DEFINED Query Func---
    public List<Object> userdef_query(String table_name, String Query_SQL, String[] values){

        List<Object> ret = new ArrayList<Object>();
        //WARNING: Using This Func needs to be with caution!
        try {
            Cursor cursor = null;


                switch (table_name) {

                    case "user":
                        cursor = db.rawQuery(Query_SQL, values);
                        while(cursor.moveToNext()){

                            User user = new User();
                            user.user_id = cursor.getInt(0);
                            user.username = cursor.getString(1);
                            user.userpass = cursor.getString(2);
                            user.point = cursor.getInt(3);
                            user.first_sign = cursor.getString(4);

                        }
                        cursor.close();
                        return ret;

                    case "schedule":

                        cursor = db.rawQuery(Query_SQL, values);
                        while (cursor.moveToNext()) {
                            Schedule schedule = new Schedule();
                            schedule.sche_id = cursor.getInt(0);
                            schedule.user_id = cursor.getInt(1);
                            schedule.title = cursor.getString(2);
                            schedule.content = cursor.getString(3);
                            schedule.year = cursor.getInt(4);
                            schedule.month = cursor.getInt(5);
                            schedule.day = cursor.getInt(6);
                            schedule.hour = cursor.getInt(7);
                            schedule.minute = cursor.getInt(8);
                            schedule.type = cursor.getInt(9);
                            ret.add(schedule);
                        }
                        cursor.close();
                        return ret;

                    case "signaward":

                        cursor = db.rawQuery(Query_SQL, values);
                        while (cursor.moveToNext()) {

                            signaward signaward = new signaward();
                            signaward.award_id = cursor.getInt(0);
                            signaward.award_item = cursor.getString(1);
                            signaward.point = cursor.getInt(2);
                            ret.add(signaward);
                        }
                        cursor.close();
                        return ret;

                    case "exchange":

                        cursor = db.rawQuery(Query_SQL, values);
                        while (cursor.moveToNext()) {

                            exchange exchange = new exchange();
                            exchange.ex_id = cursor.getInt(0);
                            exchange.user_id = cursor.getInt(1);
                            exchange.award_id = cursor.getInt(2);
                            exchange.exchange_date = cursor.getString(3);
                            ret.add(exchange);
                        }
                        cursor.close();
                        return ret;

                    case "signin":

                        cursor = db.rawQuery(Query_SQL, values);
                        while (cursor.moveToNext()) {

                            signin signin = new signin();
                            signin.signin_id = cursor.getInt(0);
                            signin.user_id = cursor.getInt(1);
                            signin.sign_date = cursor.getString(2);
                            ret.add(signin);
                        }
                        cursor.close();
                        return ret;

                    default:

                        Log.d(Settings.Error_logTag, "Error with SelfDef Query");
                        return null;
                }

        }catch(Exception e){
            Log.d(Settings.Error_logTag,"Error When SelfDef Query At Table : " + table_name);
            return null;
        }
    }

    public void close_db(){

        db.close();
    }
}
