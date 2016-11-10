package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends AppCompatActivity
{
    private boolean inOnCreate = false;
    protected int mCategoryColor = R.color.color_white;  // The background color for the activity set by a subclass


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        inOnCreate = true;
    }

    public MiwokFragment setup()
    {
        trace("--> setup");
        MiwokFragment mFragment;

        trace("--: setContentView");
        if(inOnCreate)
        {
            setContentView(R.layout.activity_category);
        }
        trace("--: MiwokFragment");
        mFragment = new MiwokFragment();
        trace("--: mFragment");
        addWords(mFragment);
        trace("--: setActivityColor");
        mFragment.setActivityColor(getMyColor());
        trace("<-- setup");
        return mFragment;
    }

    protected int getMyColor()
    {
        return R.color.color_white;
    }
    /**
     * Create the translation words for this class
     */
    protected void addWords(MiwokFragment mFragment)
    {
        mFragment.addWord(new Word("Undefined Activity"  , "Undefined Activity"     , R.drawable.number_one  , R.raw.number_one));
    }

    void trace(String msg)
    {
        // Note to self: The getClass().getName() returns the class name
        //               (sub-class in this case)
        Log.v(this.getClass().getName(), msg);

        // Note to self: Code to display a message on the screen
        //               for a short time, should we decide that
        //               it would be useful
        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
