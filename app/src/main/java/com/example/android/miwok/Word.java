package com.example.android.miwok;

import android.media.MediaPlayer;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by slavko on 28-Oct-2016 08:35:38PM.
 */

public class Word
{
    private int    mImageResourceId;
    private String mMiwokTranslation;
    private String mDefaulttranslation;
    private int    mSoundResourceId;
    private MediaPlayer mMediaPlayer;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     *
     * @param defaulttranslation - the default word
     * @param miwokTranslation - the Miwok word
     * @param soundResourceId - image resource id
     */
    public Word(String defaulttranslation, String miwokTranslation, int soundResourceId)
    {
        mMiwokTranslation   = miwokTranslation;
        mDefaulttranslation = defaulttranslation;
        mImageResourceId    = NO_IMAGE_PROVIDED; // Image resourses are NEVER -1
        mSoundResourceId    = soundResourceId;
    }
    public Word(String defaulttranslation, String miwokTranslation, int imageResourceId, int soundResourceId)
    {
        mMiwokTranslation   = miwokTranslation;
        mDefaulttranslation = defaulttranslation;
        mImageResourceId    = imageResourceId;
        mSoundResourceId    = soundResourceId;
    }

    /**
     *
     * @return the imager resorce id
     */
    public int getImageResourceId()
    {
        return mImageResourceId;
    }

    /**
     *
     * @return the default translation word
     */
    public String getDefaultTranslation()
    {
        return mDefaulttranslation;
    }

    /**
     *
     * @return the Miwok word
     */
    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    public int getSoundResource() {return mSoundResourceId; }

    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


    /**
     * Debug helper to sump words in an array
     * @param activityName - name of the acctivity that called this procedure
     * @param words - array list of words to dump
     */
    public static void dumpWords(String activityName, ArrayList<Word> words)
    {
        for(int i = 0; i < words.size(); ++i)
        {
            Log.v(activityName, "Word at IndexX " + i + ": " + words.get(i).getDefaultTranslation());
        }
    }

    @Override
    public String toString()
    {
        return "Word{" +
                "mImageResourceId="       + mImageResourceId +
                ", mMiwokTranslation='"   + mMiwokTranslation + '\'' +
                ", mDefaulttranslation='" + mDefaulttranslation + '\'' +
                ", mSoundResourceId="     + mSoundResourceId +
                ", mMediaPlayer="         + mMediaPlayer +
                '}';
    }
}