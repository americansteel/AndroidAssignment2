package com.gb.comp3074_assignment2.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gb.comp3074_assignment2.R;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

import java.util.List;

public class DisplayPatientInfoActivity extends Activity {

    MedicalDataSource dataSource;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_patient_info);

        TextView patientId = findViewById(R.id.patientIdText);
        TextView patientName = findViewById(R.id.nameText);
        TextView department = findViewById(R.id.departmentText);
        TextView room = findViewById(R.id.roomText);
        TextView doctorId = findViewById(R.id.doctorText);

        dataSource = new MedicalDataSource(this);
        dataSource.open();
        intent = getIntent();
        String fName = intent.getStringExtra("firstName");

        List<Patient> patientsList = dataSource.getPatientByFirstName(fName);
        Patient patient = patientsList.get(0);

        patientId.setText(String.valueOf(patient.getId()));
        patientName.setText(patient.getFirstName() + " " + patient.getLastName());
        department.setText(patient.getDepartment());
        room.setText(patient.getRoom());
        doctorId.setText(String.valueOf(patient.getDoctorId()));


    }
}
