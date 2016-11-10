package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.android.miwok.R.id.family;

public class FamilyActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, setup()).commit();


    }

    protected int getMyColor()
    {
        return R.color.category_family;
    }

    /**
     * Create the translation words for this class
     */
    protected void addWords(MiwokFragment mFragment)
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
