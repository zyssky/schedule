package com.example.administrator.schedule.SignReward.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.administrator.schedule.SignReward.Store.FragmentBackHandler;

import java.util.List;

/**
 * Created by nyq on 2016/11/24.
 */
public class BackHandlerHelper {

    public static boolean handleBackPress(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();

        if (fragments == null) return false;

        for (int i = fragments.size() - 1; i >= 0; i--) {
            Fragment child = fragments.get(i);

            if (isFragmentBackHandled(child)) {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                    return true;
                }
            }
        }
        return true;

    }
    public static boolean handleBackPress(FragmentActivity fragmentActivity) {
        return handleBackPress(fragmentActivity.getSupportFragmentManager());
    }
    public static boolean isFragmentBackHandled(Fragment fragment) {
        return fragment != null
                && fragment.isVisible()
                && fragment.getUserVisibleHint() //for ViewPager
                && fragment instanceof FragmentBackHandler
                && ((FragmentBackHandler) fragment).onBackPressed();
    }
}

