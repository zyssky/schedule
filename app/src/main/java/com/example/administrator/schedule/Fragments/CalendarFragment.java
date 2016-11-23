package com.example.administrator.schedule.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.schedule.Activities.*;
import com.example.administrator.schedule.*;

import com.example.administrator.schedule.Models.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class CalendarFragment extends ListFragment implements OnActivityInteractionListener,View.OnClickListener,OnMonthChangedListener,OnDateSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarView=(MaterialCalendarView) getView().findViewById(R.id.calendar);
        calendarView.setOnDateChangedListener(this);
        calendarView.setTopbarVisible(false);
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        calendarView.allowClickDaysOutsideCurrentMonth();
        calendarView.setSelectedDate(CalendarDay.today());
        calendarView.setOnMonthChangedListener(this);
        calendarView.setDynamicHeightEnabled(true);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Format.formatDateTitle(CalendarDay.today()));
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayOptions();

        add_button = (Button) getView().findViewById(R.id.add_but);
        add_button.setOnClickListener(this);
        today=(Button) getView().findViewById(R.id.today);
        today.setOnClickListener(this);
        schedulerhandler = ScheduleHandler.getInstance();
        adapter = new MyArrayAdapter(getActivity(),R.layout.list_item,schedulerhandler.getList());
        setListAdapter(adapter);
        
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemLongClick: hahahahahahhaahhahahahahahaah");
                ScheduleHandler.isMultiSelected = true;
                adapter = new MyArrayAdapter(adapter);
                setListAdapter(adapter);
//                schedulerhandler.selectedList.add(new ScheduleHandler.Index(position));
//                adapter.onClickWithUiChange(view);
                return true;
            }
        });


    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(ScheduleHandler.isMultiSelected){
//            ScheduleHandler.Index index = new ScheduleHandler.Index(position);
            schedulerhandler.addSelectedSchedule(position);
            adapter.onClickWithUiChange(v);
        }
        else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(KEY.SCHEDULE_POSITION, position);
            startActivityForResult(intent, 0);
        }
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
                // TODO: 2016/11/20
                schedulerhandler.deleteSchedules();
                if(ScheduleHandler.isMultiSelected ==true)
                    changeAdapter(false);
                break;
            case R.id.cancle:
                // TODO: 2016/11/20
                if(ScheduleHandler.isMultiSelected == true)
                    changeAdapter(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeAdapter(boolean mode){
        ScheduleHandler.isMultiSelected = mode;
        adapter = new MyArrayAdapter(adapter);
        setListAdapter(adapter);
        schedulerhandler.getList();
        if(!mode)
            schedulerhandler.cleanSelected();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            adapter.notifyDataSetChanged();
        }
    }

    private MaterialCalendarView calendarView;

    private Button add_button;
    private Button today;

    private ScheduleHandler schedulerhandler;
    private MyArrayAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Bundle bundle) {
        if (mListener != null) {
            mListener.onFragmentInteraction(bundle);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_but:
                startActivityForResult(new Intent(getActivity(),DetailActivity.class),1);
                break;
            case R.id.today:
                CalendarDay currentmonth = CalendarDay.today();
                calendarView.setCurrentDate(currentmonth);
                calendarView.setSelectedDate(currentmonth);
                ScheduleHandler.getInstance(currentmonth);
                adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        schedulerhandler=ScheduleHandler.getInstance(date);
        adapter.notifyDataSetChanged();
//        List<Schedule> l = adapter.getObjects();
//        Log.d(TAG, "onDateSelected: "+l.size());
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        String s = Format.formatDateTitle(date.getYear(),date.getMonth());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(s);
    }

    @Override
    public void OnActivityInteraction(Bundle bundle) {
        adapter.notifyDataSetChanged();

    }

}
