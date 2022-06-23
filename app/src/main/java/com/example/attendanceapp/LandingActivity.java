package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
                finish();
            }
        }, 1000 );
    }
}