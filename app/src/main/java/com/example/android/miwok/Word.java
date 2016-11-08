package com.example.android.miwok;

/**
 * Created by Slavko on 28-Oct-2016 08:35:38PM.
 */

class Word
{


    /**
     * private variable used by this class
     */
    private       int    mImageResourceId = NO_IMAGE_PROVIDED;    // Image associated
    private final String mMiwokTranslation;                       // Miwok word phrase
    private final String mDefaulttranslation;                     // The default (English) word phrase
    private final int    mSoundResourceId;                        // Sound file id

    private static final int NO_IMAGE_PROVIDED = -1;        // Constant used to check if an image exists for this word phrase

    /**
     * Create a Word with no image associated with it
     * @param defaultTranslation - the default word
     * @param miwokTranslation   - the Miwok word
     * @param soundResourceId    - the sound file resource id for this word phrase
     */
    public Word(String defaultTranslation,
                String miwokTranslation,
                int soundResourceId
               )
    {
        mMiwokTranslation   = miwokTranslation;
        mDefaulttranslation = defaultTranslation;
        mImageResourceId    = NO_IMAGE_PROVIDED; // Image resources are NEVER -1
        mSoundResourceId    = soundResourceId;
    }

    /**
     *      * Create a Word with an image associated with it
     * @param defaultTranslation - the default word
     * @param miwokTranslation   - the Miwok word
     * @param imageResourceId    - the image id associated with this word phrase
     * @param soundResourceId    - the sound file resource id for this word phrase
     */
    public Word(String defaultTranslation,
                String miwokTranslation,
                int imageResourceId,
                int soundResourceId
               )
    {
        mMiwokTranslation   = miwokTranslation;
        mDefaulttranslation = defaultTranslation;
        mImageResourceId    = imageResourceId;
        mSoundResourceId    = soundResourceId;
    }

    /**
     *
     * @return the image resource id
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

    /**
     *
     * @return the sound for the Miwok word phrase
     */
    public int getSoundResource()       {return mSoundResourceId; }

    /**
     *
     * @return true if this word phrase has an image and
     *         false if not image exists for the word phrase
     */
    public boolean hasImage()           { return mImageResourceId != NO_IMAGE_PROVIDED; }

    /**
     *
     * @return - a string version of the Word structure
     */
    @Override
    public String toString()
    {
        return "Word{" +
                "mImageResourceId="       + mImageResourceId    + '\'' +
                ", mMiwokTranslation='"   + mMiwokTranslation   + '\'' +
                ", mDefaulttranslation='" + mDefaulttranslation + '\'' +
                ", mSoundResourceId="     + mSoundResourceId    +
                '}';
    }
}