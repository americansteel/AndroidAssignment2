package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

import java.util.ArrayList;
import java.util.List;

public class DisplayTestActivity extends Activity {

    private MedicalDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_test);

        dataSource = new MedicalDataSource(this);

        dataSource.open();
        List<Patient> patientList = dataSource.getAllPatients();
        List<String> patientNames = new ArrayList<String>();

        for (Patient patient : patientList) {
            patientNames.add(patient.getFirstName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, patientNames);
        Spinner sPatients = findViewById(R.id.patient_spinner);
        sPatients.setAdapter(adapter);

    }

    public void onClickBtn(View v) {
        Spinner spinner = findViewById(R.id.patient_spinner);
        Intent intent = new Intent(getApplicationContext(), DisplayTestInfoActivity.class);
        intent.putExtra("firstName", spinner.getSelectedItem().toString());
        startActivity(intent);
    }

}

