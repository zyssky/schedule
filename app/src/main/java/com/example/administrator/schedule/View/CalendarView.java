package com.example.administrator.schedule.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Util.MyArrayAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


public class CalendarView extends FrameLayout {
    private MaterialCalendarView calendarView;
    private ListView listView;
    private Button addBtn;
    private Button todayBtn;


    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        calendarView = (MaterialCalendarView) findViewById(R.id.calendar);
        listView = (ListView) findViewById(R.id.calendar_list);
        addBtn = (Button) findViewById(R.id.add_but);
        todayBtn = (Button) findViewById(R.id.today);


        calendarView.setTopbarVisible(false);
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        calendarView.allowClickDaysOutsideCurrentMonth();
        calendarView.setSelectedDate(CalendarDay.today());
        calendarView.setDynamicHeightEnabled(true);
    }

    public void setCalendarViewListener(OnDateSelectedListener listener1, OnMonthChangedListener listener2){
        calendarView.setOnDateChangedListener(listener1);
        calendarView.setOnMonthChangedListener(listener2);
    }
    public void setListViewAdapter(MyArrayAdapter adapter){listView.setAdapter(adapter);}
    public void setListViewOnLongClickListener(AdapterView.OnItemLongClickListener listener){ listView.setOnItemLongClickListener(listener);}
    public void setListViewOnClickListener(AdapterView.OnItemClickListener listener){ listView.setOnItemClickListener(listener);}
    public void setAddBtnListener(OnClickListener listener){ addBtn.setOnClickListener(listener);}
    public void setTodayBtnListener(OnClickListener listener){ todayBtn.setOnClickListener(listener);}

    public void setCalendarViewSelectedDay(CalendarDay day){
        calendarView.setCurrentDate(day);
        calendarView.setSelectedDate(day);
    }
}
