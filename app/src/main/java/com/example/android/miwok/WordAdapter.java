package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by slavko on 28-Oct-2016 09:10:03PM.
 */

public class WordAdapter extends ArrayAdapter<Word>
{
    private int mColorResourceId;
    private int mSoundReourceId;
//    private MediaPlayer mMediaPlayer;

    /**
     * @param context         - View context
     * @param resource
     * @param objects
     * @param colorResourceId
     */
    public WordAdapter(Context context, int resource, List<Word> objects, int colorResourceId)
    {
        super(context, resource, objects);
        mColorResourceId = colorResourceId;
    }

    /**
     * @param context         - is the current context (activity)
     * @param objects         - is the list of word translations and appropriate image (if required)
     * @param colorResourceId - The colour of the word translation area
     */
    public WordAdapter(Context context, List<Word> objects, int colorResourceId)
    {
        super(context, 0, objects);
        mColorResourceId = colorResourceId;
    }

    public WordAdapter(Context context, List<Word> objects, int colorResourceId, int soundResourceId)
    {
        super(context, 0, objects);
        mColorResourceId = colorResourceId;
        mSoundReourceId = soundResourceId;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout for each view to be displayed

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image_view);
        View translationContainer = listItemView.findViewById(R.id.text_container);
        View textContainer = listItemView.findViewById(R.id.text_container);
        TextView miworkTextView = (TextView) listItemView.findViewById(R.id.miword_text_view);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        //
        // Set the views to the image/words
        int color = ContextCompat.getColor(getContext(), mColorResourceId);     // Get the colour for the colour resource provided
//        textContainer.setBackgroundColor(color);                                // Set the background of the word container to the specified colour
        translationContainer.setBackgroundColor(color);
        // Set up the image for the
        if (currentWord.hasImage())
        {
            // We have an image - set the image resource and make sure that the the view is visible
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        } else
        {
            // We don't have an image - hide the view so it takes up no space (INVISIBLE would
            // hide the view but still take up space
            iconView.setVisibility(View.GONE);
        }


        miworkTextView.setText(currentWord.getMiwokTranslation());              // Display the Miwok word
        defaultTextView.setText(currentWord.getDefaultTranslation());           // Display the translated word
        //Log.v("WordAddapter", "Word: " + currentWord.getDefaultTranslation());  // Log it (for now)
        return listItemView;
        //return super.getView(position, convertView, parent);
    }
/*
    protected void trace(String msg)
    {
        Log.v("WordAddapter", msg);
        //        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
*/
}
