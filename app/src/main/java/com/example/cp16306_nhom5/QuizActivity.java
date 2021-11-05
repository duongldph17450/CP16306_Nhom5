package com.example.cp16306_nhom5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp16306_nhom5.Audio.PlayAudioForAnswer;
import com.example.cp16306_nhom5.Dialog.CorrectDialog;
import com.example.cp16306_nhom5.Dialog.FinalScoreDialog;
import com.example.cp16306_nhom5.Dialog.WrongDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnConfirmNext;
    private TextView tvQuestions, tvScore, tvQuestionsCount, tvCountDown, tvCorrect, tvWrong;

    private ArrayList<Questions> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Questions currentQuestions;
    private boolean answerd;

    private final Handler handler = new Handler();

    private ColorStateList defaultTextColor;

    private int correctAns = 0, wrongAns = 0;

    private FinalScoreDialog finalScoreDialog;
    private CorrectDialog correctDialog;
    private WrongDialog wrongDialog;
    private PlayAudioForAnswer playAudioForAnswer;

    int FLAG = 0;

    int score = 0;

    private int totalSizeOfQuiz = 0;

    private static final long COUNT_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupUI();

        fetchDB();

        defaultTextColor = rb1.getTextColors();

        finalScoreDialog = new FinalScoreDialog(this);
        correctDialog = new CorrectDialog(this);
        wrongDialog = new WrongDialog(this);
        playAudioForAnswer = new PlayAudioForAnswer(this);
    }

    private void setupUI() {
        tvQuestions = findViewById(R.id.tvQuestion);

        tvScore = findViewById(R.id.tvScore);
        tvQuestionsCount = findViewById(R.id.tvTotalQuestion);
        tvCountDown = findViewById(R.id.tvViewTimer);

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);



        btnConfirmNext = findViewById(R.id.btnConfirmNext);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
    }

    private void fetchDB() {

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();

        startQuiz();
    }

    private void startQuiz() {

        questionTotalCount = questionList.size();
        Collections.shuffle(questionList);

        showQuestions(); // gọi đến phương thức showQuestions

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.radio_button1:

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                    break;
                    case R.id.radio_button2:

                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                    case R.id.radio_button3:

                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                    case R.id.radio_button4:

                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

                        break;
                }
            }
        });

        btnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                        quizOperations();
                    } else {

                        Toast.makeText(QuizActivity.this, "Hãy chọn đáp án", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    private void quizOperations() {

        answerd = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        checkSolution(answerNr, rbSelected);
        
    }

    private void checkSolution(int answerNr, RadioButton rbSelected) {

        switch (currentQuestions.getAnswerNr()) {
            case 1:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb1.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("Đúng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("Điểm: " + String.valueOf(score));
                    correctDialog.correctDialog(score);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 2000);

                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("Điểm: " + String.valueOf(score));

                    String correctAnswer = (String) rb1.getText();
                    wrongDialog.wrongDialog(correctAnswer);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 1000);
                }
                break;

            case 2:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb2.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("Đúng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("Điểm: " + String.valueOf(score));
                    correctDialog.correctDialog(score);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 2000);

                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("Điểm: " + String.valueOf(score));

                    String correctAnswer = (String) rb2.getText();
                    wrongDialog.wrongDialog(correctAnswer);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 1000);
                }
                break;

            case 3:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb3.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("Đúng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("Điểm: " + String.valueOf(score));
                    correctDialog.correctDialog(score);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 2000);

                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("Điểm: " + String.valueOf(score));

                    String correctAnswer = (String) rb3.getText();
                    wrongDialog.wrongDialog(correctAnswer);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 1000);
                }
                break;

            case 4:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb4.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_correct));

                    correctAns++;
                    tvCorrect.setText("Đúng: " + String.valueOf(correctAns));

                    score += 20;
                    tvScore.setText("Điểm: " + String.valueOf(score));
                    correctDialog.correctDialog(score);

                    FLAG = 1;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 2000);

                } else {

                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    tvWrong.setText("Sai: " + String.valueOf(wrongAns));

                    score -= 5;
                    tvScore.setText("Điểm: " + String.valueOf(score));

                    String correctAnswer = (String) rb4.getText();
                    wrongDialog.wrongDialog(correctAnswer);

                    FLAG = 2;
                    playAudioForAnswer.setAudioForAnswer(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    }, 1000);
                }
                break;


        }

        if (questionCounter < questionTotalCount) {

            btnConfirmNext.setText("Tiếp tục");
        }

    }

    private void changeToIncorrectColor(RadioButton rbSelected) {

        rbSelected.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_wrong));

        rbSelected.setTextColor(Color.BLACK);
    }

    private void showQuestions() {

        rbGroup.clearCheck();

        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

        if (questionCounter < questionTotalCount) {

            currentQuestions = questionList.get(questionCounter);
            tvQuestions.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());

            questionCounter++;
            answerd = false;

            btnConfirmNext.setText("Tiếp tục");

            tvQuestionsCount.setText("Câu hỏi: " + questionCounter + "/" + questionTotalCount);

            timeLeftInMillis = COUNT_IN_MILLIS;
            startCountDown();


        } else {
            //Khi chạy hết số câu hỏi sẽ hiện ra dialog



            //Sử dụng handler để delay hiển thị
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                

                }
            }, 2000);
        }
    }


                // timer code

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;

                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis/1000) / 60;
        int seconds = (int) (timeLeftInMillis/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {

            tvCountDown.setTextColor(Color.RED);

            FLAG = 3;
            playAudioForAnswer.setAudioForAnswer(FLAG);

        } else {

            tvCountDown.setTextColor(defaultTextColor);

        }

        if (timeLeftInMillis == 0) {

            Toast.makeText(this, "Hết thời gian", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    startActivity(intent);

                }
            }, 2000);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}