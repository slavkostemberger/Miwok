package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;

public class ColorsActivity extends MiwokBase
{
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setActivityColor(R.color.category_colors);
        super.onCreate(savedInstanceState);

    }

    /**
     * Create the translation words for this class
     */
    protected void addWords()
    {
        mWords.add(new Word("red"           , "weṭeṭṭi" , R.drawable.color_red           , R.raw.color_red));
        mWords.add(new Word("green"         , "chokokki", R.drawable.color_green         , R.raw.color_green));
        mWords.add(new Word("brown"         , "ṭakaakki", R.drawable.color_brown         , R.raw.color_brown));
        mWords.add(new Word("gray"          , "ṭopoppi" , R.drawable.color_gray          , R.raw.color_gray));
        mWords.add(new Word("black"         , "kululli" , R.drawable.color_black         , R.raw.color_black));
        mWords.add(new Word("white"         , "kelelli" , R.drawable.color_white         , R.raw.color_white));
        mWords.add(new Word("dusty yellow"  , "ṭopiisә" , R.drawable.color_dusty_yellow  , R.raw.color_dusty_yellow));
        mWords.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

    }

}