package com.example.administrator.schedule.Controller.TodaySchedule;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.schedule.Activity.ScheduleDetail.ScheduleDetailActivity;
import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Util.KEY;
import com.example.administrator.schedule.Util.MyArrayAdapter;
import com.example.administrator.schedule.Models.Schedule.ScheduleHandler;
import com.example.administrator.schedule.View.TodayView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import static android.content.ContentValues.TAG;

/**
 * Created by wand on 2016/12/2.
 */

public class TodayController implements View.OnClickListener,AdapterView.OnItemLongClickListener,AdapterView.OnItemClickListener{

    private TodayListener todayListener;
    private TodayView todayView;
    private ScheduleHandler schedulehandler;
    private MyArrayAdapter adapter;

    public TodayController(TodayView view, TodayListener listener){

        this.todayView      = view;
        this.todayListener  = listener;
        initValuesOnView();
    }

    public void initValuesOnView(){

        schedulehandler = ScheduleHandler.getInstance(CalendarDay.today());
        adapter = new MyArrayAdapter(todayListener.getActivityContext(), R.layout.list_item,schedulehandler.getList());
        todayView.setAdd_buttonListener(this);
        todayView.setListViewAdapter(adapter);
        todayView.setListViewOnLongClickListener(this);
        todayView.setListViewOnClickListener(this);
    }

    // delete all selected item
    public void deleteItem(){
        schedulehandler.deleteSchedules();
        changeAdapter(false);
    }

    public void cancleSelected(){
        changeAdapter(false);
    }

    // set the status of each item
    public void finishItem(){
        schedulehandler.finishSchedules();
        if(ScheduleHandler.isMultiSelected == true)
            changeAdapter(false);
    }

    // show the checkbox when longclick the item
    private void changeAdapter(boolean mode){
        ScheduleHandler.isMultiSelected = mode;
        adapter = new MyArrayAdapter(adapter);
        todayView.setListViewAdapter(adapter);
        if(!mode)
            schedulehandler.cleanSelected();
    }

    // it is called every time when the fragmentview is resume
    public void updateView(){
        schedulehandler = ScheduleHandler.getInstance();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_but_today:
                todayListener.getActivityContext().startActivity(new Intent(todayListener.getActivityContext(),ScheduleDetailActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position , long id){
        if(ScheduleHandler.isMultiSelected){
            schedulehandler.addSelectedSchedule(position);
            adapter.onClickWithUiChange(view);
        }
        else
            todayListener.getActivityContext().startActivity(new Intent(todayListener.getActivityContext(),ScheduleDetailActivity.class).putExtra(KEY.SCHEDULE_POSITION,position));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){

        Log.d(TAG, "onItemLongClick: hahahahahahhaahhahahahahahaah");
        ScheduleHandler.isMultiSelected = true;
        adapter = new MyArrayAdapter(adapter);
        todayView.setListViewAdapter(adapter);
        return true;
    }

}
