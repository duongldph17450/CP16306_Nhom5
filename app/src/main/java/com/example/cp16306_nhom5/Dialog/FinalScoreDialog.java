package com.example.cp16306_nhom5.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cp16306_nhom5.QuizActivity;
import com.example.cp16306_nhom5.R;

public class FinalScoreDialog {

    private Context mContext;
    private Dialog finalScoreDialog;

    private TextView tvFinalScore;

    public FinalScoreDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void finalScoreDialog(int correctAns, int wrongAns, int totalSizeOfQuiz) {

        finalScoreDialog = new Dialog(mContext);
        finalScoreDialog.setContentView(R.layout.final_score_dialog);

        final Button btnFinalScore = (Button) finalScoreDialog.findViewById(R.id.btn_final_score);

        finalScoreCal(correctAns, wrongAns, totalSizeOfQuiz);

        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalScoreDialog.dismiss();

                Intent intent = new Intent(mContext, QuizActivity.class);
                mContext.startActivity(intent);

            }
        });

        finalScoreDialog.show();
        finalScoreDialog.setCancelable(false);
        finalScoreDialog.setCanceledOnTouchOutside(false);

    }

    private void finalScoreCal(int correctAns, int wrongAns, int totalSizeOfQuiz) {

        int tempScore;
        tvFinalScore = (TextView) finalScoreDialog.findViewById(R.id.tv_final_score);

        if (correctAns == totalSizeOfQuiz) {

            tempScore = (correctAns * 20) - (wrongAns * 5);
            tvFinalScore.setText("Điểm của bạn: " + String.valueOf(tempScore));
        } else if (wrongAns == totalSizeOfQuiz) {

            tempScore = 0;
            tvFinalScore.setText("Điểm của bạn: " + String.valueOf(tempScore));
        } else if (correctAns > wrongAns) {

            tempScore = (correctAns * 20) - (wrongAns * 5);
            tvFinalScore.setText("Điểm của bạn: " + String.valueOf(tempScore));
        } else if (wrongAns > correctAns) {

            tempScore = (correctAns * 20) - (wrongAns * 5);
            tvFinalScore.setText("Điểm của bạn: " + String.valueOf(tempScore));
        } else if (correctAns == wrongAns) {

            tempScore = (correctAns * 20) - (wrongAns * 5);
            tvFinalScore.setText("Điểm của bạn: " + String.valueOf(tempScore));
        }
    }

}
