package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends MiwokBase
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setActivityColor(R.color.category_family);
        super.onCreate(savedInstanceState);


    }

    protected void addWords()
    {
        mWords.add(new Word("father"         , "әpә"     , R.drawable.family_father         , R.raw.family_father));
        mWords.add(new Word("mother"         , "әṭa"     , R.drawable.family_mother         , R.raw.family_mother));
        mWords.add(new Word("son"            , "angsi"   , R.drawable.family_son            , R.raw.family_son));
        mWords.add(new Word("daughter"       , "tune"    , R.drawable.family_daughter       , R.raw.family_daughter));
        mWords.add(new Word("older brother"  , "taachi"  , R.drawable.family_older_brother  , R.raw.family_older_brother));
        mWords.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        mWords.add(new Word("older sister"   , "teṭe"    , R.drawable.family_older_sister   , R.raw.family_older_sister));
        mWords.add(new Word("younger sister" , "kolliti" , R.drawable.family_younger_sister , R.raw.family_younger_sister));
        mWords.add(new Word("grandmother"    , "ama"     , R.drawable.family_grandmother    , R.raw.family_grandmother));
        mWords.add(new Word("grandfather"    , "paapa"   , R.drawable.family_grandfather    , R.raw.family_grandfather));
    }

}
