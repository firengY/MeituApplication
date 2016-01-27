package com.firengy.meituapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

    public static final String NAME_SHAREDPREFERENCES = "my_prefer";
    public static final String KEY_FIRSTENTER = "first_enter";
    public static final int DELAY_MILLIS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = getSharedPreferences(NAME_SHAREDPREFERENCES, MODE_PRIVATE);
        boolean isFirstEnter = sp.getBoolean(KEY_FIRSTENTER, true);


        if (isFirstEnter) {
            mHandler.sendEmptyMessageDelayed(SWITCH_WELCOME, DELAY_MILLIS);
            sp.edit().putBoolean(KEY_FIRSTENTER, false).apply();
        } else {
            mHandler.sendEmptyMessageDelayed(SWITCH_MAIN, DELAY_MILLIS);
        }
    }

    public static final int SWITCH_WELCOME = 333;
    public static final int SWITCH_MAIN = 334;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent();
            switch (msg.what) {
                case SWITCH_WELCOME:
                    intent.setClass(SplashActivity.this, WelcomeActivity.class);
                    break;
                case SWITCH_MAIN:
                    intent.setClass(SplashActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };
}
