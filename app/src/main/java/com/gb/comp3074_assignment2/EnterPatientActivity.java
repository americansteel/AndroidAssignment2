package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

public class EnterPatientActivity extends Activity {

    Patient patient;
    MedicalDataSource dataSource;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_patient);
    }

    public void onClickBtn(View v)
    {
        EditText firstName = findViewById(R.id.firstNameEdit);
        EditText lastName = findViewById(R.id.lastNameEdit);
        EditText department = findViewById(R.id.departmentEdit);
        EditText room = findViewById(R.id.roomEdit);
        EditText doctorId = findViewById(R.id.doctorIdEdit);
        patient = new Patient(firstName.getText().toString(),
                              lastName.getText().toString(),
                              department.getText().toString(),
                              Integer.parseInt(doctorId.getText().toString()),
                              room.getText().toString()
                                );
        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("Please enter a First Name");
            return;
        } else if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("Please enter a Last Name");
            return;
        } else if (TextUtils.isEmpty(department.getText().toString())) {
            department.setError("Please enter a Department");
            return;
        } else if (TextUtils.isEmpty(room.getText().toString())) {
            room.setError("Please enter a Room Number");
            return;
        } else if (TextUtils.isEmpty(doctorId.getText().toString())) {
            doctorId.setError("Please enter a Doctor ID");
            return;
        }


        dataSource = new MedicalDataSource(this);
        dataSource.open();
        dataSource.createPatient(patient);
        dataSource.close();
        Toast.makeText(getApplicationContext(), "Patient successfully created!", Toast.LENGTH_SHORT).show();
        intent = new Intent(getApplicationContext(), NurseMainActivity.class);
        startActivity(intent);
    }
}
