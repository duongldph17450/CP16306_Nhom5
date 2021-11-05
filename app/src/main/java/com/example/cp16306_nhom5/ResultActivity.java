package com.example.cp16306_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvHighScore, tvTotalQuizQuestion, tvCorrectQues, tvWrongQues;
    Button btnStartQuiz, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnMainMenu = findViewById(R.id.result_btn_mainmenu);
        btnStartQuiz = findViewById(R.id.resuLt_btn_playAgain);
        tvHighScore = findViewById(R.id.result_tv_High_Score);
        tvTotalQuizQuestion = findViewById(R.id.result_tv_total_Ques);
        tvCorrectQues = findViewById(R.id.result_tv_Correct_Ques);
        tvWrongQues = findViewById(R.id.result_tv_Wrong_Ques);

    }
}