package com.example.android.miwok;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TabPagerActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MiwokFragmentPagererAdapter adapter
            = new MiwokFragmentPagererAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // Setup the tab viewer

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setVisibility(View.VISIBLE);          // Make sure that the tab area is visible
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * Debug Helper - log "error" message so that it show up in red in the Android Studio
     * @param msg - message to log
     */
    private void traceE(String msg)
    {
        Log.e(this.getClass().getName(), msg);
    }
    /**
     * Debug Helper - log "info" message so that it show up in red in the Android Studio
     * @param msg - message to log
     */
    private void trace(String msg)
    {
        Log.i(this.getClass().getName(), msg);
    }

}
