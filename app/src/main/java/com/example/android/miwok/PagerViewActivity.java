package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class PagerViewActivity extends AppCompatActivity
{
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        MiwokFragmentPagererAdapter adapter
            = new MiwokFragmentPagererAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // Hide the tab layout area
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setVisibility(View.GONE);

    }


    private void traceE(String msg)
    {
        Log.e(this.getClass().getName(), msg);
    }
    private void trace(String msg)
    {
        Log.i(this.getClass().getName(), msg);
    }
}

