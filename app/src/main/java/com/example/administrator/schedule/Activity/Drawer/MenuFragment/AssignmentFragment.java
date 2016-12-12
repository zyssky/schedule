package com.example.administrator.schedule.Activity.Drawer.MenuFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.schedule.Controller.Assignment.AssignmentController;
import com.example.administrator.schedule.Controller.Assignment.AssignmentListener;
import com.example.administrator.schedule.R;
import com.example.administrator.schedule.View.AssignmentView;


public class AssignmentFragment extends Fragment implements AssignmentListener{

    private AssignmentView view;
    private AssignmentController controller;

    public AssignmentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AssignmentFragment newInstance(String param1, String param2) {
        AssignmentFragment fragment = new AssignmentFragment();

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
        return inflater.inflate(R.layout.fragment_assignment, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (AssignmentView) getView();
        view.init();
        controller = new AssignmentController(view,this);
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
    public Activity getAcitvityContext() {
        return getActivity();
    }


}
