package com.example.administrator.schedule.SignReward.Data;

import java.util.HashMap;
import com.example.administrator.schedule.R;
/**
 * Created by nyq on 2016/11/22.
 */

public class AwardResource {
    private static AwardResource sAwardResource;
    private HashMap<String, Integer> mResourceNameMap;
    private HashMap<Integer, Integer> mResourceIDMap;

    private AwardResource() {
        mResourceNameMap = new HashMap<>();
        mResourceIDMap = new HashMap<>();

        mResourceNameMap.put("star", R.drawable.star);
        mResourceIDMap.put( 1 , R.drawable.star);
        mResourceIDMap.put(2, R.drawable.kale);
        mResourceIDMap.put(3, R.drawable.kazila);
    }

    public int getResourceByName(String name) {
        return mResourceNameMap.get(name);
    }

    public int getResourceByID(int id) {
        return mResourceIDMap.get(id);
    }

    public static AwardResource getAwardResource() {
        if (sAwardResource == null) {
            sAwardResource = new AwardResource();
        }
        return sAwardResource;
    }
}
