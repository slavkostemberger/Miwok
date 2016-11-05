package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class MiwokBase extends AppCompatActivity
{
    /**
     * All sub-classes must override the onCreate and addWords methods
     */


    /**
     * Define class variables
     */

        // The word list has to be final because it is used in an anonymous class
    final ArrayList<Word> mWords = new ArrayList<Word>();
    private int mCategoryColor = R.drawable.color_red;  // The background color for the activity set by a subclass

    private MediaPlayer mMediaPlayer;   // Media player player - So we can manage it properly
    private AudioManager mAudioManager; // Audio Focus handling

    final AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener()
            {
                public void onAudioFocusChange(int focusChange)
                {
trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = " + focusChange);
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
                    {
                        /**
                         * Note to self
                         * It appears that AUDIOFOCUS_LOSS_TRANSIENT at the end of the sound
                         * sets the sound object to null. If I call the pause() method
                         * I end up with a null exception!!!
                         */
                        trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_LOSS_TRANSIENT");
                        // Pause playback and reset to start of audio file
//                        mMediaPlayer.pause();
                        // For music, you would continue from where you seft off
                        // But in this case, we want to resetart so we need to point
                        // to the begining of the file
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                        // In this case, we wnat ot handel it the same was as for\
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                    {
trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_GAIN");
                        // Resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                    {
trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_GAIN_TRANSIENT");
                        // Resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
                    {
trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                        // Pause playback - we don't want to make it quieter only
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
                    {
                        /**
                         * I am NOT getting this when the audio is completed!!!
                         */
                        trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_LOSS");
                        // Stop playback if playing and clean-up media player stuff
                        releaseMediaPlayer();
                    } else
                    {
                        trace("MiwokBase.OnCreate.OnAudioFocusChangeListener Audio Change listener returned unhandeled code " + focusChange);
                    }
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup {@link AudioManager} to request audio manager
trace("MiwokBase.OnCreate Getting AudioManager");
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        //mWords = new ArrayList<Word>();
        addWords();                 // Set up the required word translations (redefined at the subclass level)

        // Dump word list to log
/*
        for(int i = 0; i < mWords.size(); ++i)
        {
            trace("Word at index " + i + ": " + mWords.get(i));
        }
*/
        WordAdapter adapter = new WordAdapter(this, mWords, mCategoryColor);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
trace("MiwokBase.OnCreate.onItemClick ");
                releaseMediaPlayer(); // Just in case it has not been release yet

                //
                // Ask for audio focus
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

trace("MiwokBase.OnCreate.onItemClick requestAudioFocus result = " + result);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
trace("MiwokBase.OnCreate.onItemClick requestAudioFocus result wa AUDIO_REQUEST_GRANTED ");
                    // We have audio focus - Start playback.
                    mMediaPlayer = MediaPlayer.create(MiwokBase.this,
                            mWords.get(position).getSoundResource());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mMediaCompleted);
                }

            }
        });

    }

    /**
     * Save the background colour for the activity (set in a sub-class
     * @param activityColor - Background colour
     */
    protected void setActivityColor(int activityColor)
    {
        mCategoryColor = activityColor;
    }

    /**
     * This methid set up the translation words
     * It MUST be over-ridden in the sub-class
     */
    protected void addWords()
    {
        // trace("MworkBase.addWords");
        mWords.add(new Word("red", "Bug in MiwokBase.addWord", R.drawable.color_red, R.raw.color_red));
    }


    /**
     * Debug helper - display a log message
     * @param msg - message to display
     */
    protected void trace(String msg)
    {
            // The getClass().getName() returns the class name (sub-class in this case)
        Log.v(this.getClass().getName(), msg);
            // Code to display a message on the screen for a short time, should we decide that it would be useful
        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer()
    {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null)
        {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
trace("MiwokBase.releaseMediaPlayer mMediaPlayer != null");
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Abandon the audio focus when playback is completed (or interrupted)
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);

        }
else {    trace("MiwokBase.releaseMediaPlayer mMediaPlayer != null");}
    }

    /**
     * Create variable to hold the on complete media listener
     */
    MediaPlayer.OnCompletionListener mMediaCompleted = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
trace("MiwokBase.OnCreate.onCompletion");
            // trace("Media player complete - releaseing ...");
            mediaPlayer.release();
            mMediaPlayer = null;
        }
    };

    @Override
    protected void onStop()
    {
trace("MiwokBase.onStop ============================");
        super.onStop();
        releaseMediaPlayer();
    }

}
