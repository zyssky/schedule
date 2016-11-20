package com.example.administrator.schedule.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.schedule.Activities.*;
import com.example.administrator.schedule.*;

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


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarView=(MaterialCalendarView) getView().findViewById(R.id.calendar);
        calendarView.setOnDateChangedListener(this);
        calendarView.setTopbarVisible(false);
        calendarView.setSelectedDate(CalendarDay.today());
        calendarView.setOnMonthChangedListener(this);
        calendarView.setDynamicHeightEnabled(true);
        Log.d(TAG, "onResume: "+getActivity().getLocalClassName());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Format.formatDateTitle(CalendarDay.today()));

        add_button = (Button) getView().findViewById(R.id.add_but);
        add_button.setOnClickListener(this);
        today=(Button) getView().findViewById(R.id.today);
        today.setOnClickListener(this);
        schedulerhandler = ScheduleHandler.getInstance(calendarView.getSelectedDate());
        adapter = new MyAdapter(getActivity(),schedulerhandler.loadScheduleOnDay(),R.layout.list_item,
                new String[]{KEY.SCHEDULE_NAME,KEY.SCHEDULE_TIME_DESCRIPTION},new int[]{R.id.schedule_name,R.id.schedule_time});
//        listView = (ListView) getView().findViewById(R.id.list);
//        listView.setAdapter(adapter);
        setListAdapter(adapter);
        
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemLongClick: hahahahahahhaahhahahahahahaah");
                adapter.setShowCheckbox(true);
                
                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        intent.putExtra(KEY.SCHEDULE_POSITION,position);
        startActivityForResult(intent,0);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            adapter.notifyDataSetChanged();
        }
    }

    private MaterialCalendarView calendarView;
    private ListView listView;
    private Button add_button;
    private Button today;

    private ScheduleHandler schedulerhandler;
    private MyAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
//        calendarView=(MaterialCalendarView) getView().findViewById(R.id.calendar);
//        calendarView.setOnDateChangedListener(this);
//        calendarView.setTopbarVisible(false);
//        calendarView.setSelectedDate(CalendarDay.today());
//        calendarView.setOnMonthChangedListener(this);
//        calendarView.setDynamicHeightEnabled(true);
//        Log.d(TAG, "onResume: "+getActivity().getLocalClassName());
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Format.formatDateTitle(CalendarDay.today()));
//
//        add_button = (Button) getView().findViewById(R.id.add_but);
//        add_button.setOnClickListener(this);
//        today=(Button) getView().findViewById(R.id.today);
//        today.setOnClickListener(this);

//        schedulerhandler = ScheduleHandler.getInstance(calendarView.getSelectedDate());
//        adapter = new SimpleAdapter(getActivity(),schedulerhandler.loadScheduleOnDay(),R.layout.list_item,
//                new String[]{KEY.SCHEDULE_NAME,KEY.SCHEDULE_TIME_DESCRIPTION},new int[]{R.id.schedule_name,R.id.schedule_time});
////        listView = (ListView) getView().findViewById(R.id.list);
////        listView.setAdapter(adapter);
//        setListAdapter(adapter);
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                ScheduleHandler.choose_item_position = position;
////                showDeleteItemDialog();
//                return true;
//            }
//        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(),DetailActivity.class);
//                intent.putExtra(KEY.SCHEDULE_POSITION,position);
//                startActivityForResult(intent,0);
//            }
//        });
    }

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        schedulerhandler.loadScheduleOnDay(date);
        adapter.notifyDataSetChanged();
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

    public void update(){

    }
}
