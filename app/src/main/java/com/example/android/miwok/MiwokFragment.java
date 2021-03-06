package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiwokFragment extends Fragment
{

    /**
     * Define class variables
     */

    /**
     * Define the word array to hold the translation words and image
     * Note to self: This has to be final because it is used in an anonymous class
     */
    private final ArrayList<Word> mWords = new ArrayList<>();

    /**
     * This contains the background color of the translation word area
     * defaulted to red. It should be set in the onCreate method of the subclass
     * via the setActivityColor method
     */
    private int mCategoryColor = R.drawable.color_red;  // The background color for the activity set by a subclass

    /**
     * Media Player and Audio Manager object containers
     */
    private MediaPlayer mMediaPlayer;      // Media player player - So we can manage it properly
    private AudioManager mAudioManager;     // Audio Focus handling

    /**
     * ----------------------------------------------------------------------------
     * Create the on audio focus change listener
     * Note to self: It's not clear to me yet as to ahat focus change states should
     *      be checked fo here. I've put more than what the tutorial lists as an
     *      example for me
     * ----------------------------------------------------------------------------
     */
    private final AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener
            = new AudioManager.OnAudioFocusChangeListener()
    {
        public void onAudioFocusChange(int focusChange)
        {
            //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = " + focusChange);
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_LOSS_TRANSIENT");
                /**
                 * Note to self
                 * It appears that AUDIOFOCUS_LOSS_TRANSIENT at the end of the sound
                 * sets the sound object to null when MediaPlayer is requested
                 * with AUDIOFOCUS_GAIN_TRANSIENT.
                 * If I call the pause() method I end up with a null exception!!!
                 *
                 * Base on above observation, I need to release the media player
                 * This behaviour needs to be verified
                 */
                if (mMediaPlayer != null)
                {
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                }
                // releaseMediaPlayer();

                // Pause playback and reset to start of audio file
                // mMediaPlayer.pause();
                // For music, you would continue from where you seft off
                // But in this case, we want to resetart so we need to point
                // to the beginning of the file
                //mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                // In this case, we want to handel it the same was as for AUDIOFOCUS_LOSS_TRANSIENT
                if (mMediaPlayer != null)
                {
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else
                {
                    traceE("Medial Player Not Configured (mMediaPlayer is null)");
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_GAIN"); /* */
                /**
                 * It appears that when I get this callback. I've lost the
                 * media player. The following exception is raised
                 * java.lang.NullPointerException: Attempt to invoke virtual method 'void android.media.MediaPlayer.start()' on a null object reference
                 *
                 * The mMediaPlayer variable is somtimes null
                 */
                // Resume playback
                //D trace("AUDIOFOCUS_GAIN   mMediaPlayer = " + mMediaPlayer);
                //D trace("AUDIOFOCUS_GAIN  mAudioManager = " + mAudioManager);
                //D trace("AUDIOFOCUS_GAIN mCategoryColor = " + mCategoryColor);
                if (mMediaPlayer != null)
                {
                    mMediaPlayer.start();
                } else
                {
                    traceE("Medial Player Not Configured (mMediaPlayer is null)");
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            {
                //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_GAIN_TRANSIENT"); /* */
                // Resume playback
                if (mMediaPlayer != null)
                {
                    mMediaPlayer.start();
                } else
                {
                    traceE("Medial Player Not Configured (mMediaPlayer is null)");
                }

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
            {
                //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                // Pause playback - we don't want to make it quieter only
                if (mMediaPlayer != null)
                {
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else
                {
                    traceE("Medial Player Not Configured (mMediaPlayer is null)");
                }

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                /**
                 * I am NOT getting this when the audio is completed!!!
                 */
                //D trace("MiwokBase.OnCreate.OnAudioFocusChangeListener focustChange = AUDIOFOCUS_LOSS");
                // Stop playback if playing and clean-up media player stuff
                releaseMediaPlayer();
            } else
            {
                traceE("MiwokBase.OnCreate.OnAudioFocusChangeListener Audio Change listener returned unhandeled code " + focusChange);
            }
        }
    };

    /**
     * ----------------------------------------------------------------------------
     * Create the on complete media listener
     * Question: Should the audio focus change listener be abandoned as well?
     * ----------------------------------------------------------------------------
     */
    private final MediaPlayer.OnCompletionListener mMediaCompleted
        = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            //D trace("MiwokBase.OnCreate.onCompletion");
            releaseMediaPlayer();
        }
    };

    /**
     * OnItemClickListener "holder" - this keeps it out of the onCreate method,
     * hopefully keeping the method more readable
     */
    private final AdapterView.OnItemClickListener mOnItemClickListener
            = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //D trace("MiwokBase.OnCreate.onItemClick ");
            //releaseMediaPlayer(); // Just in case it has not been release yet

            //
            // Ask for audio focus
            int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                    // Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    // Request permanent focus.
                    AudioManager.AUDIOFOCUS_GAIN);

            //D trace("MiwokBase.OnCreate.onItemClick requestAudioFocus result = " + result);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
            {
                //D traceE("MiwokBase.OnCreate.onItemClick requestAudioFocus result wa AUDIO_REQUEST_GRANTED ");
                // We have audio focus
                // If mMediaPlayer is not null then we have interrupted our own playback
                // In this case we will NEVER want to re-start, so we can just release the resource
                releaseMediaPlayer();
                // No we can start again on a new file
                mMediaPlayer = MediaPlayer.create(getActivity(), mWords.get(position).getSoundResource());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mMediaCompleted);
            }

        }
    };

    public MiwokFragment()
    {
        // Required empty public constructor
    }

    /**
     * Notes to self
     * 1) onCreate is replace by onCreateView
     * 2) Add line:
     *    TextView textView = new TextView(getActivity());
     * 3) Change
     *    ListView listView = (ListView) findViewById(R.id.list);
     *  to
     *    ListView listView = (ListView) rootView.findViewById(R.id.list);
     *  and
     *    AudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
     *  to
     *    mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
     *  and
     *    WordAdapter adapter = new WordAdapter(this, mWords, mCategoryColor);
     *  to
     *    WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
     *  and (above in the OnItemClickListener definition)
     *    mMediaPlayer = MediaPlayer.create(MiwokBase.this, mWords.get(position).getSoundResource());
     *  to
     *    mMediaPlayer = MediaPlayer.create(getActicity(), mWords.get(position).getSoundResource());
     */
    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup {@link AudioManager} to request audio manager
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //D dumpWords();            // Dump word list to log
        WordAdapter adapter = new WordAdapter(getActivity(), mWords, mCategoryColor);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mOnItemClickListener);


 // -------------------------------------------------------------------------------------------------
