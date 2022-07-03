package com.example.attendanceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.attendanceapp.R;
import com.example.attendanceapp.bean.AttendanceBean;
import com.example.attendanceapp.bean.StudentBean;
import com.example.attendanceapp.context.ApplicationContext;
import com.example.attendanceapp.db.DBAdapter;

import java.util.ArrayList;

public class ViewAttendanceByFacultyActivity extends AppCompatActivity {

    ArrayList<AttendanceBean> attendanceBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> attendanceList = new ArrayList<String>();
        attendanceList.add("Id | StudentName |  Status");

        attendanceBeanList=((ApplicationContext)ViewAttendanceByFacultyActivity.this.getApplicationContext()).getAttendanceBeanList();

        for(AttendanceBean attendanceBean : attendanceBeanList)
        {
            String users = "";
            if(attendanceBean.getAttendance_session_id() != 0)
            {
                DBAdapter dbAdapter = new DBAdapter(ViewAttendanceByFacultyActivity.this);
                StudentBean studentBean =dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
                users = attendanceBean.getAttendance_student_id()+".     "+studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()+"          "+attendanceBean.getAttendance_status();
            }
            else
            {
                users = attendanceBean.getAttendance_status();
            }

            attendanceList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list, R.id.labelAttendance, attendanceList);
        listView.setAdapter( listAdapter );

		/*listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {



				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewAttendanceByFacultyActivity.this);

				alertDialogBuilder.setTitle(getTitle()+"decision");
				alertDialogBuilder.setMessage("Are you sure?");

				alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						facultyList.remove(position);
						listAdapter.notifyDataSetChanged();
						listAdapter.notifyDataSetInvalidated();

						dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
						facultyBeanList=dbAdapter.getAllFaculty();

						for(FacultyBean facultyBean : facultyBeanList)
						{
							String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();
							facultyList.add(users);
							Log.d("users: ", users);

						}

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
*/

    }


}