package au.com.codycodes.tpk.tamizhpallikoodam;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Tamil translation, and an image for that word.
 */
public class Word {

    /** Default translation for the word */
    private String defaultTranslation;

    /** Tamil translation for the word */
    private String tamilTranslation;

    /** Audio resource ID for the word */
    private int audioResourceId;

    /** Image resource ID for the word */
    private int imageResourceId = NO_IMAGE_PROVIDED;

    /** Image resource ID for the word */
    private String category;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English)
     * @param tamilTranslation is the word in the Tamil language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     * @param category is the word category
     */
    public Word(String defaultTranslation, String tamilTranslation, int audioResourceId, String category) {
        this.defaultTranslation = defaultTranslation;
        this.tamilTranslation = tamilTranslation;
        this.audioResourceId = audioResourceId;
        this.category = category;
    }

    /**
     * Create a new Word object.
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English)
     * @param tamilTranslation is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the resource ID for the audio file associated with this word
     * @param category is the word category
     */
    public Word(String defaultTranslation, String tamilTranslation, int imageResourceId, int audioResourceId, String category) {
        this.defaultTranslation = defaultTranslation;
        this.tamilTranslation = tamilTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
        this.category = category;
    }

    /**
     * Get the Deafult translation of the word.
     */
    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    /**
     * Get the Tamil translation of the word.
     */
    public String getTamilTranslation() {
        return tamilTranslation;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return imageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return audioResourceId;
    }

    /**
     * return the category for the word.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Return String of all variables
     */
    public String toString() {
        return "Default Translation: " + defaultTranslation + " Tamil Translation: " + tamilTranslation + " Image Resource id: " + imageResourceId + " Audio Resource id: " + audioResourceId + " Category: " + category;
    }
}