//        getActivity().setContentView(R.layout.word_list);

        return rootView;
    }

    /**
     *
     * @param wd word object
     */
    public void addWord(Word wd)
    {
        mWords.add(wd);
    }
/*
    void addWords()
    {
        //D trace("MworkBase.addWords");
        mWords.add(new Word("red", "Bug in MiwokBase.addWord", R.drawable.color_red, R.raw.color_red));
    }
*/
    /**
     * Save the background colour for the activity (set in a sub-class
     * @param activityColor - Background colour
     */
    public void setActivityColor(int activityColor)
    {
        mCategoryColor = activityColor;
    }

    /**
     * Debug helper - display a log message
     * @param msg - message to display
     */
    private void trace(String msg)
    {
        // Note to self: The getClass().getName() returns the class name
        //               (sub-class in this case)
        Log.v(this.getClass().getName(), msg);

        // Note to self: Code to display a message on the screen
        //               for a short time, should we decide that
        //               it would be useful
        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Write an "error" message to the log
     * @param msg - Message to logged
     */
    private void traceE(String msg)
    {
        // This generated red lines in the log file
        Log.e(this.getClass().getName(), msg);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer()
    {
        // If the media player is not null, then it may be currently playing a sound.
        //D traceE("--> releaseMediaPlayer");
        if (mMediaPlayer != null)
        {
            //D traceE("--: mMediaPlayer = " + mMediaPlayer + " <---------------------------");

            // Abandon the audio focus when playback is completed (or interrupted)
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);

            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            //D trace("MiwokBase.releaseMediaPlayer mMediaPlayer != null");
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

        }
        //D else trace("MiwokBase.releaseMediaPlayer mMediaPlayer != null");
        //D traceE("<-- releaseMediaPlayer");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Debug helper to dump words in an array
     */
    public void dumpWords()
    {
        for(int i = 0; i < mWords.size(); ++i)
        {
            trace("Word at IndexX " + i + ": " + mWords.get(i).toString());
        }
    }
}

