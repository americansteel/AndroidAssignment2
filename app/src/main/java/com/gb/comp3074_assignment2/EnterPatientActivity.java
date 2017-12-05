package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        dataSource = new MedicalDataSource(this);
        dataSource.open();
        dataSource.createPatient(patient);
        dataSource.close();
        intent = new Intent(getApplicationContext(), NurseMainActivity.class);
        startActivity(intent);
    }
}
