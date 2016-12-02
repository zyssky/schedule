package com.example.administrator.schedule.Controller.ScheduleDetail;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TimePicker;

import com.example.administrator.schedule.Models.Schedule;
import com.example.administrator.schedule.Models.ScheduleInsatace;
import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Util.Format;

import java.util.Date;



public class ScheduleDetailController implements View.OnClickListener {
    ScheduleDetailView scheduleDetailView;
    ScheduleInsatace scheduleInsatace;
    ScheduleDetailListener scheduleDetailListener;
    int position = -1;


    public ScheduleDetailController(ScheduleDetailView view, ScheduleDetailListener listener, int position){
        scheduleDetailView = view;
        scheduleDetailListener = listener;
        scheduleInsatace = new ScheduleInsatace(position);
        this.position = position;
        initValuesOnView();
    }

    // initialize the values of each wigets
    private void initValuesOnView(){
        scheduleDetailView.setPickTimeButtonText(Format.getTime(scheduleInsatace.getTime()));
        scheduleDetailView.setTitleEditText(scheduleInsatace.getTitle());
        scheduleDetailView.setContentEditText(scheduleInsatace.getContent());
        scheduleDetailView.setPickTypeButtonIcon(scheduleInsatace.getType());
        scheduleDetailView.setPickTimeButtonListener(this);
        scheduleDetailView.setPickTypeButtonListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pick_time:
                showSelectTimeDialog();
                break;
            case R.id.pick_type:
                showSelectTypeDialog();
                break;
        }
    }

    // when user click the type button and show the dialog
    private void showSelectTypeDialog(){
        new AlertDialog.Builder(scheduleDetailListener.getContext()).setTitle("选择日程类型").setIcon(R.drawable.ic_track_changes_black_24dp).setItems(R.array.choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scheduleInsatace.setType(which);
                scheduleDetailView.setPickTypeButtonIcon(which);
            }
        }).show();
    }

    // show the selected time dialog
    private void showSelectTimeDialog(){
        new TimePickerDialog(scheduleDetailListener.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                scheduleInsatace.setTime(hourOfDay,minute);
                scheduleDetailView.setPickTimeButtonText(Format.getTime(scheduleInsatace.getTime()));
            }
        }, new Date().getHours(),new Date().getMinutes(),true).show();
    }

    // save when user click the save button
    public void save(){
        scheduleInsatace.setTitle(scheduleDetailView.getTitleEditTextText());
        scheduleInsatace.setContent(scheduleDetailView.getContentEditTextText());
        scheduleInsatace.save(position);
        scheduleDetailListener.onQuit();
    }
}
