package com.example.administrator.schedule.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Util.MyArrayAdapter;

import java.util.List;

/**
 * Created by wand on 2016/12/2.
 */

public class TodayView extends FrameLayout{

    private Button add_button;
    private ListView today_list;
    //private ListView list;
    public TodayView(Context context, AttributeSet attributeSet){

        super(context,attributeSet);
    }

    public void init(){

        add_button = (Button)findViewById(R.id.add_but_today);
        today_list = (ListView)findViewById(R.id.today_list);
        Log.d("[*]Init View " , "aaa");
    }

    public void setAdd_buttonListener(OnClickListener listener) {add_button.setOnClickListener(listener);}

    public void setListViewAdapter(MyArrayAdapter adapter){today_list.setAdapter(adapter);}

    public void setListViewOnLongClickListener(AdapterView.OnItemLongClickListener listener){ today_list.setOnItemLongClickListener(listener);}

    public void setListViewOnClickListener(AdapterView.OnItemClickListener listener){ today_list.setOnItemClickListener(listener);}
}
