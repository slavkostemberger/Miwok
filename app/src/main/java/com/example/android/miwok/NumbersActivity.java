package com.example.android.miwok;

import android.os.Bundle;

/**
 * Created by slavko on 04-Nov-2016 11:43:32AM.
 */

public class NumbersActivity extends MiwokBase
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setActivityColor(R.color.category_numbers);
        super.onCreate(savedInstanceState);

    }

    protected void addWords()
    {
        trace("NumbersActivity.addWords");

        mWords.add(new Word("one"  , "luti"     , R.drawable.number_one  , R.raw.number_one));
        mWords.add(new Word("two"  , "otiiko"   , R.drawable.number_two  , R.raw.number_two));
        mWords.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        mWords.add(new Word("four" , "oyyisa"   , R.drawable.number_four , R.raw.number_four));
        mWords.add(new Word("five" , "massokka" , R.drawable.number_five , R.raw.number_five));
        mWords.add(new Word("six"  , "temmokka" , R.drawable.number_six  , R.raw.number_six));
        mWords.add(new Word("seven", "kenekaku" , R.drawable.number_seven, R.raw.number_seven));
        mWords.add(new Word("eight", "kawinta"  , R.drawable.number_eight, R.raw.number_eight));
        mWords.add(new Word("nine" , "wo'e"     , R.drawable.number_nine , R.raw.number_nine));
        mWords.add(new Word("ten"  , "na'aacha" , R.drawable.number_ten  , R.raw.number_ten));

    }

}