package com.coohomeless.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.coohomeless.R;
import com.coohomeless.ui.auth.LoginActivity;

public class SplashScreenActivity extends Activity {

    private static final int SPLASH_SCREEN_DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoginScreen();
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }

    public void showLoginScreen() {
        Intent toLogin = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(toLogin);
    }
}
