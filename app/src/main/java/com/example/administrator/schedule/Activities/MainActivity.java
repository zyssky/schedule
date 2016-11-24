package com.example.administrator.schedule.Activities;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.Toast;

import com.example.administrator.schedule.Fragments.AboutFragment;
import com.example.administrator.schedule.Fragments.AssignmentFragment;
import com.example.administrator.schedule.Fragments.CalendarFragment;
import com.example.administrator.schedule.Fragments.ClockArrangementFragment;
import com.example.administrator.schedule.Fragments.SettingFragment;
import com.example.administrator.schedule.Fragments.SignInFragment;
import com.example.administrator.schedule.*;
import com.example.administrator.schedule.Fragments.TodayFragment;
import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.Models.Schedule;
import com.example.administrator.schedule.Models.User;
import com.example.administrator.schedule.Models.dbOpt;
import com.example.administrator.schedule.Notifications.LongRunningService;

import java.util.ArrayList;
import java.util.List;
import com.example.administrator.schedule.Notifications.*;
import com.example.administrator.schedule.SignReward.Utils.BackHandlerHelper;

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

        Intent intent = new Intent(this, LongRunningService.class);
        intent.putExtra(KEY.NEXT_ALARM,0);
        startService(intent);

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
        BackHandlerHelper.handleBackPress(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exit();
        }

    }

    private boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }

    };

    public void exit(){
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
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
            startActivity(new Intent(this,SettingsActivity.class));
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
        } else if (id == R.id.today) {
            fragment = new TodayFragment();
        } else if (id == R.id.canlendar) {
            fragment = new CalendarFragment();
        } else if (id == R.id.setting) {
            startActivity(new Intent(this,SettingsActivity.class));
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
//        switch (v.getId()){
//            case R.id.login_btn:
//                Intent intent = new Intent(this,LoginActivity.class);
//                startActivity(intent);
//                break;
//        }
    }


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

        Schedule s1 = new Schedule(1,"Test","Content",2016,12,13,13,57,1,0);
        Schedule s2 = new Schedule(1,"Tester","Contentt",2016,12,13,13,57,1,0);
        Schedule s3 = new Schedule(1,"Testerr","Contenttt",2016,12,13,13,57,1,0);
//      Fake-Register:
        User user    = new User("0xcc","since2016",0,"2016-11-21 08:21:57");
        User user2   = new User("zyssky","123456",0,"2016-11-21 12:22:46");
        User user3   = new User("y2k2016","123456",0,"2016-11-21 15:20:17");
//        User user4   = new User("tester","since2016",0,"2016-11-21 15:20:57");
        dbopt.add_user(user);
        dbopt.add_user(user2);
        dbopt.add_user(user3);
//        dbopt.close_db();
//        dbopt.add_user(user4);
//        dbopt.delete_func("user","username","0xcc");
//        dbopt.update_table("user","username","username","tester","0xcc");
//        List<Object> users = dbopt.query_info("user","","anything");

        dbopt.add_schedule(s1);
        dbopt.add_schedule(s2);
        dbopt.add_schedule(s3);
        dbopt.delete_func("schedule","title","Test");
        dbopt.update_table("schedule","title","title","Tester","0xcc");
        List<Object> schedules = ScheduleHandler.dbopt.userdef_query("schedule","SELECT * FROM schedule WHERE year=? and month=? and day=?",
                new String[]{2016+"",12+"",13+""});
        for(Object tempsc: schedules){
            Schedule temp = (Schedule) tempsc;
            Log.d("[*]Have-User:" , temp.title);
            Log.d("[*]Have-User:" , temp.content);
            Log.d("[*]Have-User:" , ""+temp.year);
        }
        if(dbopt.query_info("signaward","","").size()==0) {
            Award award1 = new Award(1, "This is a description", 1, "yinhun");
            Award award2 = new Award(2, "This is a decription", 2, "yinsan");
            Award award3 = new Award(3,"just a description", 2, "kela");
            Award award4 = new Award(4, "just a description", 2, "kazila");
            Award award5 = new Award(5, "just a description", 2, "yilisaba");
            dbopt.add_signaward(award1);
            dbopt.add_signaward(award2);
            dbopt.add_signaward(award3);
            dbopt.add_signaward(award4);
            dbopt.add_signaward(award5);
        }

        dbopt.close_db();
    }


}