package com.example.cp16306_nhom5.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cp16306_nhom5.R;

public class CorrectDialog {

    private Context mContext;

    private Dialog correctDialog;

    public CorrectDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score) {

        correctDialog = new Dialog(mContext);
        correctDialog.setContentView(R.layout.correct_dialog);

        Button btnCorrectDialog = (Button) correctDialog.findViewById(R.id.btn_correct_dialog);

        score(score);

        btnCorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctDialog.dismiss();
            }
        });

        correctDialog.show();
        correctDialog.setCancelable(false);
        correctDialog.setCanceledOnTouchOutside(false);
    }

    private void score(int score) {

        TextView tvScore = (TextView) correctDialog.findViewById(R.id.tv_score);
        tvScore.setText("Điểm: " + String.valueOf(score));
    }
}
