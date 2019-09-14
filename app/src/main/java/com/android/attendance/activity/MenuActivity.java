package com.android.attendance.activity;

import java.util.ArrayList;

import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.telephony.SmsManager;
import android.Manifest;
import android.widget.Toast;

import com.android.attendance.bean.StudentBean;

public class MenuActivity extends Activity {

    Button addStudent;
    Button addFaculty;
    Button viewStudent;
    Button viewFaculty;
    Button logout;
    final ArrayList<String> studentList = new ArrayList<String>();
    String users = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        addStudent = (Button) findViewById(R.id.buttonaddstudent);
        addFaculty = (Button) findViewById(R.id.buttonaddfaculty);
        viewStudent = (Button) findViewById(R.id.buttonViewstudent);
        viewFaculty = (Button) findViewById(R.id.buttonviewfaculty);
        logout = (Button) findViewById(R.id.buttonlogout);
        ArrayList<StudentBean> studentBeanList;
        DBAdapter dbAdapter = new DBAdapter(this);
        studentBeanList = dbAdapter.getAllStudent();
        for (StudentBean studentBean : studentBeanList) {
            users += "\nFirstName: " + studentBean.getStudent_firstname() +
                    "\nContact:" + studentBean.getStudent_mobilenumber();
        }

        addStudent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MenuActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        addFaculty.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MenuActivity.this, AddFacultyActivity.class);
                startActivity(intent);
            }
        });

        viewFaculty.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MenuActivity.this, ViewFacultyActivity.class);
                startActivity(intent);
            }
        });

        viewStudent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MenuActivity.this, ViewStudentActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void smsGonder(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int smsPermission = checkSelfPermission(Manifest.permission.SEND_SMS);
            Toast.makeText(getApplicationContext(), "Send message successfully", Toast.LENGTH_SHORT).show();

            if (smsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 0);
            } else if (smsPermission == PackageManager.PERMISSION_GRANTED) {
                smsSendingFun();
            }
        } else {
            smsSendingFun();
        }
    }

    public void smsSendingFun() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+905427397855", "+905427397855", users, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
