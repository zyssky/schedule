package com.example.administrator.schedule.SignReward.Data;

import com.example.administrator.schedule.R;

import java.util.ArrayList;

/**
 * Created by nyq on 2016/11/21.
 */

public class AwardLab {
    private static AwardLab sAwardLab;
    private ArrayList<Award> mAwards;

    private AwardLab() {
        mAwards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Award award = new Award();
            award.setDescription("This is a star");
            award.setPoints(2);
            award.setSrc(R.drawable.star);
            award.setAwardID(i);
            mAwards.add(award);
        }
    }

    public static AwardLab getAwardLab() {
        if (sAwardLab == null) {
            sAwardLab = new AwardLab();
        }
        return sAwardLab;
    }
    public ArrayList<Award> getAwards() {
        return mAwards;
    }

    public Award getAwardByID(int id) {
        Award award = null;
        for (int i = 0; i < mAwards.size(); i++) {
            award = mAwards.get(i);
            if (award.getAwardID() == id) {
                return award;
            }
        }
        return null;
    }
}
