package com.example.administrator.schedule.Controller.Tour;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.Log;

import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BaiduNaviManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class NavigationHandler {
    public static String TAG = NavigationHandler.class.getSimpleName();

    public static String CurrentCity = "广州";

    private ControllerListener controllerListener;

    public NavigationHandler(ControllerListener listener){
        this.controllerListener = listener;
        CurrentCity = PreferenceManager.getDefaultSharedPreferences(listener.getContext()).getString("city","广州");
    }


    private OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Log.d(TAG, "onGetTransitRouteResult: 未找到结果");
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                 transitRouteResult.getSuggestAddrInfo();
                return;
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                List<TransitRouteLine> resultlist = transitRouteResult.getRouteLines();
                controllerListener.handlerResult(resultlist);
                Log.d(TAG, "onGetTransitRouteResult: "+resultlist.size());
            }
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            Log.d(TAG, "onGetMassTransitRouteResult: ");
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

    private OnGetBusLineSearchResultListener busLineSearchResultListener = new OnGetBusLineSearchResultListener() {

        @Override
        public void onGetBusLineResult(BusLineResult busLineResult) {
            if (busLineResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Log.d(TAG, "onGetBusLineResult: 未找到结果");
            } else {

            }
        }
    };

    public void search(String start,String end) {
        if (!BaiduNaviManager.isNaviInited()) {
            Log.d(TAG, "BaiduNaviManager do not inited! ");
            return;
        }
//        String key = "712";
//        BusLineSearch busLineSearch = BusLineSearch.newInstance();
//        busLineSearch.setOnGetBusLineSearchResultListener(busLineSearchResultListener);
//        busLineSearch.searchBusLine((new BusLineSearchOption().city(CurrentCity).uid(key)));


        RoutePlanSearch routeplansearch = RoutePlanSearch.newInstance();
        routeplansearch.setOnGetRoutePlanResultListener(listener);

        PlanNode stNode = PlanNode.withCityNameAndPlaceName(CurrentCity, start);
        PlanNode enNode = PlanNode.withCityNameAndPlaceName(CurrentCity, end);

        routeplansearch.transitSearch((new TransitRoutePlanOption())
                .from(stNode)
                .city(CurrentCity)
                .to(enNode));

//        routeplansearch.destroy();
    }

}
