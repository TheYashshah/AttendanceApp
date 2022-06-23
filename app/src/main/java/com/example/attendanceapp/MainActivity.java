package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView)findViewById(R.id.studentlogo);
        img2 = (ImageView)findViewById(R.id.teacherlogo);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "You on stundet Side...", Toast.LENGTH_SHORT).show();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "You on teacher Side...", Toast.LENGTH_SHORT).show();
            }
        });



    }
}