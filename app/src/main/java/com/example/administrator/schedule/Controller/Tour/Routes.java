package com.example.administrator.schedule.Controller.Tour;

import android.util.Log;

import com.baidu.mapapi.search.route.TransitRouteLine;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by Administrator on 2016/12/11.
 */

public class Routes {
    private List<String> showRoutes;

    public Routes(){

        showRoutes = new ArrayList<String>();
    }

    public void update(List<TransitRouteLine> list){

        showRoutes.clear();

        for (int i = 0;i<list.size();i++) {
            TransitRouteLine result = list.get(i);
            showRoutes.add("路线"+(i+1)+"---------------------------------------------------");
            ArrayList<String> temp = new ArrayList<String>();
            for (TransitRouteLine.TransitStep step :
                    result.getAllStep()) {
                temp.add(step.getInstructions());
                showRoutes.add(step.getInstructions());
            }
        }
    }

    List getShowList(){ return showRoutes;}
}
