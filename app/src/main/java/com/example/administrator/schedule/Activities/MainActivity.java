package com.example.administrator.schedule.Activities;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.administrator.schedule.Fragments.AboutFragment;
import com.example.administrator.schedule.Fragments.CalendarFragment;
import com.example.administrator.schedule.Fragments.ClockArrangementFragment;
import com.example.administrator.schedule.Fragments.SignInFragment;
import com.example.administrator.schedule.*;
import com.example.administrator.schedule.Fragments.TodayFragment;
import com.example.administrator.schedule.Models.Schedule;
import com.example.administrator.schedule.Models.User;
import com.example.administrator.schedule.Models.dbOpt;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,
        OnFragmentInteractionListener{

    public Toolbar toolbar;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Init DB.
        dbOpt.mContext = this;
        dbOpt dbopt = new dbOpt();
        dbOptThread dbInitializer = new dbOptThread(dbopt);
        dbInitializer.start();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Button login_btn = (Button) navigationView.getHeaderView(0).findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_main, new SignInFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        Fragment fragment = null;

        if (id == R.id.sign_in) {
            fragment = new SignInFragment();

        } else if (id == R.id.clock) {
            fragment = new ClockArrangementFragment();

        } else if (id == R.id.today) {
            fragment = new TodayFragment();
        } else if (id == R.id.canlendar) {
            fragment = new CalendarFragment();
        } else if (id == R.id.assigment) {

        } else if (id == R.id.tour) {

        }else if (id == R.id.setting) {

        }else if (id == R.id.about) {
            fragment = new AboutFragment();
        }

        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK){
//            if(fragment instanceof CalendarFragment)
//                ((CalendarFragment)fragment).OnActivityInteraction(data.getExtras());
//        }
//    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {

    }

}


class dbOptThread extends Thread{

    dbOpt dbopt = null;
    Context context = null;

    public dbOptThread(dbOpt dbopt){

        this.dbopt = dbopt;
    }
    @Override
    public void run(){

//        Schedule s1 = new Schedule(1,"Test","Content",2016,12,13,13,57,1);
//        Schedule s2 = new Schedule(1,"Tester","Contentt",2016,12,13,13,57,1);
//        Schedule s3 = new Schedule(1,"Testerr","Contenttt",2016,12,13,13,57,1);
//      Fake-Register:
        User user    = new User("0xcc","since2016",0,"2016-11-21 08:21:57");
        User user2   = new User("zyssky","123456",0,"2016-11-21 12:22:46");
        User user3   = new User("y2k2016","123456",0,"2016-11-21 15:20:17");
//        User user4   = new User("tester","since2016",0,"2016-11-21 15:20:57");
        dbopt.add_user(user);
        dbopt.add_user(user2);
        dbopt.add_user(user3);
        dbopt.close_db();
//        dbopt.add_user(user4);
//        dbopt.delete_func("user","username","0xcc");
//        dbopt.update_table("user","username","username","tester","0xcc");
//        List<Object> users = dbopt.query_info("user","","anything");

//        dbopt.add_schedule(s1);
//        dbopt.add_schedule(s2);
//        dbopt.add_schedule(s3);
//        dbopt.delete_func("schedule","title","Test");
//        dbopt.update_table("schedule","title","title","Tester","0xcc");
//        List<Object> schedules = dbopt.query_info("schedule","","anything");
//        for(Object tempsc: schedules){
//            Schedule temp = (Schedule) tempsc;
//            Log.d("[*]Have-User:" , temp.title);
//            Log.d("[*]Have-User:" , temp.content);
//            Log.d("[*]Have-User:" , ""+temp.year);
//
//        }
    }

}