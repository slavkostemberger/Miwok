package com.example.android.miwok;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by slavko on 09-Nov-2016 08:21:05PM.
 */

public class MiwokFragmentPagererAdapter extends FragmentPagerAdapter
{

    public MiwokFragmentPagererAdapter(FragmentManager fm)
    {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position)
    {
        trace("MiwokFragmentPagererAdapter.getItem.position = " + position);
        switch(position)
        {
            case 0: return new NumbersActivity().setup();
            case 1: return new FamilyActivity().setup();
            case 2: return new ColorsActivity().setup();
            case 3: return new PhrasesActivity().setup();
            default: return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch(position)
        {
            case 0:
                return Resources.getSystem().getString(R.string.category_numbers);
            case 1:
                return Resources.getSystem().getString(R.string.category_family);
            case 2:
                return Resources.getSystem().getString(R.string.category_colors);
            case 3:
                return Resources.getSystem().getString(R.string.category_phrases);
            default: return "Undefined page";
        }
    }

    void trace(String msg)
    {
        Log.e(this.getClass().getName(), msg);

    }

}
