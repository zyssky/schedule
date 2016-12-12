package com.example.administrator.schedule.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.administrator.schedule.Controller.Assignment.GridViewAdapter;
import com.example.administrator.schedule.R;

/**
 * Created by Administrator on 2016/12/11.
 */
public class AssignmentView extends FrameLayout{
    private GridView table;

    public AssignmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        table = (GridView) findViewById(R.id.class_table);
    }

    public void setTableAdapter(GridViewAdapter adapter){
        table.setAdapter(adapter);
    }
}
