package com.test.sm.mstest;

import android.os.Handler;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ssIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(ssIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
