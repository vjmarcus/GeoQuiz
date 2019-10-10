package com.freshappbooks.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "true";
    private boolean answerIsTrue;
    private Button showAnswerButton;
    private TextView textViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        showAnswerButton = findViewById(R.id.show_answer_button);
        textViewAnswer = findViewById(R.id.answer_text_view);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerIsTrue) {
                    textViewAnswer.setText(R.string.question_true);
                } else {
                    textViewAnswer.setText(R.string.question_false);
                }
            }
        });
    }

    public static Intent newIntent(Context context, boolean answerIsTrue) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }
}
