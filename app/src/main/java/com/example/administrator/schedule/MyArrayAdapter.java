package com.example.administrator.schedule;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.administrator.schedule.Models.Schedule;


import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/11/20.
 */

public class MyArrayAdapter extends BaseAdapter implements OnClickWithUIChangeListener{
    private LayoutInflater inflater=null;
    private Context context;
    private int resource;
    private List<Schedule> objects;

    public MyArrayAdapter(Context context,int resource,List<Schedule> objects){
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MyArrayAdapter(MyArrayAdapter adapter){
        context = adapter.getContext();
        resource = adapter.getResource();
        objects = adapter.getObjects();
        inflater = adapter.getInflater();
    }

    public Context getContext(){return context;}

    public int getResource(){return resource;}

    public List<Schedule> getObjects(){return objects;}

    public LayoutInflater getInflater(){return inflater;}

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(resource,null);
        }
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
        TextView name = (TextView) view.findViewById(R.id.schedule_name);
        TextView time = (TextView) view.findViewById(R.id.schedule_time);


        if(ScheduleHandler.isMultiSelected){
            checkBox.setVisibility(View.VISIBLE);
        }
        else{
            checkBox.setVisibility(View.GONE);
        }
        Schedule schedule = objects.get(position);
        name.setText(schedule.title);
        if(schedule.status > 0)
            name.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        time.setText(Format.formatRemindTitle(schedule));


        return view;

    }

    @Override
    public void onClickWithUiChange(View view) {
        CheckBox cb = (CheckBox) view.findViewById(R.id.check);
        if(cb.isChecked()){
            cb.setChecked(false);
        }
        else{
            cb.setChecked(true);
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

//        objects = ScheduleHandler.getInstance().getList();
        objects = ScheduleHandler.getInstance().getList();

    }

    @Override
    public void onLongClickWithChange(View view) {

    }
}
