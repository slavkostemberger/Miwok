package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PhrasesActivity extends BaseActivity
{

    /**
     *
     * @param savedInstanceState The bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, setup()).commit();
    }
    protected int getMyColor()
    {
        return R.color.category_phrases;
    }


    /**
     * Create the translation words for this class
     * Note that this is the only activity in this app that does not need a icon/image
     */
    protected void addWords(MiwokFragment mFragment)
    {
        mFragment.addWord(new Word("Where are you going?", "minto wuksus"   , R.raw.phrase_where_are_you_going));
        mFragment.addWord(new Word("What is your name?"  , "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        mFragment.addWord(new Word("My name is..."       , "oyaaset..."     , R.raw.phrase_my_name_is));
        mFragment.addWord(new Word("How are you feeling?", "michәksәs?"     , R.raw.phrase_how_are_you_feeling));
        mFragment.addWord(new Word("I’m feeling good."   , "kuchi achit"    , R.raw.phrase_im_feeling_good));
        mFragment.addWord(new Word("Are you coming?"     , "әәnәs'aa?"      , R.raw.phrase_are_you_coming));
        mFragment.addWord(new Word("Yes, I’m coming."    , "hәә’ әәnәm"     , R.raw.phrase_yes_im_coming));
        mFragment.addWord(new Word("I’m coming."         , "әәnәm"          , R.raw.phrase_im_coming));
        mFragment.addWord(new Word("Let’s go."           , "yoowutis"       , R.raw.phrase_lets_go));
        mFragment.addWord(new Word("Come here."          , "әnni'nem"       , R.raw.phrase_come_here));
    }

}
