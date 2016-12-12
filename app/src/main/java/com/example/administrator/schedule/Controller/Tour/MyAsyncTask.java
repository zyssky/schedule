package com.example.administrator.schedule.Controller.Tour;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/12/11.
 */

public class MyAsyncTask extends AsyncTask<Activity,Integer,Object>{
    @Override
    protected Object doInBackground(Activity...activities) {
        activity = activities[0];
        if(!BaiduNaviManager.isNaviInited())
            initBaiduMap();
//        test();
        return null;
    }

    private Activity activity;


    String authinfo = "";

    private void initBaiduMap(){
        String mSDCardPath = getSdcardDir();
        String app_folder_name = "schedule";
        makedir(mSDCardPath,app_folder_name);

        SDKInitializer.initialize(activity.getApplicationContext());
        BaiduNaviManager.getInstance().init(activity, mSDCardPath, app_folder_name, newNaviInitlistener(),null,ttsHandler,null);
        SDKInitializer.initialize(activity.getApplicationContext());
    }

    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    private void initSetting(){
        // 设置是否双屏显示
        BNaviSettingManager.setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
        // 设置导航播报模式
        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
        // 是否开启路况
        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
    }

    public static boolean makedir(String mSDCardPath,String folder_name){
        File f = new File(mSDCardPath, folder_name);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private BaiduNaviManager.NaviInitListener newNaviInitlistener(){
        BaiduNaviManager.NaviInitListener listener = new BaiduNaviManager.NaviInitListener() {
            @Override
            public void onAuthResult(int i, String s) {
                if (0 == i) {
                    authinfo = "key校验成功!";
                } else {
                    authinfo = "key校验失败, " + s;
                }
                showToastMsg(authinfo);
            }

            @Override
            public void initStart() {
                showToastMsg( "百度导航引擎初始化开始");
            }

            @Override
            public void initSuccess() {
                showToastMsg("百度导航引擎初始化成功");
                initSetting();
            }

            @Override
            public void initFailed() {
                showToastMsg("百度导航引擎初始化失败");
            }
        };
        return listener;
    }

    /**
     * 内部TTS播报状态回传handler
     */
    private Handler ttsHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.what;
            switch (type) {
                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
                    showToastMsg("Handler : TTS play start");
                    break;
                }
                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
                    showToastMsg("Handler : TTS play end");
                    break;
                }
                default :
                    break;
            }
        }
    };

    private void showToastMsg(String message){
//        Toast.makeText(listener.getActivityContext(),message,Toast.LENGTH_SHORT);
//        listener.showMsg(message);
        Log.d("baidmap!!!!!!!!!!!!!!", "showToastMsg: "+message);
    }

    private void test(){
        try {
            URL url =new URL("http://www.scut.edu.cn/");
            URLConnection connection = url.openConnection();
            connection.connect();
        } catch (Exception e) {
            Log.d("test!!!!!!!!!", "test: "+e.toString());
            e.printStackTrace();
        }
    }
}
