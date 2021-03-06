package com.example.attendanceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.attendanceapp.R;
import com.example.attendanceapp.bean.FacultyBean;
import com.example.attendanceapp.db.DBAdapter;

import java.util.ArrayList;

public class ViewFacultyActivity extends AppCompatActivity {

    ArrayList<FacultyBean> facultyBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> facultyList = new ArrayList<String>();

        facultyBeanList=dbAdapter.getAllFaculty();

        for(FacultyBean facultyBean : facultyBeanList)
        {
            String faculty = " Name : " + facultyBean.getFaculty_firstname() + " " + facultyBean.getFaculty_lastname() +
                    "\n Mobile No : " + facultyBean.getFaculty_mobilenumber() ;

            facultyList.add(faculty);
            Log.d("faculty: ", faculty);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_view_faculty, R.id.labelF, facultyList);
        listView.setAdapter( listAdapter );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFacultyActivity.this);

                alertDialogBuilder.setTitle(getTitle());
                alertDialogBuilder.setMessage("Are you sure you want to delete?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        facultyList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
                        facultyBeanList=dbAdapter.getAllFaculty();

//                        for(FacultyBean facultyBean : facultyBeanList)
//                        {
//                            String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();
//                            facultyList.add(users);
//                            Log.d("users: ", users);
//
//                        }

                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();

                return false;
            }
        });


    }
}