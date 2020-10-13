package com.elejandria.app.elejandria.adapters;

import android.content.Context;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.ui.collection.CollectionReadingFragment;

import java.util.Collection;

import androidx.annotation.Nullable;
        import androidx.annotation.StringRes;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentPagerAdapter;


public class TabsCollectionPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] { R.string.tab_collection_enprogreso, R.string.tab_collection_quieroleer, R.string.tab_collection_leídos };
    private final Context mContext;

    public TabsCollectionPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CollectionReadingFragment.newInstance();
            case 1:
                return CollectionReadingFragment.newInstance();
            case 2:
                return CollectionReadingFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}