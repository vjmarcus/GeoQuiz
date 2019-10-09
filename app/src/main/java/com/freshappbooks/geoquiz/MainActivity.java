package com.freshappbooks.geoquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonTrue;
    private Button buttonFalse;
    private ImageButton buttonPrev;
    private ImageButton buttonNext;
    private TextView questionTextView;
    private int currentIndex = 0;
    private Question [] questionBook = new Question[] {
      new Question(R.string.question_australia, true),
      new Question(R.string.question_oceans, true),
      new Question(R.string.question_mideast, false),
      new Question(R.string.question_africa, false),
      new Question(R.string.question_americas, true),
      new Question(R.string.question_asia, true),
    };

    private static final String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrue = findViewById(R.id.btn_true);
        buttonFalse = findViewById(R.id.btn_false);
        buttonNext = findViewById(R.id.btn_next);
        buttonPrev = findViewById(R.id.btn_prev);
        questionTextView = findViewById(R.id.question_tv);

        updateQuestion();

        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questionBook.length;
                updateQuestion();
            }
        });

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questionBook.length;
                updateQuestion();

            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex = (currentIndex - 1) % questionBook.length;
                    updateQuestion();

                }
            }
        });
    }

    private void updateQuestion() {
        int question = questionBook[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPassedTrue) {
        boolean answerIsTrue = questionBook[currentIndex].isAnswerTrue();
        int messageResId;
        if (userPassedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putInt(KEY_INDEX, currentIndex);
        }
    }
}
