package com.example.attendanceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendanceapp.R;
import com.example.attendanceapp.bean.FacultyBean;
import com.example.attendanceapp.context.ApplicationContext;
import com.example.attendanceapp.db.DBAdapter;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    Spinner spinnerloginas;
    String userrole;
    private String[] userRoleString = new String[] { "admin", "faculty"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login =(Button)findViewById(R.id.buttonlogin);
        username=(EditText)findViewById(R.id.editTextTextPersonName);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        spinnerloginas=(Spinner)findViewById(R.id.spinnerforlogin);

        spinnerloginas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                userrole =(String) spinnerloginas.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloginas.setAdapter(adapter_role);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(userrole.equals("admin"))
                {

                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name))
                    {
                        username.setError("Invalid User Name");
                    }
                    else if(TextUtils.isEmpty(pass_word))
                    {
                        password.setError("enter password");
                    }
                    else
                    {
                        if(user_name.equals("admin") & pass_word.equals("admin123")){
                            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                else
                {
                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name))
                    {
                        username.setError("Invalid User Name");
                    }
                    else if(TextUtils.isEmpty(pass_word))
                    {
                        password.setError("enter password");
                    }
                    DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
                    FacultyBean facultyBean = dbAdapter.validateFaculty(user_name, pass_word);

                    if(facultyBean!=null)
                    {
                        Intent intent = new Intent(LoginActivity.this, AddAttandanceSessionActivity.class);
                        startActivity(intent);
                        ((ApplicationContext)LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });




    }
}