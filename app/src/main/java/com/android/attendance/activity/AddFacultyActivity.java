package com.android.attendance.activity;

import com.android.attendance.bean.FacultyBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFacultyActivity extends Activity {

    Button registerButton;
    EditText textFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfaculty);

        textFirstName = (EditText) findViewById(R.id.editTextFirstName);
        registerButton = (Button) findViewById(R.id.RegisterButton);

        registerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String first_name = textFirstName.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("please enter firstname");
                } else {

                    FacultyBean facultyBean = new FacultyBean();
                    facultyBean.setFaculty_firstname(first_name);

                    DBAdapter dbAdapter = new DBAdapter(AddFacultyActivity.this);
                    dbAdapter.addFaculty(facultyBean);

                    Intent intent = new Intent(AddFacultyActivity.this, MenuActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
