package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.classes.Test;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

import java.util.ArrayList;
import java.util.List;

public class ChooseTestActivity extends Activity {

    Intent intent;
    MedicalDataSource dataSource;
    String fName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_test);
        dataSource = new MedicalDataSource(this);
        dataSource.open();
        intent = getIntent();
        fName = intent.getStringExtra("firstName");

        List<Patient> patientsList = dataSource.getPatientByFirstName(fName);
        Patient patient = patientsList.get(0);


        List<Test> testList = dataSource.getTestByPatientId(patient.getId());
        List<String>testIds = new ArrayList<>();

        for (Test test : testList) {
            testIds.add(Integer.toString(test.getId()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, testIds);
        Spinner sTests = findViewById(R.id.test_spinner);
        sTests.setAdapter(adapter);
    }
    public void onClickBtn(View v){
        Spinner spinner = findViewById(R.id.test_spinner);
        fName = intent.getStringExtra("firstName");
        intent = new Intent(getApplicationContext(), DisplayTestInfoActivity.class);
        intent.putExtra("firstName", fName);
        intent.putExtra("testId", spinner.getSelectedItem().toString());
        startActivity(intent);

    }
}
