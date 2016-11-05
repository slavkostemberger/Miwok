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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    private void defineOnClickListener(int id, final Class c)
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, "Should play sound", Toast.LENGTH_LONG).show();

        // OnClickListener For:
        defineOnClickListener(R.id.numbers, NumbersActivity.class);     //      Numbers
        defineOnClickListener(R.id.family,  FamilyActivity.class);      //      Family Members
        defineOnClickListener(R.id.colors,  ColorsActivity.class);      //      Colors
        defineOnClickListener(R.id.phrases, PhrasesActivity.class);     //      Phrases
/*
    The following is a bit redundant but would have to be used if there was work to do that was
    different for each activity.

    In that case, it ma make sense to write a method for activity to start just to keep this code clean

        // -----------------------------------------------------------------------------------------
        // OnClickListener for numbers
        // -----------------------------------------------------------------------------------------

        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener()
        {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view)
            {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });
        // -----------------------------------------------------------------------------------------
        // OnClickListener for Family Members
        // -----------------------------------------------------------------------------------------

        // Find the View that shows the numbers category
        TextView family = (TextView) findViewById(R.id.family);

        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener()
        {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view)
            {
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        // -----------------------------------------------------------------------------------------
        // OnClickListener for Colors
        // -----------------------------------------------------------------------------------------

        // Find the View that shows the numbers category
        TextView colors = (TextView) findViewById(R.id.colors);

        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener()
        {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view)
            {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });


        // -----------------------------------------------------------------------------------------
        // OnClickListener for Phrases
        // -----------------------------------------------------------------------------------------

        // Find the View that shows the numbers category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener()
        {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view)
            {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
*/
    }

}
