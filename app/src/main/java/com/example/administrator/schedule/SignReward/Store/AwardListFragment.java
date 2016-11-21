package com.example.administrator.schedule.SignReward.Store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.SignReward.Data.Award;
import com.example.administrator.schedule.SignReward.Data.AwardLab;

import java.util.ArrayList;

/**
 * Created by nyq on 2016/11/21.
 */

public class AwardListFragment extends ListFragment {
    private ArrayList<Award> mAwards;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAwards = AwardLab.getAwardLab().getAwards();
        AwardAdapter awardAdapter = new AwardAdapter(mAwards);
        setListAdapter(awardAdapter);
        setRetainInstance(true);
    }


    private class AwardAdapter extends ArrayAdapter<Award> {
        public AwardAdapter(ArrayList<Award> awards) {
            super(getActivity(), 0, awards);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_store_item, null);
            }
            Award award = getItem(position);
            TextView descriptionTextView = (TextView)convertView.findViewById(R.id.storeItemDesciptionTextView);
            descriptionTextView.setText(award.getDescription());
            TextView pointsTextView = (TextView)convertView.findViewById(R.id.storeItemPointsTextView);
            pointsTextView.setText(Integer.toString(award.getPoints()));
            ImageView imageView = (ImageView)convertView.findViewById(R.id.storeItemImageView);
            imageView.setImageResource(award.getSrc());
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AwardAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
