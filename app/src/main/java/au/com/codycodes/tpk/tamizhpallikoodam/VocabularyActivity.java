package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class VocabularyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // Find the View
        CardView colors = (CardView) findViewById(R.id.CardView_colors);
        CardView numbers = (CardView) findViewById(R.id.CardView_numbers);
        CardView phrases = (CardView) findViewById(R.id.CardView_phrases);
        CardView family = (CardView) findViewById(R.id.CardView_family);

        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LearnActivity}
                Intent colorsIntent = new Intent(VocabularyActivity.this, LearnActivity.class);
                colorsIntent.putExtra("category","Colors");

                // Start the new activity
                startActivity(colorsIntent);
            }
        });


        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LearnActivity}
                Intent numbersIntent = new Intent(VocabularyActivity.this, LearnActivity.class);
                numbersIntent.putExtra("category","Numbers");

                // Start the new activity
                startActivity(numbersIntent);
            }
        });

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LearnActivity}
                Intent phrasesIntent = new Intent(VocabularyActivity.this, LearnActivity.class);
                phrasesIntent.putExtra("category","Phrases");

                // Start the new activity
                startActivity(phrasesIntent);
            }
        });

        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LearnActivity}
                Intent familyIntent = new Intent(VocabularyActivity.this, LearnActivity.class);
                familyIntent.putExtra("category","Family");

                // Start the new activity
                startActivity(familyIntent);
            }
        });
    }
}