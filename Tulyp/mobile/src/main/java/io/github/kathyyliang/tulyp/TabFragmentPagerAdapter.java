package io.github.kathyyliang.tulyp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kathyyliang on 4/20/16.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Day", "Week", "Month", "Year" };
    private Context context;
    private String patientID;

    public TabFragmentPagerAdapter(FragmentManager fm, Context context, String patientID) {
        super(fm);
        this.context = context;
        this.patientID = patientID;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1, patientID);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
