package com.example.administrator.schedule.Controller.Tour;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.example.administrator.schedule.R;
import com.example.administrator.schedule.View.TourView;
import com.baidu.mapapi.SDKInitializer;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class TourController implements View.OnClickListener,AdapterView.OnItemClickListener,ControllerListener{
    TourListener listener;
    TourView view;
    private ArrayAdapter adapter;
    private Routes routes;
    private NavigationHandler navigationhandler;

    public static String TAG = TourController.class.getSimpleName();

    public TourController(TourView view,TourListener listener){
        this.listener = listener;
        this.view = view;
        this.routes = new Routes();
        adapter = new ArrayAdapter<String>(listener.getActivityContext(), R.layout.route_list_item,routes.getShowList());
        initValueOnView();
    }

    private void initValueOnView(){
        view.setSearchListener(this);
        view.setListViewClickListener(this);
        view.setListViewAdapter(adapter);
        navigationhandler = new NavigationHandler(this);
        new MyAsyncTask().doInBackground(listener.getActivityContext());
    }


    @Override
    public void onClick(View v) {
        navigationhandler.search(view.getSourceText(),view.getDestText());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void handlerResult(List list) {
        List<TransitRouteLine> result = (List<TransitRouteLine>) list;
        routes.update(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return listener.getActivityContext();
    }
}
