package com.example.administrator.schedule.Activity.ScheduleDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.schedule.*;
import com.example.administrator.schedule.Controller.ScheduleDetail.ScheduleDetailController;
import com.example.administrator.schedule.Controller.ScheduleDetail.ScheduleDetailListener;
import com.example.administrator.schedule.Util.KEY;
import com.example.administrator.schedule.View.ScheduleDetailView;


public class ScheduleDetailActivity extends AppCompatActivity implements ScheduleDetailListener {

    private ScheduleDetailController scheduleDetailController;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO: 2016/12/1 with refactor of MVC
        ScheduleDetailView scheduleDetailView = (ScheduleDetailView)  findViewById(R.id.schedule_detail);
        scheduleDetailView.init();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
            position = bundle.getInt(KEY.SCHEDULE_POSITION);
        else
            position = -1;
        scheduleDetailController = new ScheduleDetailController(scheduleDetailView,this,position);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.confirm:
                scheduleDetailController.save();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuit() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

}
