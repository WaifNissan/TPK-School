package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.media.MediaPlayer;

public class QuestionActivity extends AppCompatActivity {

    private String correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        DatabaseHelper db = new DatabaseHelper(this);

        final Quiz quiz = db.getQuiz();

        TextView category = findViewById(R.id.category);
        category.setText(quiz.getCategory());

        TextView question = findViewById(R.id.question);
        question.setText(quiz.getTamilTranslation());

        TextView option1 = findViewById(R.id.option1);
        option1.setText(quiz.getOption1());
        TextView option2 = findViewById(R.id.option2);
        option2.setText(quiz.getOption2());
        TextView option3 = findViewById(R.id.option3);
        option3.setText(quiz.getOption3());
        TextView option4 = findViewById(R.id.option4);
        option4.setText(quiz.getOption4());

        final Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.options);
                if(rg.getCheckedRadioButtonId() != -1) {
                    String selectedOption = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                    if (selectedOption.equals(quiz.getAnswer())) {
                        correct = "CORRECT";
                    } else {
                        correct = "INCORRECT";
                    }

                    // Create a new intent to open the {@link LearnActivity}
                    Intent resultIntent = new Intent(QuestionActivity.this, ResultSplashActivity.class);
                    resultIntent.putExtra("correct", correct);

                    // Start the new activity
                    startActivity(resultIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView play =  findViewById(R.id.play);
        final MediaPlayer mp = MediaPlayer.create(this, quiz.getAudioResourceId());

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
            }
        });
    }
}
