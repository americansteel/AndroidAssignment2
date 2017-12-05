package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.classes.Test;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

import java.util.List;

public class DisplayTestInfoActivity extends Activity {

    private MedicalDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_test_info);

        TextView patientIdText = findViewById(R.id.patient_id_text);
        TextView testIdText = findViewById(R.id.test_id_text);
        TextView bplText = findViewById(R.id.bpl_text);
        TextView bphText = findViewById(R.id.bph_text);
        TextView temperatureText = findViewById(R.id.temperature_text);


        dataSource = new MedicalDataSource(this);
        dataSource.open();

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");

        List<Patient> patientsList = dataSource.getPatientByFirstName(firstName);
        Patient patient = patientsList.get(0);


        List<Test> testList = dataSource.getTestById(patient.getId());
        Test test = testList.get(0);

        patientIdText.setText(String.valueOf(patient.getId()));
        testIdText.setText(String.valueOf(test.getId()));
        bplText.setText(String.valueOf(test.getBpl()));
        bphText.setText(String.valueOf(test.getBph()));
        temperatureText.setText(String.valueOf(test.getTemperature()));


    }
}
