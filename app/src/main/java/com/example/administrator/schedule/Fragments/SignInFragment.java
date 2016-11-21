package com.example.administrator.schedule.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.schedule.R;
import com.example.administrator.schedule.SignReward.DBRepository;
import com.example.administrator.schedule.SignReward.Data.DateRange;
import com.example.administrator.schedule.SignReward.Data.CalendarUtils;
import com.example.administrator.schedule.SignReward.SignIn.SignInContract;
import com.example.administrator.schedule.SignReward.Month.MonthFragment;
import com.example.administrator.schedule.SignReward.SignIn.SignInPresenter;
import com.example.administrator.schedule.SignReward.Store.StoreFragment;

import java.util.Calendar;

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

    private Calendar mCurrentCalender = Calendar.getInstance();
    final int currentYear = mCurrentCalender.get(Calendar.YEAR);
    final int currentMonth = mCurrentCalender.get(Calendar.MONTH) + 1;
    final int currentDay = mCurrentCalender.get(Calendar.DATE) ;
    private int mCurrentPosition;
    private DateRange mDateRange = DateRange.getDateRange();

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
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
//        DBRepository.getDBRepository().setContext(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signInView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        mPointsTextView = (TextView) signInView.findViewById(R.id.points_text_view);

        mPresenter = new SignInPresenter(this);

        mViewPager = (ViewPager)signInView.findViewById(R.id.viewPager);
        FragmentManager fm = getChildFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                int datePosition = position + 1;
                return MonthFragment.newInstance(datePosition);
            }

            @Override
            public int getCount() {
                return mDateRange.getCount();
            }
        });
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
        mViewPager.setCurrentItem(0);
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
                StoreFragment storeFragment = new StoreFragment();

                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), storeFragment, TAG_FRAGMENT)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button todayButton = (Button) signInView.findViewById(R.id.todayButton);
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentMonth();
            }
        });


        mPresenter.getPoints();
        setCurrentMonth();
        mPresenter.checkSigned(CalendarUtils.getTodayDate());
        return signInView;
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
    public void updateViewAfterSign() {
//        ImageView signStarImageView = (ImageView) mViewPager.findViewById(R.id.monthFragmentLayout).findViewById(R.id.todayView).findViewById(R.id.sign_star);
//        signStarImageView.setImageResource(R.drawable.star);
        ImageView signStarImageView = (ImageView) getActivity().findViewById(R.id.todayView).findViewById(R.id.starImageView);
        signStarImageView.setVisibility(View.VISIBLE);
        mSignButton.setEnabled(false);
    }

    @Override
    public void setPointsView(int points) {
        String pointsStr = Integer.toString(points);
        mPointsTextView.setText(pointsStr);
    }


    @Override
    public void unableSignButton() {
        mSignButton.setEnabled(false);
    }
}
