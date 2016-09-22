package com.brutcode.seedapp;

/**
 * Created by Danilo on 09/09/2016.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.brutcode.seedapp.ui.DetailSmallPictureFragment;

import java.util.List;


public class PicturePagerAdapter extends FragmentPagerAdapter {

    private List<String> mImages;

    public PicturePagerAdapter(FragmentManager fm, List<String> subGroups) {
        super(fm);
        this.mImages = subGroups;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mImages.get(position);
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Fragment getItem(int position) {

        DetailSmallPictureFragment fragment = DetailSmallPictureFragment.newInstance(mImages.get(position),position, mImages );

        return fragment;
    }
}
