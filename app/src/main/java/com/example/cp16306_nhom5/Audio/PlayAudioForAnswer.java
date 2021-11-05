package com.example.cp16306_nhom5.Audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.cp16306_nhom5.R;

public class PlayAudioForAnswer {

    private Context mContext;
    private MediaPlayer mediaPlayer;

    public PlayAudioForAnswer(Context mContext) {
        this.mContext = mContext;
    }

    public void setAudioForAnswer(int flag) {

        switch (flag) {

            case 1:
                int correctAudio = R.raw.correct;
                playMusic(correctAudio);
                break;
            case 2:
                int wrongAudio = R.raw.wrong;
                playMusic(wrongAudio);
                break;
            case 3:
                int timerAudio = R.raw.timetick;
                playMusic(timerAudio);
                break;

        }

    }

    private void playMusic(int audioFile) {

        mediaPlayer = MediaPlayer.create(mContext, audioFile);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
    }
}
