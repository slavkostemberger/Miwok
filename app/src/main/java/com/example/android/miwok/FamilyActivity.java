package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FamilyActivity extends AppCompatActivity
{
    MiwokFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        mFragment = new MiwokFragment();
        addWords();
        mFragment.setActivityColor(R.color.category_family);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();


    }

    /**
     * Create the translation words for this class
     */
    protected void addWords()
    {
        mFragment.addWord(new Word("father"         , "әpә"     , R.drawable.family_father         , R.raw.family_father));
        mFragment.addWord(new Word("mother"         , "әṭa"     , R.drawable.family_mother         , R.raw.family_mother));
        mFragment.addWord(new Word("son"            , "angsi"   , R.drawable.family_son            , R.raw.family_son));
        mFragment.addWord(new Word("daughter"       , "tune"    , R.drawable.family_daughter       , R.raw.family_daughter));
        mFragment.addWord(new Word("older brother"  , "taachi"  , R.drawable.family_older_brother  , R.raw.family_older_brother));
        mFragment.addWord(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        mFragment.addWord(new Word("older sister"   , "teṭe"    , R.drawable.family_older_sister   , R.raw.family_older_sister));
        mFragment.addWord(new Word("younger sister" , "kolliti" , R.drawable.family_younger_sister , R.raw.family_younger_sister));
        mFragment.addWord(new Word("grandmother"    , "ama"     , R.drawable.family_grandmother    , R.raw.family_grandmother));
        mFragment.addWord(new Word("grandfather"    , "paapa"   , R.drawable.family_grandfather    , R.raw.family_grandfather));
    }

}
