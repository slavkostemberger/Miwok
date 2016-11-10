package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NumbersActivity extends AppCompatActivity
{
    MiwokFragment mFragment;
    /**
     * Created by slavko on 04-Nov-2016 11:43:32AM.
     */

    /**
     * Perform initialization of all fragments and loaders
     * @param savedInstanceState The bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        mFragment = new MiwokFragment();
        addWords();
        mFragment.setActivityColor(R.color.category_numbers);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();

    }

    /**
     * Create the translation words for this class
     */
    protected void addWords()
    {
        //trace("NumbersActivity.addWords");

        mFragment.addWord(new Word("one"  , "luti"     , R.drawable.number_one  , R.raw.number_one));
        mFragment.addWord(new Word("two"  , "otiiko"   , R.drawable.number_two  , R.raw.number_two));
        mFragment.addWord(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        mFragment.addWord(new Word("four" , "oyyisa"   , R.drawable.number_four , R.raw.number_four));
        mFragment.addWord(new Word("five" , "massokka" , R.drawable.number_five , R.raw.number_five));
        mFragment.addWord(new Word("six"  , "temmokka" , R.drawable.number_six  , R.raw.number_six));
        mFragment.addWord(new Word("seven", "kenekaku" , R.drawable.number_seven, R.raw.number_seven));
        mFragment.addWord(new Word("eight", "kawinta"  , R.drawable.number_eight, R.raw.number_eight));
        mFragment.addWord(new Word("nine" , "wo'e"     , R.drawable.number_nine , R.raw.number_nine));
        mFragment.addWord(new Word("ten"  , "na'aacha" , R.drawable.number_ten  , R.raw.number_ten));

    }
}