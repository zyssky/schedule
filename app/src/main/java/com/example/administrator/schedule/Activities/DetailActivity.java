package com.example.administrator.schedule.Activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.administrator.schedule.*;

import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    public final String TAG = DetailActivity.class.getSimpleName();
    private Intent intent;

    private EditText schedule_title;
    private EditText schedule_content;
    private Button pick_time_btn;

//    private int hour;
//    private int minute;
//    private String title;
//    private String content;
    private Schedule schedule;
    private int position;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context=this;

        pick_time_btn = (Button) findViewById(R.id.pick_time);
        schedule_title = (EditText) findViewById(R.id.schedule_title);
        schedule_content = (EditText) findViewById(R.id.schedule_content);

        pick_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        schedule.hour = hourOfDay;
                        schedule.minute = minute;
                        pick_time_btn.setText(Format.formatRemindTitle(hourOfDay,minute));
                    }
                }, new Date().getHours(),new Date().getMinutes(),true).show();
            }
        });

        intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        position = -1;

        if(bundle!=null) {
//            hour = bundle.getInt(KEY.SCHEDULE_START_HOUR);
//            minute = bundle.getInt(KEY.SCHEDULE_START_MINUTE);
//            activity_content_edittext.setText(bundle.getString(KEY.SCHEDULE_NAME));
//            pick_time_btn.setText(Format.formatRemindTitle(hour, minute));
            position = bundle.getInt(KEY.SCHEDULE_POSITION);
            schedule = ScheduleHandler.getInstance().getSchedule(position);

            displaySchedule(position);
        }
        else{
            int year = ScheduleHandler.getInstance().year;
            int month = ScheduleHandler.getInstance().month;
            int day = ScheduleHandler.getInstance().day;
            schedule = new Schedule(year,month,day,0,0,"","");
        }

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
                schedule.title = schedule_title.getText().toString();
                schedule.content = schedule_content.getText().toString();
                if(position>=0)
                    ScheduleHandler.getInstance().updateSchedule(position, schedule);
                else
                    ScheduleHandler.getInstance().addSchedule(schedule);
//                intent.putExtra(KEY.SCHEDULE_START_HOUR, hour);
//                intent.putExtra(KEY.SCHEDULE_START_MINUTE, minute);
//                intent.putExtra(KEY.SCHEDULE_NAME, activity_content_edittext.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySchedule(int position){
        Schedule schedule = ScheduleHandler.getInstance().getSchedule(position);
        // TODO: 2016/11/7 set the coresponding text to the widget
        schedule_title.setText(schedule.title);
        schedule_content.setText(schedule.content);
        pick_time_btn.setText(Format.formatDateTitle(schedule.hour,schedule.minute));
    }

//    private Schedule generateSchedule(){
//
//        return schedule;
//    }

}
