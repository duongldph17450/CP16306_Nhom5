package com.example.cp16306_nhom5.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cp16306_nhom5.R;

public class WrongDialog {

    private Context mContext;

    private Dialog wrongDialog;

    public WrongDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void wrongDialog(String correctAnswer) {

        wrongDialog = new Dialog(mContext);
        wrongDialog.setContentView(R.layout.wrong_dialog);
        Button btnWrongDialog = (Button) wrongDialog.findViewById(R.id.btn_wrong_dialog);
        TextView tvCorrectAnswer = (TextView) wrongDialog.findViewById(R.id.tv_correct_answer);

        tvCorrectAnswer.setText("Đáp án đúng: " + String.valueOf(correctAnswer));

        btnWrongDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.dismiss();
            }
        });

        wrongDialog.show();
        wrongDialog.setCancelable(false);
        wrongDialog.setCanceledOnTouchOutside(false);

    }
}
