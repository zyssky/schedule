package com.example.administrator.schedule.Controller.Assignment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.View.AssignmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class AssignmentController {
    private AssignmentListener listener;
    private AssignmentView view;
    private GridViewAdapter adapter;

    public AssignmentController(AssignmentView view,AssignmentListener listener){
        this.view = view;
        this.listener = listener;
        initValueOnView();
    }

    void initValueOnView(){
        List list = new ArrayList<String>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(listener.getAcitvityContext());
        int count = Integer.parseInt(sharedPreferences.getString("class_count","9"));
        for(int i =0;i<7*count;i++)
            list.add("");
        adapter = new GridViewAdapter(listener.getAcitvityContext(), R.id.class_detail,list);
        adapter.setInflater(listener.getAcitvityContext().getLayoutInflater());
        view.setTableAdapter(adapter);
    }
}
