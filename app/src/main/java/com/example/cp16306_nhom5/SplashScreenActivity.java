package com.example.cp16306_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    TextView tvSplashText;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvSplashText = findViewById(R.id.tvLogoText);
        imgLogo = findViewById(R.id.imgLogo);
    }
}