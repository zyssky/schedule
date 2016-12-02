package com.example.administrator.schedule.Controller.Drawer.MenuFragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.schedule.Controller.ScheduleDetail.ScheduleDetailActivity;
import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Util.Format;
import com.example.administrator.schedule.Util.KEY;
import com.example.administrator.schedule.Util.MyArrayAdapter;
import com.example.administrator.schedule.Util.ScheduleHandler;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


public class CalendarController implements View.OnClickListener,AdapterView.OnItemClickListener,
        OnMonthChangedListener,OnDateSelectedListener,AdapterView.OnItemLongClickListener {
    private CalendarListener listener;
    private CalendarView calendarView;
    private MyArrayAdapter adapter;
    private ScheduleHandler scheduleHandler;

    public CalendarController(CalendarView view,CalendarListener listener){
        calendarView = view;
        this.listener = listener;

        initValuesOnView();
    }

    // initialize the attributes of the widgets in this view
    private void initValuesOnView(){
        scheduleHandler = ScheduleHandler.getInstance();
        adapter = new MyArrayAdapter(listener.getActivityContext(), R.layout.list_item,scheduleHandler.getList());
        calendarView.setAddBtnListener(this);
        calendarView.setCalendarViewListener(this,this);
        calendarView.setTodayBtnListener(this);
        calendarView.setListViewAdapter(adapter);
        calendarView.setListViewOnClickListener(this);
        calendarView.setListViewOnLongClickListener(this);
    }

    // delete all selected item
    public void deleteItem(){
        scheduleHandler.deleteSchedules();
        changeAdapter(false);
    }

    public void cancleSelected(){
        changeAdapter(false);
    }

    // set the status of each item
    public void finishItem(){
        scheduleHandler.finishSchedules();
        if(ScheduleHandler.isMultiSelected == true)
            changeAdapter(false);
    }

    // show the checkbox when longclick the item
    private void changeAdapter(boolean mode){
        ScheduleHandler.isMultiSelected = mode;
        adapter = new MyArrayAdapter(adapter);
        calendarView.setListViewAdapter(adapter);
        if(!mode)
            scheduleHandler.cleanSelected();
    }

    // it is called every time when the fragmentview is resume
    public void updateView(){
        scheduleHandler = ScheduleHandler.getInstance();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_but:
                listener.getActivityContext().startActivity(new Intent(listener.getActivityContext(), ScheduleDetailActivity.class));
                break;
            case R.id.today:
                ScheduleHandler.getInstance(CalendarDay.today());
                calendarView.setCalendarViewSelectedDay(CalendarDay.today());
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(ScheduleHandler.isMultiSelected){
            scheduleHandler.addSelectedSchedule(position);
            adapter.onClickWithUiChange(view);
        }
        else
            listener.getActivityContext().startActivity(new Intent(listener.getActivityContext(),ScheduleDetailActivity.class).putExtra(KEY.SCHEDULE_POSITION,position));
    }


    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        String s = Format.formatDateTitle(date.getYear(),date.getMonth());
        ((AppCompatActivity)listener.getActivityContext()).getSupportActionBar().setTitle(s);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        scheduleHandler = ScheduleHandler.getInstance(date);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ScheduleHandler.isMultiSelected = true;
        adapter = new MyArrayAdapter(adapter);
        calendarView.setListViewAdapter(adapter);
        return true;
    }

}
