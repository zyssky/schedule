package com.example.administrator.schedule.Controller.Drawer.MenuFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.schedule.*;

import com.example.administrator.schedule.Util.Format;
import com.example.administrator.schedule.Util.ScheduleHandler;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class CalendarFragment extends android.support.v4.app.Fragment implements CalendarListener {

    private CalendarView calendarView;
    private CalendarController calendarController;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: 2016/12/2 with refactor of MVC
        calendarView = (CalendarView) getView();
        calendarView.init();
        calendarController = new CalendarController(calendarView,this);
        
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Format.formatDateTitle(CalendarDay.today()));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.withdelete_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                calendarController.deleteItem();
                break;
            case R.id.cancle:
                calendarController.cancleSelected();
                break;
            case R.id.finish:
                calendarController.finishItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        calendarView.init();
        calendarController.updateView();
    }

}
