package com.example.administrator.schedule.Controller.SignReward.RewardDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Controller.SignReward.Data.ExchangedRecord;
import com.example.administrator.schedule.Controller.SignReward.Data.ExchangedRecordLab;
import com.example.administrator.schedule.Models.SignReward.Award;
import com.example.administrator.schedule.Controller.SignReward.Data.AwardLab;

/**
 * Created by nyq on 2016/11/20.
 */

public class RewardContentFragment extends Fragment {

    private static final String REWARDTYPE = "reward_type";

    private int mPosition;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rewardContentView = inflater.inflate(R.layout.fragment_reward_content, container, false);
        mPosition = getArguments().getInt(REWARDTYPE);
        ExchangedRecord exchangedRecord = ExchangedRecordLab.getExchangedRecordLab().getExchangedRecord(mPosition);

        int awardID = exchangedRecord.getAwardID();
        Award award = AwardLab.getAwardLab().getAwardByID(awardID);

        String dateStr = exchangedRecord.getDateWrapper().toDateStr();
        TextView rewardDateTextView = (TextView)rewardContentView.findViewById(R.id.rewardDateTextView);
        rewardDateTextView.setText(dateStr);

        ImageView awardImageView = (ImageView) rewardContentView.findViewById(R.id.rewardImageView);
        awardImageView.setImageResource(award.getSrc());

        TextView pointsTextView = (TextView) rewardContentView.findViewById(R.id.pointsTextView);
        pointsTextView.setText(Integer.toString(award.getPoint()));

        if (mPosition == 0) {
            ImageView leftArrowImageView = (ImageView) rewardContentView.findViewById(R.id.leftArrowImageView);
            leftArrowImageView.setVisibility(View.INVISIBLE);
        }
        if (mPosition == ExchangedRecordLab.getExchangedRecordLab().getSize() - 1) {
            ImageView rightArrowImageView = (ImageView) rewardContentView.findViewById(R.id.rightArrowImageView);
            rightArrowImageView.setVisibility(View.INVISIBLE);
        }

        return rewardContentView;
    }

    public static RewardContentFragment newInstance(int position) {
        RewardContentFragment rewardContentFragment = new RewardContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(REWARDTYPE, position);
        rewardContentFragment.setArguments(bundle);
        return rewardContentFragment;
    }
}

