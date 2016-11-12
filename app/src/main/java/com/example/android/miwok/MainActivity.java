/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    /**
     * Helper that creates the OnClick listener to start eaach of the activity on the main screen
     *
     * @param id - View id
     * @param c  - Activity class to execute onClick
     */
    private void createOnClickListener(int id, final Class c)
    {
        // Find the View that shows the numbers category
        TextView myView = (TextView) findViewById(id);

        // Set a click listener on that View
        myView.setOnClickListener(new View.OnClickListener()
        {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MainActivity.this, c);
                startActivity(myIntent);
            }
        });

    }

    /**
     * Override the onCreat method to define this app's activities
     * @param savedInstanceState The bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);


        // OnClickListener For:
        createOnClickListener(R.id.activity_pager, PagerViewActivity.class);    //      This app using the activity pager
        createOnClickListener(R.id.activity_tab  , TabPagerActivity.class);    //      This app using the activity pager
        createOnClickListener(R.id.numbers       , NumbersActivity.class);      //      Numbers
        createOnClickListener(R.id.family        , FamilyActivity.class);       //      Family Members
        createOnClickListener(R.id.colors        , ColorsActivity.class);       //      Colors
        createOnClickListener(R.id.phrases       , PhrasesActivity.class);      //      Phrases
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
