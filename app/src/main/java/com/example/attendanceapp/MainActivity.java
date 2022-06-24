package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageView img1,img2;
    FloatingActionButton floatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView)findViewById(R.id.studentlogo);
        img2 = (ImageView)findViewById(R.id.teacherlogo);
        floatbtn = findViewById(R.id.btnOpenDialog);

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

        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.floatbutton);
                dialog.show();

                Button addstudent = dialog.findViewById(R.id.addstudent);
                Button addteacher = dialog.findViewById(R.id.addteacher);

                addstudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(MainActivity.this,AddStudentActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Add stundet Side...", Toast.LENGTH_SHORT).show();
                    }
                });

                addteacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(MainActivity.this,AddFacultyActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Add Teacher Side...", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });



    }
}