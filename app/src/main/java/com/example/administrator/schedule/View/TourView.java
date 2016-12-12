package com.example.administrator.schedule.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.administrator.schedule.R;

/**
 * Created by Administrator on 2016/12/11.
 */

public class TourView extends FrameLayout {
    private EditText dest;
    private EditText source;
    private Button search;
    private ListView listView;

    public TourView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        dest = (EditText) findViewById(R.id.dest);
        source = (EditText) findViewById(R.id.source);
        search = (Button) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.route_list);
    }

    public void setSearchListener(OnClickListener listener){ search.setOnClickListener(listener);}
    public String getDestText(){ return dest.getText().toString();}
    public String getSourceText(){ return source.getText().toString();}
    public void setListViewAdapter(ListAdapter adapter){ listView.setAdapter(adapter);}
    public void setListViewClickListener(AdapterView.OnItemClickListener listener){ listView.setOnItemClickListener(listener);}
}
