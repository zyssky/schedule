package com.example.administrator.schedule.Controller.SignReward.RewardDialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Controller.SignReward.Data.ExchangedRecordLab;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RewardDialogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RewardDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EXCHANGEDPOSITON = "arg_exchangedPosition";

    // TODO: Rename and change types of parameters
    private int mExchangedPosition;
    private ViewPager mRewardViewPager;

    private OnFragmentInteractionListener mListener;

    public RewardDialogFragment() {
        // Required empty public constructor
    }

    public static RewardDialogFragment newInstance(int exchangedPosition) {
        RewardDialogFragment fragment = new RewardDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EXCHANGEDPOSITON, exchangedPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExchangedPosition = getArguments().getInt(ARG_EXCHANGEDPOSITON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View rewardView = inflater.inflate(R.layout.fragment_reward_dialog, container, false);

        mRewardViewPager = (ViewPager)rewardView.findViewById(R.id.rewardViewPager);
        FragmentManager fm = getChildFragmentManager();
        mRewardViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return RewardContentFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return ExchangedRecordLab.getExchangedRecordLab().getSize();
            }
        });
        mRewardViewPager.setCurrentItem(mExchangedPosition);
        return rewardView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(window.getAttributes().width, 1200);
        window.setGravity(Gravity.CENTER);
    }
}
