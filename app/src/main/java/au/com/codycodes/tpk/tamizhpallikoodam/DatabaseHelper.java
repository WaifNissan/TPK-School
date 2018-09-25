package au.com.codycodes.tpk.tamizhpallikoodam;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Random;


public class DatabaseHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "db_tpk.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create words table
        String CREATE_WORDS_TABLE = "CREATE TABLE words ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "english TEXT, "+
                "tamil TEXT, "+
                "image INTEGER, "+
                "audio INTEGER, "+
                "category TEXT )";

        // create words table
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older words table if existed
        db.execSQL("DROP TABLE IF EXISTS words");

        // create fresh words table
        this.onCreate(db);
    }

    // Words table name
    private static final String tWords = "words";

    // Words Table Column names
    private static final String kID = "id";
    private static final String kEnglish = "english";
    private static final String kTamil = "tamil";
    private static final String kImage = "image";
    private static final String kAudio = "audio";
    private static final String kCategory = "category";

    private static final String[] columns = {kID,kEnglish,kTamil,kImage,kAudio,kCategory};

    public void addWord(Word word){

        // for logging
        Log.d("addWord", word.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(kEnglish, word.getDefaultTranslation()); // get Default Translation
        values.put(kTamil, word.getTamilTranslation()); // get Tamil Translation
        values.put(kImage, word.getImageResourceId()); // get Image resource id
        values.put(kAudio, word.getAudioResourceId()); // get Audio Resource id
        values.put(kCategory, word.getCategory()); // get word category

        db.insert(tWords,null, values);

        db.close();
    }

    public ArrayList<Word> getWords(String category){
        ArrayList<Word> words = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tWords, columns," category = ?", new String[] { String.valueOf(category) },null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                words.add(new Word(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5)));
            } while (cursor.moveToNext());
        }

        db.close();

        return words;
    }


    // Options //
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public Quiz getQuiz(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor question = db.rawQuery("SELECT id, english, tamil, image, audio, category " + "FROM words " + "WHERE id >= (abs(random()) % (SELECT max(id) FROM words)) LIMIT 1", null);
        if (question.moveToFirst()) {
            Cursor options = db.rawQuery("SELECT DISTINCT english " + "FROM words " + "WHERE id >= (abs(random()) % (SELECT COUNT(id) FROM words WHERE category = ? AND id != ?)) AND category = ? AND id != ? ORDER BY english LIMIT 0,4", new String [] { question.getString(5), question.getString(0), question.getString(5), question.getString(0)});

            if(options != null && options.moveToFirst()) {
                option1 = options.getString(0);
                if(options.moveToNext()) {
                    option2 = options.getString(0);
                }
                if(options.moveToNext()) {
                    option3 = options.getString(0);
                }
                if(options.moveToLast()) {
                    option4 = options.getString(0);
                }
            }

            Random r = new Random();

            switch(r.nextInt(4)) {
                case 0: option1 = question.getString(1); break;
                case 1: option2 = question.getString(1); break;
                case 2: option3 = question.getString(1); break;
                case 3: option4 = question.getString(1); break;
            }

            options.close();

            Quiz q = new Quiz(question.getString(2), question.getString(1), question.getString(5), option1, option2, option3, option4, Integer.parseInt(question.getString(3)), Integer.parseInt(question.getString(4)));
            return q;
        }

        db.close();
        return null;
    }

    public void populateWords() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM words", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 0) {

            // Colors //
            addWord(new Word("white", "வெள்ளை", R.drawable.color_white, R.raw.colors_white, "Colors"));
            addWord(new Word("gray", "சாம்பல்", R.drawable.color_gray, R.raw.colors_gray, "Colors"));
            addWord(new Word("black", "கருப்பு", R.drawable.color_black, R.raw.colors_black, "Colors"));
            addWord(new Word("red", "சிவப்பு", R.drawable.color_red, R.raw.colors_red, "Colors"));
            addWord(new Word("blue", "நீலமான", R.drawable.color_blue, R.raw.colors_blue, "Colors"));
            addWord(new Word("yellow", "மஞ்சள்", R.drawable.color_yellow, R.raw.colors_yellow, "Colors"));
            addWord(new Word("green", "பச்சை", R.drawable.color_green, R.raw.colors_green, "Colors"));
            addWord(new Word("brown", "பழுப்பு", R.drawable.color_brown, R.raw.colors_brown, "Colors"));

            // Numbers //
            addWord(new Word("one", "ஒன்று", R.drawable.number_1, R.raw.numbers_one, "Numbers"));
            addWord(new Word("two", "இரண்டு", R.drawable.number_2, R.raw.numbers_two, "Numbers"));
            addWord(new Word("three", "மூன்று", R.drawable.number_3, R.raw.numbers_three, "Numbers"));
            addWord(new Word("four", "நான்கு", R.drawable.number_4, R.raw.numbers_four, "Numbers"));
            addWord(new Word("five", "ஐந்து", R.drawable.number_5, R.raw.numbers_five, "Numbers"));
            addWord(new Word("six", "ஆறு", R.drawable.number_6, R.raw.numbers_six, "Numbers"));
            addWord(new Word("seven", "ஏழு", R.drawable.number_7, R.raw.numbers_seven, "Numbers"));
            addWord(new Word("eight", "எட்டு", R.drawable.number_8, R.raw.numbers_eight, "Numbers"));
            addWord(new Word("nine", "ஒன்பது", R.drawable.number_9, R.raw.numbers_nine, "Numbers"));
            addWord(new Word("ten", "பத்து", R.drawable.number_10, R.raw.numbers_ten, "Numbers"));

            // Family //
            addWord(new Word("father", "அப்பா", R.drawable.father, R.raw.family_father, "Family"));
            addWord(new Word("mother", "அம்மா", R.drawable.mother, R.raw.family_mother, "Family"));
            addWord(new Word("son", "மகன்", R.drawable.son, R.raw.family_son, "Family"));
            addWord(new Word("daughter", "மகள்", R.drawable.daughter, R.raw.family_daughter, "Family"));
            addWord(new Word("older brother", "அண்ணன்", R.drawable.olderbrother, R.raw.family_older_brother, "Family"));
            addWord(new Word("younger brother", "தம்பி", R.drawable.youngerbrother, R.raw.family_younger_brother, "Family"));
            addWord(new Word("older sister", "அக்கா", R.drawable.oldersister, R.raw.family_older_sister, "Family"));
            addWord(new Word("younger sister", "தங்கை", R.drawable.youngersister, R.raw.family_younger_sister, "Family"));
            addWord(new Word("grandmother", "பாட்டி", R.drawable.grandmother, R.raw.family_grand_mother, "Family"));
            addWord(new Word("grandfather", "தாத்தா", R.drawable.grandfather, R.raw.family_grand_father, "Family"));

            // Phrases //
            addWord(new Word("Where are you going?", "நீஎங்கேபோகிறாய்?", R.drawable.empty_image, R.raw.phrases_where_are_you_going, "Phrases"));
            addWord(new Word("What is your name?", "உங்கள்பெயர்என்ன", R.drawable.empty_image, R.raw.phrases_what_is_your_name, "Phrases"));
            addWord(new Word("My name is...", "என்பெயர்...", R.drawable.empty_image, R.raw.phrases_my_name_is, "Phrases"));
            addWord(new Word("How are you feeling?", "நீஎப்படிஇருக்கிறாய்?", R.drawable.empty_image, R.raw.phrases_how_are_you_feeling, "Phrases"));
            addWord(new Word("I’m feeling good.", "நான்நன்றாகஇருகிறேன்.", R.drawable.empty_image, R.raw.phrases_im_feeling_good, "Phrases"));
            addWord(new Word("Are you coming?", "நீவருகிறாயா?", R.drawable.empty_image, R.raw.phrases_are_you_coming, "Phrases"));
            addWord(new Word("Yes, I’m coming.", "ஆம், நான்வருகிறேன்.", R.drawable.empty_image, R.raw.phrases_yes_im_coming, "Phrases"));
            addWord(new Word("I’m coming.", "நான்வருகிறேன்.", R.drawable.empty_image, R.raw.phrases_im_coming, "Phrases"));
            addWord(new Word("Let’s go.", "செல்லலாம்.", R.drawable.empty_image, R.raw.phrases_lets_go, "Phrases"));
            addWord(new Word("Come here.", "இங்கேவா.", R.drawable.empty_image, R.raw.phrases_come_here, "Phrases"));
        }
        db.close();
    }
}