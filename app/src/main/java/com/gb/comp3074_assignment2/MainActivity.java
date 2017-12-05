package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gb.comp3074_assignment2.classes.Doctor;
import com.gb.comp3074_assignment2.classes.Nurse;
import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.classes.Test;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends Activity {

    EditText username;
    EditText password;
    private MedicalDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        dataSource = new MedicalDataSource(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //open db
        dataSource.open();
        List<Patient> patients = dataSource.getAllPatients();
        //create DB data
        if (patients.isEmpty()) {
            for (int i = 1; i < 11; i++) {
                String iStr = String.valueOf(i);
                dataSource.createPatient(new Patient(i, "firstName" + iStr, "lastName" + iStr, "MainDepartment", i + 30, "Room " + iStr));
                dataSource.createTest(new Test(i, i, 90 + i, 100 + i, 98 + i));
                dataSource.createNurse(new Nurse(i, "firstName" + iStr, "lastName" + iStr, "MainDepartment"));
                dataSource.createDoctor(new Doctor(i, "firstName" + iStr, "lastName" + iStr, "MainDepartment"));
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        dataSource.close();
    }

    public void onLoginClick(View v) {
        if (username.getText().toString().equals("doctor") && password.getText().toString().equals("doctor")) {
            Intent intent = new Intent(getApplicationContext(), DoctorMainActivity.class);
            startActivity(intent);
        } else if (username.getText().toString().equals("nurse") && password.getText().toString().equals("nurse")) {
            Intent intent = new Intent(getApplicationContext(), NurseMainActivity.class);
            startActivity(intent);
        }
    }
}
