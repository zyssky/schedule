package com.example.administrator.schedule.SignReward.Store;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Models.Award;
import com.example.administrator.schedule.SignReward.Data.AwardLab;
import com.example.administrator.schedule.SignReward.Test;
import com.example.administrator.schedule.SignReward.Utils.BackHandlerHelper;

import java.util.ArrayList;

/**
 * Created by nyq on 2016/11/21.
 */

public class StoreFragment extends ListFragment implements StoreContract.View, FragmentBackHandler{
    private ArrayList<Award> mAwards;
    private StoreContract.Presenter mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAwards = AwardLab.getAwardLab().getAwards();
        AwardAdapter awardAdapter = new AwardAdapter(mAwards);
        setListAdapter(awardAdapter);
//        setRetainInstance(true);
        mPresenter = new StorePresenter(this);
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
            final Award award = getItem(position);
            TextView descriptionTextView = (TextView)convertView.findViewById(R.id.storeItemDesciptionTextView);
            descriptionTextView.setText(award.getDescription());
            TextView pointsTextView = (TextView)convertView.findViewById(R.id.storeItemPointsTextView);
            pointsTextView.setText(Integer.toString(award.getPoint()));
            ImageView imageView = (ImageView)convertView.findViewById(R.id.storeItemImageView);
            imageView.setImageResource(award.getSrc());

            Button storeItemExchangeButton = (Button) convertView.findViewById(R.id.storeItemExchangeButton);
            storeItemExchangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mPresenter.checkExchangedToday()) {
                        return;
                    }

                    int currentPoints = mPresenter.getPoint();
                    if (currentPoints < award.getPoint()) {
                        alert("Insufficient Integral", "Sorry, your integral is insufficient!", android.R.drawable.ic_dialog_alert);
                    }
                    else {
                        currentPoints = currentPoints - award.getPoint();
                        new AlertDialog.Builder(getContext())
                                .setTitle("Tip!")
                                .setMessage("It will cost you " + Integer.toString(award.getPoint()) + " points.")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        boolean isSuccess = mPresenter.dealExchange(award);
                                        if (isSuccess) {
                                            alert("Congratulation!", "You got an award!", android.R.drawable.btn_star);
                                        }
                                        else {
                                            alert("Failed!", "Please try again!", android.R.drawable.ic_dialog_alert);
                                        }

                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setIcon(android.R.drawable.btn_star)
                                .show();
                    }
                }
            });
            return convertView;
        }
    }

    private void alert(String title, String message, int iconID) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setIcon(iconID)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
//        new Test().addAwards();
        ((AwardAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showNoExchangeToady() {
        alert("Sorry", "Sorry! You have exchanged today. Please wait for tomorrow!", android.R.drawable.btn_star);
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
