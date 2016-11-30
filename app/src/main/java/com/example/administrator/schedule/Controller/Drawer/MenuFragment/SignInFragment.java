package com.example.administrator.schedule.Controller.Drawer.MenuFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.Controller.SignReward.Data.DateRange;
import com.example.administrator.schedule.Controller.SignReward.Data.DateWrapper;
import com.example.administrator.schedule.Controller.SignReward.Data.ExchangedRecordLab;
import com.example.administrator.schedule.Controller.SignReward.Utils.CalendarUtils;
import com.example.administrator.schedule.Controller.SignReward.SignIn.SignInContract;
import com.example.administrator.schedule.Controller.SignReward.Month.MonthFragment;
import com.example.administrator.schedule.Controller.SignReward.SignIn.SignInPresenter;
import com.example.administrator.schedule.Controller.SignReward.Store.StoreFragment;
import com.example.administrator.schedule.Controller.SignReward.Utils.ViewGroupUtils;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignInFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment implements SignInContract.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG_FRAGMENT = "fragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView mPointsTextView;
    private Button mSignButton;
    private ViewPager mViewPager;
    private SignInContract.Presenter mPresenter;

    private DateWrapper mDateWrapper = DateWrapper.getToday();
    final int currentYear = mDateWrapper.getYear();
    final int currentMonth = mDateWrapper.getMonth();
    final int currentDay = mDateWrapper.getDay();
    private int mCurrentPosition;
    private DateRange mDateRange = DateRange.getDateRange();
    private MyPagerAdapter mMyPagerAdapter;

    public static boolean ischanged = false;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter hana.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signInView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        mPointsTextView = (TextView) signInView.findViewById(R.id.points_text_view);

        mPresenter = new SignInPresenter(this);

        mViewPager = (ViewPager)signInView.findViewById(R.id.viewPager);
        mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                int datePosition = position + 1;
                int[] date = CalendarUtils.DatePositionToDate(datePosition);
                int year = date[0];
                int month = date[1];
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(CalendarUtils.formatDate(year, month));
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        mSignButton = (Button)signInView.findViewById(R.id.sign_button);
        mSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentMonth();
                mPresenter.signToday();
            }
        });
        Button exchangeButton = (Button)signInView.findViewById(R.id.exchange_button);
        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter.checkTodaySigned() != 1) {
                    Toast.makeText(getActivity(), "Please sign first!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StoreFragment storeFragment = new StoreFragment();
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), storeFragment, TAG_FRAGMENT)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button todayButton = (Button) signInView.findViewById(R.id.todayButton);
        todayButton.setText(Integer.toString(currentDay));
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentMonth();
            }
        });
        mPresenter.getPoints();
        setCurrentMonth();
        mPresenter.checkTodaySigned();
        return signInView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMyPagerAdapter != null && ischanged) {
            ExchangedRecordLab.getExchangedRecordLab().loadExchangedRecords();
            mMyPagerAdapter.clearFragment();
            mPresenter.getPoints();
            setCurrentMonth();
            mPresenter.checkTodaySigned();

            ischanged = false;
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setCurrentMonth() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(CalendarUtils.formatDate(currentYear, currentMonth));
        int currentDatePosition = CalendarUtils.DateToDatePosition(currentYear, currentMonth);
        mViewPager.setCurrentItem(currentDatePosition - 1);
    }

    @Override
    public void updateViewAfterSign(int points) {
//        ImageView signStarImageView = (ImageView) mViewPager.findViewById(R.id.monthFragmentLayout).findViewById(R.id.todayView).findViewById(R.id.sign_star);
//        signStarImageView.setImageResource(R.drawable.star);
//
//        ImageView signStarImageView = (ImageView) getActivity().findViewById(R.id.todayView).findViewById(R.id.starImageView);
//        signStarImageView.setVisibility(View.VISIBLE);
        LayoutInflater inflater = (LayoutInflater)getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View oldView = getActivity().findViewById(R.id.todayView);
        View newView = inflater.inflate(R.layout.grid_item_sign, null);
        TextView dayTextView = (TextView)newView.findViewById(R.id.dayTextView);
        dayTextView.setTextColor(getResources().getColor(R.color.colorToday));
        dayTextView.setText(Integer.toString(currentDay));
        newView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f) );

        ViewGroupUtils.replaceView(oldView, newView);
        mSignButton.setEnabled(false);
        mPointsTextView.setText(Integer.toString(points));
    }

    @Override
    public void setPointsView(int points) {
        String pointsStr = Integer.toString(points);
        mPointsTextView.setText(pointsStr);
    }


    @Override
    public void updateSignButton(boolean isEnable) {
        mSignButton.setEnabled(isEnable);
    }


    public void updateFragment() {
        mMyPagerAdapter.clearFragment();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private HashMap<Integer, Fragment> mFragmentMap;
        private FragmentManager mFragmentManager;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            this.mFragmentManager = fm;
            mFragmentMap = new HashMap<>();
        }

        @Override
        public Fragment getItem(int position) {
            if (!mFragmentMap.containsKey(position)) {
                int datePosition = position + 1;
                mFragmentMap.put(position, MonthFragment.newInstance(datePosition));
            }
            return mFragmentMap.get(position);
        }

        @Override
        public int getCount() {
            return mDateRange.getCount();
        }

        public void clearFragment() {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            for (Integer key :
                    mFragmentMap.keySet()) {
                    ft.remove(mFragmentMap.get(key));
                    mFragmentMap.put(key, MonthFragment.newInstance(key+1));
            }
            ft.commit();
            mFragmentManager.executePendingTransactions();
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
