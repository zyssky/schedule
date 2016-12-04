package com.example.administrator.schedule.Activity.Drawer.MenuFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.schedule.Controller.TodaySchedule.TodayController;
import com.example.administrator.schedule.Controller.TodaySchedule.TodayListener;
import com.example.administrator.schedule.Util.Format;
import com.example.administrator.schedule.*;
import com.example.administrator.schedule.View.TodayView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class TodayFragment extends android.support.v4.app.Fragment implements TodayListener {

    private TodayView todayView;
    private TodayController todayController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //    private static final String ARG_PARAM1 = "param1";
    //    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TodayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TodayFragment.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        todayView = (TodayView)getView();
        todayView.init();
        todayController = new TodayController(todayView,this);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Format.formatDay(CalendarDay.today()));
    }


    @Override
    public Context getActivityContext(){
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (ScheduleHandler.userIsChange){
//            schedulerhandler = ScheduleHandler.getInstance();
//            ScheduleHandler.userIsChange = false;
//        }
        todayView.init();
        todayController.updateView();
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
                todayController.deleteItem();
                break;
            case R.id.cancle:
                // TODO: 2016/11/20
                todayController.cancleSelected();
                break;
            case R.id.finish:
                todayController.finishItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
