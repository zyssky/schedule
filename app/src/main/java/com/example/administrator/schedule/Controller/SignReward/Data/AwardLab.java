package com.example.administrator.schedule.Controller.SignReward.Data;

import com.example.administrator.schedule.Models.SignReward.Award;
import com.example.administrator.schedule.Controller.SignReward.DBRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nyq on 2016/11/21.
 */

public class AwardLab {
    private static AwardLab sAwardLab;
    private ArrayList<Award> mAwards;

    private AwardLab() {
        mAwards = new ArrayList<>();
        DBRepository dbRepository = DBRepository.getDBRepository();
        List<Object> awards = dbRepository.querySignAward();
        if (awards == null) {
            return;
        }
        for (Object object :
                awards) {
            Award award = (Award) object;
            award.setSrc(AwardResource.getAwardResource().getResourceByID(award.getAwardID()));
            mAwards.add(award);
        }
    }

//    private AwardLab() {
//        mAwards = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Award award = new Award();
//            award.setName("star");
//            award.setDescription("This is a star");
//            award.setPoint(hana);
//            award.setSrc(R.drawable.star);
//            award.setAwardID(i);
//            mAwards.add(award);
//        }
//    }

    public static AwardLab getAwardLab() {
        if (sAwardLab == null) {
            sAwardLab = new AwardLab();
        }
        return sAwardLab;
    }
    public ArrayList<Award> getAwards() {
        return mAwards;
    }

    public void setAwards(ArrayList<Award> awards) {
        mAwards = awards;
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
