package com.example.android.miwok;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PagerViewActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MiwokFragmentPagererAdapter adapter = new MiwokFragmentPagererAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
