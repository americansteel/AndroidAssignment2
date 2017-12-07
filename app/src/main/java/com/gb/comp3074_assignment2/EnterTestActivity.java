package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.classes.Test;
import com.gb.comp3074_assignment2.db.MedicalContract;
import com.gb.comp3074_assignment2.db.MedicalDataSource;

import org.w3c.dom.Text;

import java.util.List;

public class EnterTestActivity extends Activity {

    Test test;
    MedicalDataSource dataSource;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_test);
        intent = getIntent();
        String fName = intent.getStringExtra("firstName");
        dataSource = new MedicalDataSource(this);
        dataSource.open();
        List<Patient> patientsList = dataSource.getPatientByFirstName(fName);
        Patient patient = patientsList.get(0);

        TextView patientName = findViewById(R.id.patientNameText);
        TextView patientId = findViewById(R.id.patientIdText);

        patientName.setText(patient.getFirstName() + " " + patient.getLastName());
        patientId.setText(Integer.toString(patient.getId()));



    }

    public void onClickBtn(View v) {


        intent = getIntent();
        dataSource = new MedicalDataSource(this);
        dataSource.open();
        EditText testId = findViewById(R.id.testIdEdit);
        TextView patientId = findViewById(R.id.patientIdText);
        EditText bpl = findViewById(R.id.bplEdit);
        EditText bph = findViewById(R.id.bphEdit);
        EditText temperature = findViewById(R.id.temperatureEdit);
        EditText bloodpressure = findViewById(R.id.bloodPressureEdit);

        if (TextUtils.isEmpty(testId.getText().toString())) {
            testId.setError("Please enter a Test ID");
            return;
        } else if (TextUtils.isEmpty(bpl.getText().toString())) {
            bpl.setError("Please enter a BPL");
            return;
        } else if (TextUtils.isEmpty(bph.getText().toString())) {
            bph.setError("Please enter a BPH");
            return;
        } else if (TextUtils.isEmpty(temperature.getText().toString())) {
            temperature.setError("Please enter a Temperature");
            return;
        } else if (TextUtils.isEmpty(bloodpressure.getText().toString())) {
            bloodpressure.setError("Please enter a Blood Pressure");
            return;
        }


        test = new Test(Integer.parseInt(testId.getText().toString()),
                        Integer.parseInt(patientId.getText().toString()),
                        Integer.parseInt(bpl.getText().toString()),
                        Integer.parseInt(bph.getText().toString()),
                        Integer.parseInt(temperature.getText().toString()),
                        Integer.parseInt(bloodpressure.getText().toString()));
        dataSource.createTest(test);
        Toast.makeText(getApplicationContext(), "Test successfully created!", Toast.LENGTH_SHORT).show();
        String controller = intent.getStringExtra("controller");
        if (controller.equals("doctor")) {
        intent = new Intent(getApplicationContext(), DoctorMainActivity.class);
        startActivity(intent);
        } else {
            intent = new Intent(getApplicationContext(), NurseMainActivity.class);
            startActivity(intent);
        }
    dataSource.close();
    }
}
