package com.example.administrator.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/19.
 */

public class MyAdapter extends SimpleAdapter implements OnClickWithUIChangeListener {

    private Context context;
    private List data;
    private int resource;
    private String[] from;
    private int[] to;

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.from = from;
        this.to = to;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);
        if(ScheduleHandler.isMultiSelected){
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
            checkBox.setVisibility(View.VISIBLE);
//            checkBox.setClickable(true);
        }
//        view.setOnClickListener(new onclick());
//        view.setOnLongClickListener(new onlongclick());

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
    public void onLongClickWithChange(View view) {

    }

//    class onclick implements View.OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//            if(ScheduleHandler.isMultiSelected){
//                CheckBox cb = (CheckBox) v.findViewById(R.id.check);
//                cb.setChecked(true);
//            }
//            else {
//
//            }
//        }
//    }
//
//    class onlongclick implements View.OnLongClickListener{
//
//        @Override
//        public boolean onLongClick(View v) {
//            return true;
//        }
//    }


}
