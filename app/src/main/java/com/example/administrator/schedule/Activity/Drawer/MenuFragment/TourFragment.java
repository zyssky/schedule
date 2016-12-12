package com.example.administrator.schedule.Activity.Drawer.MenuFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.schedule.Controller.Tour.TourController;
import com.example.administrator.schedule.Controller.Tour.TourListener;
import com.example.administrator.schedule.R;
import com.example.administrator.schedule.View.TourView;

public class TourFragment extends Fragment implements TourListener{

    TourView tourView;
    TourController controller;
    public TourFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TourFragment newInstance(String param1, String param2) {
        TourFragment fragment = new TourFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tour, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tourView = (TourView) getView();
        tourView.init();
        controller = new TourController(tourView,this);
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
    public Activity getActivityContext() {
        return getActivity();
    }

    @Override
    public void showMsg(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
