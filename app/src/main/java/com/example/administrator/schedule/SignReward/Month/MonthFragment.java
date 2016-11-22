package com.example.administrator.schedule.SignReward.Month;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.SignReward.Data.AwardResource;
import com.example.administrator.schedule.SignReward.Data.DayInfo;
import com.example.administrator.schedule.SignReward.RewardDialog.RewardDialogFragment;

import java.util.ArrayList;

/**
 * Created by nyq on 2016/11/20.
 */

public class MonthFragment extends Fragment implements MonthContract.View{
    private static final String EXTRA_POSITION = "com.example.administrator.schedule.View.position";
    private int mDatePosition;
    private  static final String DIALOG_REWARD = "reward";

    private MonthContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View monthView = inflater.inflate(R.layout.fragment_month, container, false);

        mPresenter = new MonthPresenter(this);

        mDatePosition = getArguments().getInt(EXTRA_POSITION);
        ArrayList<ArrayList<DayInfo>> dayInfoMatrix = mPresenter.generateDayInfoMatrix(mDatePosition);

        for (int i = 0; i < dayInfoMatrix.size(); i++) {
            LinearLayout weekLayout = new LinearLayout(getActivity());
            weekLayout.setOrientation(LinearLayout.HORIZONTAL);
            weekLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            ArrayList<DayInfo> dayInfos_week = dayInfoMatrix.get(i);
            for (int j = 0; j < dayInfos_week.size(); j++) {
                DayInfo dayInfo = dayInfos_week.get(j);
                View dayView = generateView(inflater,dayInfo);
                dayView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f) );
//                addDayViewInfo(dayView, dayInfo);
                weekLayout.addView(dayView);
            }
            ((ViewGroup) monthView).addView(weekLayout);
        }

        return monthView;
    }

    private View generateView(LayoutInflater inflater, DayInfo dayInfo) {
        View dayView;
        if (dayInfo == null) {
            dayView = inflater.inflate(R.layout.grid_item, null);
            dayView.setAlpha(0.5f);
            ((TextView) dayView.findViewById(R.id.dayTextView)).setText("");
            return dayView;
        }
        else if(!dayInfo.isSigned && dayInfo.exchangedPosition == -1) {
            dayView = inflater.inflate(R.layout.grid_item, null);
        }
        else if (dayInfo.isSigned && dayInfo.exchangedPosition == -1) {
            dayView = inflater.inflate(R.layout.grid_item_sign, null);
        }
        else if(!dayInfo.isSigned && dayInfo.exchangedPosition != -1) {
            dayView = inflater.inflate(R.layout.grid_item_award, null);
        }
        else {
            dayView = inflater.inflate(R.layout.grid_item_award_sign, null);
        }
        TextView dayTextView = (TextView) dayView.findViewById(R.id.dayTextView);
        dayTextView.setText(Integer.toString(dayInfo.day));
        if (dayInfo.isToday) {
            dayView.setId(R.id.todayView);
            dayTextView.setTextColor(getResources().getColor(R.color.colorToday));
        }
        if (dayInfo.exchangedPosition != -1) {
            setDayViewListener(dayView, dayInfo);
            ImageView awardImageView = (ImageView)dayView.findViewById(R.id.awardImageView);
            awardImageView.setImageResource(AwardResource.getAwardResource().getResourceByID(dayInfo.awardID));
        }
        return dayView;
    }

    private void setDayViewListener(View v, final DayInfo dayInfo) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                RewardDialogFragment dialog = RewardDialogFragment.newInstance(dayInfo.exchangedPosition);
                dialog.show(fm, DIALOG_REWARD);
            }
        });
    }

    public static MonthFragment newInstance(int datePosition) {
        MonthFragment monthFragment = new MonthFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_POSITION, datePosition);
        monthFragment.setArguments(bundle);
        return monthFragment;
    }
}
