package com.example.cp16306_nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnConfirmNext;
    private TextView tvQuestions, tvScore, tvQuestionsCount, tvCountDown, tvCorrect, tvWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupUI();
    }

    private void setupUI() {

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvCountDown = findViewById(R.id.tvViewTimer);
        tvQuestionsCount = findViewById(R.id.tvTotalQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvQuestions = findViewById(R.id.tvQuestion);

        btnConfirmNext = findViewById(R.id.btnConfirmNext);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
    }
}