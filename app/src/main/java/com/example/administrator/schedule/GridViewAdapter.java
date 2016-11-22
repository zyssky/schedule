package com.example.administrator.schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/10/23.
 */

public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List data ;
    private LayoutInflater inflater;
    private SharedPreferences.Editor classes;

    public void setInflater(LayoutInflater inflater){
        this.inflater = inflater;
    }

    public GridViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = objects;
        classes = context.getSharedPreferences(KEY.CLASS,Context.MODE_PRIVATE).edit();
    }

    public void loadList() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY.CLASS,Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt(KEY.CLASS_SIZE,63);
        List<String> list = new ArrayList<String>();
        for(int i=0;i<size;i++){
            list.add(sharedPreferences.getString(getKey(i),""));
        }
        data = list;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate (context,R.layout.class_item, null);
        }
        final TextView textView = (TextView) convertView.findViewById(R.id.class_detail);

        textView.setText((String)data.get(position));
//        textView.setHeight(40);



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.setText("click");
                final View view = inflater.inflate(R.layout.input,null);

                new AlertDialog.Builder(context).setTitle("设置课程").setView(view).
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = (EditText)view.findViewById(R.id.input);
                                String c = editText.getText().toString();
                                textView.setText(c);
                                classes.putString(getKey(position),c);
                                classes.commit();
//                                SharedPreferences sharedPreferences = context.getSharedPreferences(KEY.CLASS,Context.MODE_PRIVATE);
//                                String s = sharedPreferences.getString(getKey(position),"cuole");
//                                loadList();
                            }
                        }).setNegativeButton("取消",null).show();

            }
        });
        return  convertView;
    }

    private String getKey(int position){
        return KEY.CLASS+"_"+position;
    }
}
