package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ColorsActivity extends BaseActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, setup()).commit();

    }
    protected int getMyColor()
    {
        return R.color.category_colors;
    }

    /**
     * Create the translation words for this class
     */
    protected void addWords(MiwokFragment mFragment)
    {
        mFragment.addWord(new Word("red"           , "weṭeṭṭi" , R.drawable.color_red           , R.raw.color_red));
        mFragment.addWord(new Word("green"         , "chokokki", R.drawable.color_green         , R.raw.color_green));
        mFragment.addWord(new Word("brown"         , "ṭakaakki", R.drawable.color_brown         , R.raw.color_brown));
        mFragment.addWord(new Word("gray"          , "ṭopoppi" , R.drawable.color_gray          , R.raw.color_gray));
        mFragment.addWord(new Word("black"         , "kululli" , R.drawable.color_black         , R.raw.color_black));
        mFragment.addWord(new Word("white"         , "kelelli" , R.drawable.color_white         , R.raw.color_white));
        mFragment.addWord(new Word("dusty yellow"  , "ṭopiisә" , R.drawable.color_dusty_yellow  , R.raw.color_dusty_yellow));
        mFragment.addWord(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

    }

}